package com.beto.desafio.repository;

import com.beto.desafio.entities.Cargos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargosRepository extends JpaRepository<Cargos, Long> {
}
