package com.beto.desafio.dto;

import com.beto.desafio.entities.Enum.Sexo;
import com.beto.desafio.entities.Enum.StatusEPI;
import com.beto.desafio.entities.Enum.StatusFuncionario;
import com.beto.desafio.entities.Epi;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",
            timezone = "GMT")
    @NotNull
    private Instant nascimento;

    private String atestado;

    @NotNull
    private Sexo sexo;

    @NotNull
    private StatusFuncionario statusFuncionario;

    @NotNull
    private StatusEPI statusEpi;

    @NotNull
    private CargosDTO cargos;

    private List<Epi> epis = new ArrayList<>();

}
