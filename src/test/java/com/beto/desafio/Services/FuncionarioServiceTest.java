package com.beto.desafio.Services;

import com.beto.desafio.entities.Enum.Sexo;
import com.beto.desafio.entities.Enum.StatusFuncionario;
import com.beto.desafio.entities.Funcionario;
import com.beto.desafio.repository.FuncionarioRepository;
import com.beto.desafio.services.FileService;
import com.beto.desafio.services.FuncionarioService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FuncionarioServiceTest {

    @SpyBean
    private FuncionarioService service;

    @MockBean
    private FileService fileService;

    @MockBean
    private FuncionarioRepository repository;

    static final String CPF = "66825659090";
    static final String RG = "445885579";

    @Test
    @DisplayName("Deve salvar um funcionario")
    public void salvar(){
        Funcionario obj = criarFuncionario();
        Mockito.when(repository.save(Mockito.any(Funcionario.class))).thenReturn(obj);

        Funcionario resposta = service.salvar(new Funcionario());

        Assertions.assertThat(resposta).isNotNull();
    }

    @Test
    @DisplayName("Deve atualizar um funcionario")
    public void atualizar(){
        Funcionario obj = criarFuncionario();

        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(obj));
        Mockito.when(repository.save(Mockito.any(Funcionario.class))).thenReturn(obj);

        Funcionario resposta = service.atualizar(obj);

        Assertions.assertThat(resposta).isNotNull();
    }

    @Test
    @DisplayName("Deve filtrar funcionarios")
    public void filtrarFuncionario(){
        List<Funcionario> list = Arrays.asList(criarFuncionario());
        Mockito.when(repository.findFuncionarioByStatus(StatusFuncionario.ATIVO)).thenReturn(list);

        List<Funcionario> resposta = service.filtrarFuncionario("ATIVO");

        Assertions.assertThat(resposta).isNotNull();
    }

    @Test
    @DisplayName("Deve deletar funcionario")
    public void deletarFuncionario(){
        Funcionario obj = criarFuncionario();
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(obj));
        Mockito.when(fileService.deletarArquivo(anyString())).thenReturn(true);

        service.deletar(obj);

        verify(repository).delete(obj);
    }

    @Test
    @DisplayName("Deve salvar arquivo")
    public void salvarArquivo() throws IOException {
        Funcionario obj = criarFuncionario();
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "foo",
                "foo.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello World".getBytes());

        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(obj));

        String resposta = service.salvarArquivo(1l, mockMultipartFile);
    }

    private Funcionario criarFuncionario(){
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1l);
        funcionario.setNome("Nome");
        funcionario.setCpf(CPF);
        funcionario.setRg(RG);
        funcionario.setNascimento(Instant.now());
        funcionario.setAtestado("");
        funcionario.setSexo(Sexo.MASCULINO);
        funcionario.setStatusFuncionario(StatusFuncionario.ATIVO);

        return funcionario;
    }
}
