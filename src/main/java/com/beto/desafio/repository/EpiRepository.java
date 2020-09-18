package com.beto.desafio.repository;

import com.beto.desafio.entities.Epi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpiRepository extends JpaRepository<Epi, Long> {

    @Query("select e from Epi e where e.funcionario.id = :id ")
    List<Epi> buscarEpiporFuncionario(Long id);
}
