package com.beto.desafio.controllers;

import com.beto.desafio.dto.AtividadesDTO;
import com.beto.desafio.mapper.AtividadesMapper;
import com.beto.desafio.services.AtividadesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/atividade")
@Api("API REST ATIVIDADE")
public class AtividadesController {

    @Autowired
    private AtividadesService service;

    @Autowired
    private AtividadesMapper mapper;

    @GetMapping
    @ApiOperation("Busca Atividades")
    public ResponseEntity<List<AtividadesDTO>> buscarAtividade(){
        return ResponseEntity.ok(mapper.toDto(service.buscarAtividade()));
    }
}
