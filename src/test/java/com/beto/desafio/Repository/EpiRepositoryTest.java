package com.beto.desafio.Repository;

import com.beto.desafio.entities.Enum.Sexo;
import com.beto.desafio.entities.Enum.StatusEPI;
import com.beto.desafio.entities.Enum.StatusFuncionario;
import com.beto.desafio.entities.Epi;
import com.beto.desafio.entities.Funcionario;
import com.beto.desafio.repository.EpiRepository;
import com.beto.desafio.repository.FuncionarioRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EpiRepositoryTest {

    @Autowired
    private EpiRepository repository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Busca Epi por Id do funcionario")
    public void buscarEpiporFuncionario(){
        criarEpi();
        List<Epi> epi = repository.buscarEpiporFuncionario(Mockito.anyLong());
        Assertions.assertThat(epi).isNotNull();
    }

    private void criarEpi(){
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Nome");
        funcionario.setCpf("66825659090");
        funcionario.setRg("6514782");
        funcionario.setNascimento(Instant.now());
        funcionario.setAtestado("");
        funcionario.setSexo(Sexo.MASCULINO);
        funcionario.setStatusFuncionario(StatusFuncionario.ATIVO);
        funcionario.setStatusEpi(StatusEPI.USA);

        entityManager.persist(funcionario);

        Epi epi = new Epi();
        epi.setCa(1111);
        epi.setFuncionario(funcionario);

        entityManager.persist(epi);
    }

}
