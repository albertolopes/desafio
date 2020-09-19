package com.beto.desafio.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    @Value("${contato.disco.raiz}")
    private String raiz;

    @Value("${contato.disco.diretorio-files}")
    private String diretorioFotos;

    public String salvarArquivo(MultipartFile arquivo){

        Path diretorioPath = Paths.get(raiz, diretorioFotos);
        Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());

        try {
            Files.createDirectories(diretorioPath);
            arquivo.transferTo(arquivoPath.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
        }

        return raiz + diretorioFotos + "/" + arquivo.getOriginalFilename();
    }

    public Boolean deletarArquivo(String caminho){
        try {
            Boolean delete = Files.deleteIfExists(Path.of(caminho));
            return delete;
        } catch (IOException e) {
            throw new RuntimeException("Problemas na tentativa de deletar arquivo.", e);
        }
    }

}
