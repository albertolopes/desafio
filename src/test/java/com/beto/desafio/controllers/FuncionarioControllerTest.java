package com.beto.desafio.controllers;

import com.beto.desafio.entities.Cargos;
import com.beto.desafio.entities.Enum.Sexo;
import com.beto.desafio.entities.Enum.StatusEPI;
import com.beto.desafio.entities.Enum.StatusFuncionario;
import com.beto.desafio.entities.Epi;
import com.beto.desafio.entities.Funcionario;
import com.beto.desafio.mapper.FuncionarioMapper;
import com.beto.desafio.repository.FuncionarioRepository;
import com.beto.desafio.services.FileService;
import com.beto.desafio.services.FuncionarioService;
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
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FuncionarioControllerTest {

    @MockBean
    private FuncionarioService service;

    @MockBean
    private FileService fileService;

    @Autowired
    private MockMvc mockMvc;

    static final String API = "/funcionario";
    static final MediaType JSON = MediaType.APPLICATION_JSON;
    static final String CPF = "66825659090";
    static final String RG = "445885579";


    @Test
    @DisplayName("Deve salvar um funcionario")
    public void salvarFuncionario() throws Exception{
        String json = objectMapper().writeValueAsString(criarFuncionario());

        mockMvc.perform(MockMvcRequestBuilders
                .post(API)
                .accept(JSON)
                .contentType(JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deve atualizar funcionario")
    public void atualizarFuncionario() throws Exception{
        Funcionario funcionario = criarFuncionario();
        funcionario.setId(1l);

        String json = objectMapper().writeValueAsString(funcionario);

        mockMvc.perform(MockMvcRequestBuilders
                .post(API)
                .accept(JSON)
                .contentType(JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deve buscar funcionario por Id")
    public void buscarFuncionarioId() throws Exception{
        Funcionario funcionario = criarFuncionario();
        funcionario.setId(1l);

        Mockito.when(service.buscarFuncionario(Mockito.anyLong())).thenReturn(Optional.of(funcionario));

        mockMvc.perform(MockMvcRequestBuilders
                .get(API.concat("/" + Mockito.anyLong()))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deve buscar funcionario por filtro")
    public void filtrarFuncionario() throws Exception{
        List<Funcionario> funcionario = Arrays.asList(criarFuncionario());

        Mockito.when(service.filtrarFuncionario(Mockito.anyString())).thenReturn(funcionario);

        mockMvc.perform(MockMvcRequestBuilders
                .get(API.concat("/filtro?status=" + StatusFuncionario.ATIVO))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deve deletar funcionario")
    public void deletarFuncionario() throws Exception{
        Funcionario funcionario = criarFuncionario();
        funcionario.setId(1l);

        Mockito.doNothing().when(service).deletar(Mockito.any(Funcionario.class));

        String json = objectMapper().writeValueAsString(funcionario);

        mockMvc.perform(MockMvcRequestBuilders
                .delete(API)
                .accept(JSON)
                .contentType(JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @DisplayName("Deve inserir um arquivo para o funcionario")
    public void salvarArquivo() throws Exception{

        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",
                "file.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "file file".getBytes());

        Mockito.when(service.salvarArquivo(1l, mockMultipartFile)).thenReturn("");

        mockMvc.perform(MockMvcRequestBuilders
                .multipart(API.concat("/arquivo/" + 1l))
                .file(mockMultipartFile)
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    public static Funcionario criarFuncionario(){
        Cargos cargos = new Cargos();
        cargos.setId(1l);
        cargos.setDescricao("Descricao");

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Nome");
        funcionario.setCpf(CPF);
        funcionario.setRg(RG);
        funcionario.setNascimento(Instant.now());
        funcionario.setAtestado("");
        funcionario.setSexo(Sexo.MASCULINO);
        funcionario.setStatusFuncionario(StatusFuncionario.ATIVO);
        funcionario.setCargos(cargos);
        funcionario.setStatusEpi(StatusEPI.USA);

        return funcionario;
    }

    public static ObjectMapper objectMapper(){
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
