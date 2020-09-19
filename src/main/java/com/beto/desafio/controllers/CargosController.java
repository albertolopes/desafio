package com.beto.desafio.controllers;

import com.beto.desafio.dto.CargosDTO;
import com.beto.desafio.mapper.CargosMapper;
import com.beto.desafio.services.CargosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/cargos")
@Api("API REST CARGOS")
public class CargosController {

    @Autowired
    private CargosService service;

    @Autowired
    private CargosMapper mapper;

    @GetMapping
    @ApiOperation("Busca Cargos")
    public ResponseEntity<List<CargosDTO>> buscarCargos(){
        return ResponseEntity.ok(mapper.toDto(service.buscarCargos()));
    }
}
