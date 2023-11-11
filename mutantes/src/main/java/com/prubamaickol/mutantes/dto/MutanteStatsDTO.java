package com.prubamaickol.mutantes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"countMutantDna", "countHumanDna", "ratio"})
public class MutanteStatsDTO {
    @JsonProperty("count_mutant_dna")
    private double countMutantDna;
    @JsonProperty("count_human_dna")
    private double countHumanDna;
    @JsonProperty("ratio")
    private double ratio;
}
