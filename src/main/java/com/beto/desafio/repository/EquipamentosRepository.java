package com.beto.desafio.repository;

import com.beto.desafio.entities.Equipamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentosRepository extends JpaRepository<Equipamentos, Long> {

}
