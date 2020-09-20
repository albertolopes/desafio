package com.beto.desafio.Services;

import com.beto.desafio.entities.Atividades;
import com.beto.desafio.repository.AtividadesRepository;
import com.beto.desafio.services.AtividadesService;
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
public class AtividadesServiceTest {

    @SpyBean
    private AtividadesService service;

    @MockBean
    private AtividadesRepository repository;

    @Test
    @DisplayName("Deve buscar todas as atividades")
    public void buscarAtividade(){
        List<Atividades> list = Arrays.asList(new Atividades());
        Mockito.when(repository.findAll()).thenReturn(list);

        List<Atividades> retorno = service.buscarAtividade();
        Assertions.assertThat(retorno).isNotNull();
    }
}
