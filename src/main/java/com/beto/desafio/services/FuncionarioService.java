package com.beto.desafio.services;

import com.beto.desafio.entities.Enum.StatusFuncionario;
import com.beto.desafio.entities.Funcionario;
import com.beto.desafio.exceptions.IllegalAccessException;
import com.beto.desafio.exceptions.ObjectNotFoundException;
import com.beto.desafio.repository.EpiRepository;
import com.beto.desafio.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Value("${contato.disco.raiz}")
    private String raiz;

    @Value("${contato.disco.diretorio-fotos}")
    private String diretorioFotos;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EpiRepository epiRepository;

    public Funcionario salvar(Funcionario funcionario){
        funcionario = funcionarioRepository.save(funcionario);
        return funcionario;
    }

    public Optional<Funcionario> buscarFuncionario(Long id) {
        return funcionarioRepository.findById(id);
    }

    public List<Funcionario> filtrarFuncionario(String status) {
        if(status.equals("ATIVO")){
            List<Funcionario> list = funcionarioRepository.findFuncionarioByStatus(StatusFuncionario.ATIVO);
            return list;
        } else if(status.equals("INATIVO")){
            List<Funcionario> list = funcionarioRepository.findFuncionarioByStatus(StatusFuncionario.INATIVO);
            return list;
        } else if(status.isEmpty()) {
            List<Funcionario> list = funcionarioRepository.findAll();
            return list;
        }
        throw new IllegalAccessException("Parametros devem ser ATIVO, INATIVO ou vazio");
    }

    public Funcionario atualizar(Funcionario funcionario) {
        if(!funcionarioRepository.findById(funcionario.getId()).isPresent()){
            throw new ObjectNotFoundException("Funcionario não encontrado");
        }
        return funcionarioRepository.save(funcionario);
    }

    public void deletar(Long id) {
        if(!funcionarioRepository.findById(id).isPresent()){
            throw new ObjectNotFoundException("Funcionario não encontrado");
        }
        funcionarioRepository.deleteById(id);
    }

    public String salvarArquivo(Long id, MultipartFile arquivo){

        Path diretorioPath = Paths.get(raiz, diretorioFotos);
        Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());

        try {
            Files.createDirectories(diretorioPath);
            arquivo.transferTo(arquivoPath.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
        }

//        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
//        funcionario.setAtestado(raiz + diretorioFotos + "/" + arquivo.getOriginalFilename());

        return null;
    }
}
