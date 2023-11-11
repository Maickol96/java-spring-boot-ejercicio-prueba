package com.prubamaickol.mutantes.repository;

import com.prubamaickol.mutantes.entity.Mutante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MutanteRepository extends JpaRepository<Mutante, String> {
    double countByIsMutantTrue();

    double countByIsMutantFalse();
}
