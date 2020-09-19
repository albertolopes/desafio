package com.beto.desafio.services;

import com.beto.desafio.entities.Atividades;
import com.beto.desafio.repository.AtividadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtividadesService {

    @Autowired
    private AtividadesRepository repository;

    public List<Atividades> buscarAtividade() {
        return  repository.findAll();
    }
}
