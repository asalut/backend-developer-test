package com.halter.herdservice.service;

import com.halter.herdservice.model.bean.HalterCollar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;

@Service
public class HalterExternalResource {

    @Value("${halter-api.collar.data}")
    private String externalApi;

    @Autowired
    private RestTemplate restTemplate;

    public List<HalterCollar> getCollarData(String collarId) {
        return this.restTemplate
                .exchange(String.format(externalApi, collarId), HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<HalterCollar>>() {})
                .getBody();
    }

    public HalterCollar getLatestCollarData(String collarId) {
        List<HalterCollar> collarData = this.getCollarData(collarId);
        if (!collarData.isEmpty()) {
            // get the latest data
            collarData.sort(Comparator.comparing(HalterCollar::getTimestamp).reversed());
            return collarData.get(0);
        }

        return new HalterCollar();
    }

}
