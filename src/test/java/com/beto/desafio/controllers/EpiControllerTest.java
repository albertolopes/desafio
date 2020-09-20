package com.beto.desafio.controllers;

import com.beto.desafio.entities.Atividades;
import com.beto.desafio.entities.Enum.Sexo;
import com.beto.desafio.entities.Enum.StatusEPI;
import com.beto.desafio.entities.Enum.StatusFuncionario;
import com.beto.desafio.entities.Epi;
import com.beto.desafio.entities.Equipamentos;
import com.beto.desafio.entities.Funcionario;
import com.beto.desafio.services.EpiService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EpiControllerTest {

    @MockBean
    private EpiService service;

    @Autowired
    private MockMvc mockMvc;

    static final String API = "/epi";
    static final MediaType JSON = MediaType.APPLICATION_JSON;

    @Test
    @DisplayName("Deve salvar EPI")
    public void salvarEpi() throws Exception{

        String json = objectMapper().writeValueAsString(criarEpi());

        mockMvc.perform(MockMvcRequestBuilders
                .post(API)
                .accept(JSON)
                .contentType(JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deve atualizar API")
    public void atualizar() throws Exception {

        String json = objectMapper().writeValueAsString(criarEpi());

        mockMvc.perform(MockMvcRequestBuilders
                .put(API)
                .accept(JSON)
                .contentType(JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deve buscar API")
    public void buscarEpi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(API.concat("/1"))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deve deletar funcionario")
    public void deletarFuncionario() throws Exception{

        Mockito.doNothing().when(service).deletar(Mockito.any(Epi.class));

        String json = objectMapper().writeValueAsString(criarEpi());

        mockMvc.perform(MockMvcRequestBuilders
                .delete(API)
                .accept(JSON)
                .contentType(JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    private Epi criarEpi(){

        Funcionario funcionario = new Funcionario();
        funcionario.setId(1l);
        funcionario.setNome("Nome");
        funcionario.setCpf("66825659090");
        funcionario.setRg("6514782");
        funcionario.setNascimento(Instant.now());
        funcionario.setAtestado("");
        funcionario.setSexo(Sexo.MASCULINO);
        funcionario.setStatusFuncionario(StatusFuncionario.ATIVO);
        funcionario.setStatusEpi(StatusEPI.USA);

        Atividades atividades = new Atividades();
        atividades.setId(1l);
        atividades.setDescricao("Descricao");

        Equipamentos equipamentos = new Equipamentos();
        equipamentos.setId(1l);
        equipamentos.setDescricao("Descricao");

        Epi epi = new Epi();
        epi.setId(1l);
        epi.setCa(1111);
        epi.setFuncionario(funcionario);
        epi.setAtividades(atividades);
        epi.setEquipamentos(equipamentos);

        return epi;
    }

    private ObjectMapper objectMapper(){
        ObjectMapper om = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return om;
    }
}
