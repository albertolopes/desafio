package com.beto.desafio.repository;

import com.beto.desafio.entities.Atividades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadesRepository extends JpaRepository<Atividades, Long> {
}
