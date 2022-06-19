package com.halter.herdservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cow")
public class Cow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "cowNumber")
    private String cowNumber;

    @Column(name= "collarId")
    private String collarId;

    private String collarStatus;

    private Float lastKnownLoc_lat;

    private Float lastKnownLoc_lng;

    public Cow(String cowNumber) {
        this.setCowNumber(cowNumber);
    }
}
