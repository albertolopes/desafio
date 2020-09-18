package com.beto.desafio.repository;

import com.beto.desafio.entities.Epi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpiRepository extends JpaRepository<Epi, Long> {
}
