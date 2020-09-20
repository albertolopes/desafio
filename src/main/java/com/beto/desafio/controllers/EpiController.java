package com.beto.desafio.controllers;

import com.beto.desafio.dto.EpiDTO;
import com.beto.desafio.mapper.EpiMapper;
import com.beto.desafio.services.EpiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/epi")
@Api("API REST EPI")
public class EpiController {

    @Autowired
    private EpiService service;

    @Autowired
    private EpiMapper mapper;

    @ApiOperation("Adicionar Epi")
    @PostMapping
    public ResponseEntity<EpiDTO> salvarEpi(@Valid @RequestBody EpiDTO dto){
        return ResponseEntity.ok(mapper.toDto(service.salvar(mapper.toEntity(dto))));
    }

    @ApiOperation("Atualizar Epi")
    @PutMapping
    public ResponseEntity<EpiDTO> atualizarEpi(@Valid @RequestBody EpiDTO dto){
        return ResponseEntity.ok(mapper.toDto(service.atualizar(mapper.toEntity(dto))));
    }

    @ApiOperation("Busca Epi pelo ID do funcionario")
    @GetMapping("/{id}")
    public ResponseEntity<List<EpiDTO>> buscarEpi(@PathVariable Long id){
        return ResponseEntity.ok(mapper.toDto(service.buscarEpiporFuncionario(id)));
    }

    @ApiOperation("Deletar Epi")
    @DeleteMapping
    public ResponseEntity<Void> deletarEpi(@RequestBody EpiDTO dto){
        service.deletar(mapper.toEntity(dto));
        return ResponseEntity.noContent().build();
    }
}
