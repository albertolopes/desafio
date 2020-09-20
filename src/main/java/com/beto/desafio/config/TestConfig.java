package com.beto.desafio.config;

import com.beto.desafio.entities.Atividades;
import com.beto.desafio.entities.Cargos;
import com.beto.desafio.entities.Equipamentos;
import com.beto.desafio.repository.AtividadesRepository;
import com.beto.desafio.repository.CargosRepository;
import com.beto.desafio.repository.EquipamentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("dev")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private AtividadesRepository atividadesRepository;

    @Autowired
    private CargosRepository cargosRepository;

    @Autowired
    private EquipamentosRepository equipamentosRepository;

    @Override
    public void run(String... args) throws Exception {
        Atividades ativ1 = new Atividades(null, "Atividade 1");
        Atividades ativ2 = new Atividades(null, "Atividade 2");
        Atividades ativ3 = new Atividades(null, "Atividade 3");
        atividadesRepository.saveAll(Arrays.asList(ativ1, ativ2, ativ3));

        Equipamentos equipamentos1 = new Equipamentos(null, "Equipamentos 1");
        Equipamentos equipamentos2 = new Equipamentos(null, "Equipamentos 2");
        Equipamentos equipamentos3 = new Equipamentos(null, "Equipamentos 3");
        equipamentosRepository.saveAll(Arrays.asList(equipamentos1, equipamentos2, equipamentos3));

        Cargos cargos1 = new Cargos(null, "Cargo 1");
        Cargos cargos2 = new Cargos(null, "Cargo 2");
        Cargos cargos3 = new Cargos(null, "Cargo 3");
        cargosRepository.saveAll(Arrays.asList(cargos1, cargos2, cargos3));
    }
}
