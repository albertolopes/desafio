package com.beto.desafio.repository;

import com.beto.desafio.entities.Enum.StatusFuncionario;
import com.beto.desafio.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query("select f from Funcionario f where f.statusFuncionario = :status")
    List<Funcionario> findFuncionarioByStatus(@Param("status") StatusFuncionario status);

}
