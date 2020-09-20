package com.beto.desafio.controllers;

import com.beto.desafio.entities.Atividades;
import com.beto.desafio.services.AtividadesService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AtividadesControllerTest {

    @SpyBean
    private AtividadesService service;

    @Autowired
    private MockMvc mockMvc;

    static final String API = "/atividades";
    static final MediaType JSON = MediaType.APPLICATION_JSON;

    @Test
    @DisplayName("Busca Atividades")
    public void buscarAtividade() throws Exception {
        List<Atividades> atividades = Arrays.asList(new Atividades());

        Mockito.when(service.buscarAtividade()).thenReturn(atividades);

        mockMvc.perform(MockMvcRequestBuilders
                .get(API)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
