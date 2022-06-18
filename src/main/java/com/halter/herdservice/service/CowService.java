package com.halter.herdservice.service;

import com.halter.herdservice.model.Cow;
import com.halter.herdservice.model.bean.CowRequest;
import com.halter.herdservice.model.bean.CowResponse;
import com.halter.herdservice.model.bean.HalterCollar;
import com.halter.herdservice.model.repository.CowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

@Service
public class CowService {

    @Autowired
    private CowRepository cowRepository;

    @Autowired
    private HalterExternalResource halterExternalResource;

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


}
