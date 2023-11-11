package com.prubamaickol.mutantes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "mutantes")
public class Mutante {
    @Id
    private String dna;

    private boolean isMutant;
}
