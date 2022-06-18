package com.halter.herdservice.model.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HalterCollar {
    @JsonProperty("id")
    private String collarId;
    private String serialNumber;
    private String lat;
    private String lng;
    private Boolean healthy;
    private Timestamp timestamp;
}
