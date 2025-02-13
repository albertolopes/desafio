package com.beto.desafio.controllers;

import com.beto.desafio.dto.FuncionarioDTO;
import com.beto.desafio.mapper.FuncionarioMapper;
import com.beto.desafio.services.FuncionarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/funcionario")
@Api("API REST Funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @Autowired
    private FuncionarioMapper mapper;

    @ApiOperation("Adiciona funcionario")
    @PostMapping
    public ResponseEntity<FuncionarioDTO> salvarFuncionario(@Valid @RequestBody FuncionarioDTO dto){
        return ResponseEntity.ok(mapper.toDto(service.salvar(mapper.toEntity(dto))));
    }

    @ApiOperation("Adiciona Arquivo para um funcionario")
    @PostMapping("/arquivo/{id}")
    public ResponseEntity<String> salvarArquivo(@PathVariable Long id,
                                             @RequestParam(name = "file") MultipartFile file){
        return ResponseEntity.ok(service.salvarArquivo(id, file));
    }

    @ApiOperation("Atualiza funcionario")
    @PutMapping
    public ResponseEntity<FuncionarioDTO> atualizarFuncionario(@Valid @RequestBody FuncionarioDTO dto){
        return ResponseEntity.ok(mapper.toDto(service.atualizar(mapper.toEntity(dto))));
    }

    @ApiOperation("Busca funcionario por id")
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> buscarFuncionarioPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(mapper.toDto(service.buscarFuncionario(id).get()));
    }

//    @ApiOperation("Buscar todos os funcionarios")
//    @GetMapping("/{id}")
//    public ResponseEntity<FuncionarioDTO> buscarTodosOsFuncionario(@PathVariable Long id){
//        return ResponseEntity.ok().body(mapper.toDto(service.buscarFuncionario(id).get()));
//    }

    @ApiOperation("Filtra funcionario por status pelos parametros (ATIVO e INATIVO)")
    @GetMapping("/filtro")
    public ResponseEntity<List<FuncionarioDTO>> buscarFuncionario(@RequestParam String status){
        return ResponseEntity.ok().body(mapper.toDto(service.filtrarFuncionario(status)));
    }

    @ApiOperation("Deletar Funcionario")
    @DeleteMapping
    public ResponseEntity<Void> deletarFuncionario(@RequestBody FuncionarioDTO dto){
        service.deletar(mapper.toEntity(dto));
        return ResponseEntity.noContent().build();
    }
}
