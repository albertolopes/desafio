package com.beto.desafio.Services;

import com.beto.desafio.entities.Cargos;
import com.beto.desafio.repository.CargosRepository;
import com.beto.desafio.services.CargosService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CargosServiceTest {

    @SpyBean
    private CargosService service;

    @MockBean
    private CargosRepository repository;

    @Test
    @DisplayName("Deve buscar todos os cargos")
    public void buscarCargos(){
        List<Cargos> list = Arrays.asList(new Cargos());
        Mockito.when(repository.findAll()).thenReturn(list);

        List<Cargos> retorno = service.buscarCargos();
        Assertions.assertThat(retorno).isNotNull();
    }
}