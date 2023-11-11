package com.prubamaickol.mutantes.util;


import com.prubamaickol.mutantes.dto.MutanteStatsDTO;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Utilitarios {

    public MutanteStatsDTO buildMutantDTO(double mutante, double noMutante){
        MutanteStatsDTO mutanteStatsDTO = new MutanteStatsDTO();
        mutanteStatsDTO.setCountMutantDna(mutante);
        mutanteStatsDTO.setCountHumanDna(noMutante);
        mutanteStatsDTO.setRatio(calculaRatio(mutante, noMutante));
        return mutanteStatsDTO;
    }

    private double calculaRatio(double mutante, double noMutante){
        return noMutante == 0 ? 0 : mutante/noMutante;
    }

    public boolean validaDna(String[] dna){
        String regex = "[ATCG]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        for(String cadena: dna){
            matcher = pattern.matcher(cadena);
            if(!matcher.matches())
                return false;
        }
        return dna.length > 3;
    }

    public boolean isMutant(String[] dna){
        int totalDna = 0;
        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna[i].length(); j++) {
                if (j < dna[i].length() - 3) {
                    if (isEqual(dna[i].charAt(j), dna[i].charAt(j + 1), dna[i].charAt(j + 2), dna[i].charAt(j + 3))) {
                        totalDna++;
                    }
                }
                if (i < dna.length - 3) {
                    if (isEqual(dna[i].charAt(j), dna[i + 1].charAt(j), dna[i + 2].charAt(j), dna[i + 3].charAt(j))) {
                        totalDna++;
                    }
                }
                if (i < dna.length - 3 && j < dna[i].length() - 3) {
                    if (isEqual(dna[i].charAt(j), dna[i + 1].charAt(j + 1), dna[i + 2].charAt(j + 2), dna[i + 3].charAt(j + 3))) {
                        totalDna++;
                    }
                }
                if (i >= 3 && j < dna[i].length() - 3) {
                    if (isEqual(dna[i].charAt(j), dna[i - 1].charAt(j + 1), dna[i - 2].charAt(j + 2), dna[i - 3].charAt(j + 3))) {
                        totalDna++;
                    }
                }
                if (totalDna > 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isEqual(char a, char b, char c, char d) {
        return a == b && b == c && c == d;
    }
}
