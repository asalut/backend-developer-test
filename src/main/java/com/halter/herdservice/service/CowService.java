package com.halter.herdservice.service;

import com.halter.herdservice.model.Cow;
import com.halter.herdservice.model.bean.CowRequest;
import com.halter.herdservice.model.bean.CowResponse;
import com.halter.herdservice.model.bean.HalterCollar;
import com.halter.herdservice.model.repository.CowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CowService {

    @Autowired
    private CowRepository cowRepository;

    @Autowired
    private HalterExternalResource halterExternalResource;

    @Cacheable(value = "cows", key = "#theCow.cowNumber")
    public CowResponse addCow(CowRequest theCow) {
        HalterCollar collarData = this.halterExternalResource.getLatestCollarData(theCow.getCollarId());

        Cow moo = new Cow();
        moo.setCollarId(theCow.getCollarId());
        moo.setCowNumber(theCow.getCowNumber());
        moo.setCollarStatus(collarData.getHealthy() ? "Healthy" : "Broken");
        moo.setLastKnownLoc_lat(NumberUtils.parseNumber(collarData.getLat(), Float.class));
        moo.setLastKnownLoc_lng(NumberUtils.parseNumber(collarData.getLng(), Float.class));

        return new CowResponse(this.cowRepository.save(moo));
    }

    public CowResponse getCowByCollarId(String collarId) {
        return new CowResponse(this.cowRepository.findByCollarId(collarId).get());
    }

    @Cacheable(value = "cows")
    public List<CowResponse> getAllCows() {
        return this.cowRepository.findAll()
                .stream().map(moo -> new CowResponse(moo))
                .sorted(Comparator.comparing(CowResponse::getCowNumber))
                .collect(Collectors.toList());
    }

    /**
     * This method takes cowId parameter but assumes that we're only changing the collarId of the cow
     * @param cowId
     * @param theCow
     * @return
     * @throws Exception
     */
    @CachePut(value = "cows", key = "#theCow.cowNumber")
    public CowResponse updateCow(String cowId, CowRequest theCow) throws Exception {
        Optional<Cow> mooPtional = this.cowRepository.findById(UUID.fromString(cowId));
        if (mooPtional.isEmpty()) {
            throw new Exception(String.format("Cow not found for id [%s]", cowId));
        }

        Cow thisMoo = mooPtional.get();
        if (!thisMoo.getCowNumber().equals(theCow.getCowNumber())) {
            throw new Exception(String.format("Incorrect cow number [%s] for id [%s]", theCow.getCowNumber(), cowId));
        }

        Optional<Cow> mooExistingCollarOwner = this.cowRepository.findByCollarId(theCow.getCollarId());
        if (mooExistingCollarOwner.isPresent()
                && !thisMoo.getCowNumber().equals(mooExistingCollarOwner.get().getCowNumber())) {
            // throw error if collarId is already used by other cow
            throw new Exception(String.format("Collar id [%s] is currently used by another cow", theCow.getCollarId()));
        }

        HalterCollar collarData = this.halterExternalResource.getLatestCollarData(theCow.getCollarId());

        thisMoo.setCollarId(theCow.getCollarId());
        thisMoo.setCollarStatus(collarData.getHealthy() ? "Healthy" : "Broken");
        thisMoo.setLastKnownLoc_lat(NumberUtils.parseNumber(collarData.getLat(), Float.class));
        thisMoo.setLastKnownLoc_lng(NumberUtils.parseNumber(collarData.getLng(), Float.class));

        return new CowResponse(this.cowRepository.save(thisMoo));
    }
}
