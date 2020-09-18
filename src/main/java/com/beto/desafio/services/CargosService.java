package com.beto.desafio.services;

import com.beto.desafio.entities.Cargos;
import com.beto.desafio.repository.CargosRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CargosService {

    @Autowired
    private CargosRepository repository;

    public List<Cargos> buscarCargos() {
        return repository.findAll();
    }
}
