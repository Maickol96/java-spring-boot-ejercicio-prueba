package com.prubamaickol.mutantes.controller;

import com.google.gson.Gson;
import com.prubamaickol.mutantes.dto.MensajeDTO;
import com.prubamaickol.mutantes.dto.MutanteStatsDTO;
import com.prubamaickol.mutantes.dto.RequestDTO;
import com.prubamaickol.mutantes.entity.Mutante;
import com.prubamaickol.mutantes.service.MutanteService;
import com.prubamaickol.mutantes.util.Utilitarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/prueba")
@CrossOrigin(origins = "*")
public class MutanteController {

    @Autowired
    MutanteService mutanteService;

    @Autowired
    Utilitarios utilitarios;

    public MutanteController(MutanteService mutanteService, Utilitarios utilitarios) {
        this.mutanteService = mutanteService;
        this.utilitarios = utilitarios;

    }

    @PostMapping("/mutant")
    public ResponseEntity<MensajeDTO> validaMutante(@RequestBody RequestDTO requestDTO) {
        if (!utilitarios.validaDna(requestDTO.getDna()))
            return new ResponseEntity<>(new MensajeDTO("El dna enviado no es correcto"), HttpStatus.BAD_REQUEST);
        Gson gson = new Gson();
        Mutante mutante = new Mutante();
        mutante.setDna(gson.toJson(requestDTO.getDna()));
        mutante.setMutant(utilitarios.isMutant(requestDTO.getDna()));
        mutanteService.save(mutante);
        if (mutante.isMutant()) {
            return new ResponseEntity<>(new MensajeDTO("Es mutante"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MensajeDTO("No es mutante"), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<MutanteStatsDTO> estadisticas() {
        return new ResponseEntity<>(mutanteService.consultaEstadisticas(), HttpStatus.OK);
    }


}
