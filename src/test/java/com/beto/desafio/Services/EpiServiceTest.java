package com.beto.desafio.Services;

import com.beto.desafio.entities.Atividades;
import com.beto.desafio.entities.Enum.Sexo;
import com.beto.desafio.entities.Enum.StatusFuncionario;
import com.beto.desafio.entities.Epi;
import com.beto.desafio.entities.Equipamentos;
import com.beto.desafio.entities.Funcionario;
import com.beto.desafio.repository.EpiRepository;
import com.beto.desafio.services.EpiService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EpiServiceTest {

    @SpyBean
    private EpiService service;

    @MockBean
    private EpiRepository repository;

    @Test
    @DisplayName("Deve salvar Epi")
    public void salvar(){
        Epi epi = criarEpi();
        Mockito.when(repository.save(Mockito.any(Epi.class))).thenReturn(epi);

        Epi retorno = service.salvar(new Epi());
        Assertions.assertThat(retorno).isNotNull();
    }

    @Test
    @DisplayName("Deve atualizar Epi")
    public void atualizar(){
        Epi epi = criarEpi();

        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(epi));
        Mockito.when(repository.save(Mockito.any(Epi.class))).thenReturn(epi);

        Epi retorno = service.atualizar(epi);
        Assertions.assertThat(retorno).isNotNull();
    }

    @Test
    @DisplayName("Busca epi pelo id do funcionario")
    public void buscarEpiPorFuncionario(){
        List<Epi> list = Arrays.asList(criarEpi());
        Mockito.when(repository.buscarEpiporFuncionario(Mockito.anyLong())).thenReturn(list);

        List<Epi> retorno = service.buscarEpiporFuncionario(1l);
        Assertions.assertThat(retorno).isNotNull();
    }

    @Test
    @DisplayName("Deve deletar epi do funcionario")
    public void deletarFuncionario(){
        Epi epi = criarEpi();
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(epi));

        service.deletar(epi);

        verify(repository).delete(epi);
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
}
