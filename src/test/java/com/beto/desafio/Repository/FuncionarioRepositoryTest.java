package com.beto.desafio.Repository;

import com.beto.desafio.entities.Enum.Sexo;
import com.beto.desafio.entities.Enum.StatusFuncionario;
import com.beto.desafio.entities.Funcionario;
import com.beto.desafio.repository.FuncionarioRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class FuncionarioRepositoryTest {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    static final String CPF = "66825659090";
    static final String RG = "445885579";

    @Test
    @DisplayName("Deve buscar um funcionario por status")
    public void BuscarUsuarioPorStatus(){
        criarFuncionario();
        List<Funcionario> funcionarios = repository.findFuncionarioByStatus(StatusFuncionario.ATIVO);
        Assertions.assertThat(funcionarios).isNotNull();
    }

    @Test
    @DisplayName("Buscar um funcionario por Cpf")
    public void BuscarUsuarioPorCpf(){
        criarFuncionario();
        Funcionario funcionarios = repository.buscarFuncionarioPorCpf(CPF);
        Assertions.assertThat(funcionarios).isNotNull();
    }

    @Test
    @DisplayName("Buscar um funcionario por Cpf")
    public void BuscarUsuarioPorRg(){
        criarFuncionario();
        Funcionario funcionarios = repository.buscarFuncionarioPorRg(RG);
        Assertions.assertThat(funcionarios).isNotNull();
    }

    private void criarFuncionario(){
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Nome");
        funcionario.setCpf(CPF);
        funcionario.setRg(RG);
        funcionario.setNascimento(Instant.now());
        funcionario.setAtestado("");
        funcionario.setSexo(Sexo.MASCULINO);
        funcionario.setStatusFuncionario(StatusFuncionario.ATIVO);

        entityManager.persist(funcionario);
    }
}
