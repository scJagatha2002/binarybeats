package com.dsa.binarybeats.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String topic;

    private String Description;

    @ManyToOne
    private Difficulty_level Difficulty;

    @Column(columnDefinition = "TEXT")
    private String Code;

    private String referenceLink;

    private String solutionLink;

}
