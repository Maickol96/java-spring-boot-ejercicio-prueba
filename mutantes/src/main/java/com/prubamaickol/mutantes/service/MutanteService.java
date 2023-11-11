package com.prubamaickol.mutantes.service;


import com.prubamaickol.mutantes.dto.MutanteStatsDTO;
import com.prubamaickol.mutantes.entity.Mutante;
import com.prubamaickol.mutantes.repository.MutanteRepository;
import com.prubamaickol.mutantes.util.Utilitarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutanteService {

    @Autowired
    MutanteRepository mutanteRepository;

    @Autowired
    Utilitarios utilitarios;

    public Mutante save(Mutante mutante) {
        return mutanteRepository.save(mutante);
    }

    public MutanteStatsDTO consultaEstadisticas() {
        return utilitarios.buildMutantDTO(mutanteRepository.countByIsMutantTrue(), mutanteRepository.countByIsMutantFalse());
    }
}
