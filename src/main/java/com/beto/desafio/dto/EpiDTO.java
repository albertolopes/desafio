package com.beto.desafio.dto;

import com.beto.desafio.entities.Atividades;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EpiDTO implements Serializable {
    public static final long serialVersionUID = 1L;

    private Long id;

    private AtividadesDTO atividades;

    private Integer ca;

    private FuncionarioDTO funcionario;

    private EquipamentosDTO Equipamentos;
}
