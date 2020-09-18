package com.beto.desafio.dto;

import com.beto.desafio.entities.Enum.Sexo;
import com.beto.desafio.entities.Enum.StatusFuncionario;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FuncionarioDTO implements Serializable {
    public static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty
    private String nome;

    @CPF(message = "Cpf inválido")
    private String cpf;

    @NotEmpty
    private String rg;

    @NotNull
    private Instant nascimento;

    private String atestado;

    @NotNull
    private Sexo sexo;

    @NotNull
    private StatusFuncionario statusFuncionario;

    @NotNull
    private CargosDTO cargos;

}
