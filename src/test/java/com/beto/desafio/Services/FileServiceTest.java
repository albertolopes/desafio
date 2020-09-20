package com.beto.desafio.Services;

import com.beto.desafio.services.FileService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FileServiceTest {

    @SpyBean
    private FileService service;

    @Test
    @DisplayName("Deve salvar um arquivo")
    public void salvarAquivo(){
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",
                "file.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "file file".getBytes());

        String retorno = service.salvarArquivo(mockMultipartFile);
        System.out.println(retorno);
        Assertions.assertThat(retorno).isNotNull();

    }

    @Test
    @DisplayName("Deve excluir um arquivo")
    public void deletarArquivo() throws IOException {
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",
                "file.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "file file".getBytes());

        String caminho = service.salvarArquivo(mockMultipartFile);
        Boolean retorno = service.deletarArquivo(caminho);

        Assertions.assertThat(retorno).isTrue();
    }
}
