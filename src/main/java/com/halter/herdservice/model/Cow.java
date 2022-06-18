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
public class Cow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "cowNumber")
    private Long cowNumber;

    @Column(name= "collarId")
    private Long collarId;

    private String collarStatus;

    private String

    public Cow(Long cowNumber) {
        this.setCowNumber(cowNumber);
    }
}
