package com.beto.desafio.dto;

import com.beto.desafio.entities.Atividades;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EpiDTO implements Serializable {
    public static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private AtividadesDTO atividades;

    @NotNull
    private Integer ca;

    @NotNull
    private FuncionarioDTO funcionario;

    @NotNull
    private EquipamentosDTO Equipamentos;
}
