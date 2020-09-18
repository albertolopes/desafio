package com.beto.desafio.dto;

import com.beto.desafio.entities.Enum.Sexo;
import com.beto.desafio.entities.Enum.StatusFuncionario;
import lombok.*;

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

    private String nome;

    private String cpf;

    private String rg;

    private Instant nascimento;

    private String atestado;

    private Sexo sexo;

    private StatusFuncionario statusFuncionario;

    private CargosDTO cargos;

}
