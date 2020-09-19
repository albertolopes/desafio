package com.beto.desafio.services;

import com.beto.desafio.entities.Epi;
import com.beto.desafio.exceptions.ObjectNotFoundException;
import com.beto.desafio.repository.EpiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpiService {

    @Autowired
    private EpiRepository epiRepository;

    public Epi salvar(Epi epi) {
        return epiRepository.save(epi);
    }

    public Epi atualizar(Epi epi) {
        if(!epiRepository.findById(epi.getId()).isPresent()){
            throw new ObjectNotFoundException("Epi não encontrado");
        }
        return epiRepository.save(epi);
    }

    public List<Epi> buscarEpiporFuncionario(Long id) {
        List<Epi> epis = epiRepository.buscarEpiporFuncionario(id);
        if(epis.isEmpty()){
            throw new ObjectNotFoundException("Epi não encontrado");
        }
        return epis;
    }

    public void deletar(Epi epi) {
        if(!epiRepository.findById(epi.getId()).isPresent()){
            throw new ObjectNotFoundException("Epi não encontrado");
        }
        epiRepository.delete(epi);
    }
}
