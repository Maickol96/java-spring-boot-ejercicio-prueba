package com.prubamaickol.mutantes.controller;

import com.prubamaickol.mutantes.dto.MensajeDTO;
import com.prubamaickol.mutantes.dto.MutanteStatsDTO;
import com.prubamaickol.mutantes.dto.RequestDTO;
import com.prubamaickol.mutantes.entity.Mutante;
import com.prubamaickol.mutantes.service.MutanteService;
import com.prubamaickol.mutantes.util.Utilitarios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

public class MutanteControllerTest {
    MutanteService mutanteServiceMock = Mockito.mock(MutanteService.class);

    Utilitarios utilitarios = new Utilitarios();

    MutanteController mutanteController = new MutanteController( mutanteServiceMock, utilitarios);
    @BeforeEach
    void setUp() {
        Mockito.when(mutanteServiceMock.save(Mockito.any(Mutante.class))).thenReturn(new Mutante());
    }

    @Test
    void validaMutanteEsMutante() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setDna(new String[]{"ATGCGA", "AATTGC", "ATATGT", "AGAATG", "CCCCTA", "TCACTG"});
        ResponseEntity<MensajeDTO> respuestaServicio = mutanteController.validaMutante(requestDTO);
        Assertions.assertEquals("Es mutante", Objects.requireNonNull(respuestaServicio.getBody()).getMensaje());
    }

    @Test
    void validaMutanteEsHumano() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setDna(new String[]{"ATGCGA", "GAGTGC", "ATATGT", "AGAATG", "CTCCTA", "TCACTG"});
        ResponseEntity<MensajeDTO> respuestaServicio = mutanteController.validaMutante(requestDTO);
        Assertions.assertEquals("No es mutante", Objects.requireNonNull(respuestaServicio.getBody()).getMensaje());
    }

    @Test
    void validaMutanteError() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setDna(new String[]{"ATGCgA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"});
        ResponseEntity<MensajeDTO> respuestaServicio = mutanteController.validaMutante(requestDTO);
        Assertions.assertEquals("El dna enviado no es correcto", Objects.requireNonNull(respuestaServicio.getBody()).getMensaje());
    }

    @Test
    void estadisticas() {
        MutanteStatsDTO mutanteStatsDTO = new MutanteStatsDTO();
        mutanteStatsDTO.setCountMutantDna(1);
        mutanteStatsDTO.setCountHumanDna(1);
        mutanteStatsDTO.setRatio(1);
        Mockito.when(mutanteServiceMock.consultaEstadisticas()).thenReturn(mutanteStatsDTO);
        ResponseEntity<MutanteStatsDTO> respuestaServicio = mutanteController.estadisticas();
        Assertions.assertEquals(1, Objects.requireNonNull(respuestaServicio.getBody()).getCountMutantDna());
        Assertions.assertEquals(1, Objects.requireNonNull(respuestaServicio.getBody()).getCountHumanDna());
        Assertions.assertEquals(1, Objects.requireNonNull(respuestaServicio.getBody()).getRatio());
    }



}
