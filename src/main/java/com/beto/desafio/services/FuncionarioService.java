package com.beto.desafio.services;

import com.beto.desafio.entities.Enum.StatusFuncionario;
import com.beto.desafio.entities.Funcionario;
import com.beto.desafio.exceptions.IllegalAccessException;
import com.beto.desafio.exceptions.ObjectAlreadyExistsException;
import com.beto.desafio.exceptions.ObjectNotFoundException;
import com.beto.desafio.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private FileService fileService;

    public Funcionario salvar(Funcionario funcionario){
        if(repository.buscarFuncionarioPorCpf(funcionario.getCpf()) != null){
            throw new ObjectAlreadyExistsException("Cpf já cadastrado");
        } else if (repository.buscarFuncionarioPorRg(funcionario.getRg()) != null){
            throw new ObjectAlreadyExistsException("Rg já cadastrado");
        }

        return repository.save(funcionario);
    }

    public Optional<Funcionario> buscarFuncionario(Long id) {
        return repository.findById(id);
    }


    public Funcionario atualizar(Funcionario funcionario) {
        Funcionario objCpf = repository.buscarFuncionarioPorCpf(funcionario.getCpf());
        if(objCpf != null && objCpf.getId() != funcionario.getId()){
            throw new ObjectAlreadyExistsException("Cpf já cadastrado");
        }

        Funcionario objRg = repository.buscarFuncionarioPorRg(funcionario.getRg());
        if (objRg != null && objRg.getId() != funcionario.getId()){
            throw new ObjectAlreadyExistsException("Rg já cadastrado");
        }

        if(!repository.findById(funcionario.getId()).isPresent()){
            throw new ObjectNotFoundException("Funcionario não encontrado");
        }
        return repository.save(funcionario);
    }

    public List<Funcionario> filtrarFuncionario(String status) {
        if(status.equals("ATIVO")){
            List<Funcionario> list = repository.findFuncionarioByStatus(StatusFuncionario.ATIVO);
            return list;
        } else if(status.equals("INATIVO")){
            List<Funcionario> list = repository.findFuncionarioByStatus(StatusFuncionario.INATIVO);
            return list;
        } else if(status.isEmpty()) {
            List<Funcionario> list = repository.findAll();
            return list;
        }
        throw new IllegalAccessException("Parametros devem ser ATIVO, INATIVO ou vazio");
    }

    public void deletar(Funcionario funcionario) {
        Funcionario valid = repository.findById(funcionario.getId()).get();
        if(valid == null){
            throw new ObjectNotFoundException("Funcionario não encontrado");
        }
        fileService.deletarArquivo(valid.getAtestado());
        repository.delete(funcionario);
    }

    public String salvarArquivo(Long id, MultipartFile arquivo){
        Funcionario funcionario = repository.findById(id).get();

        if(funcionario == null){
            throw new ObjectNotFoundException("Funcionario não encontrado");
        }

        String uri = fileService.salvarArquivo(arquivo);
        funcionario.setAtestado(uri);

        repository.save(funcionario);

        return uri;
    }

}
