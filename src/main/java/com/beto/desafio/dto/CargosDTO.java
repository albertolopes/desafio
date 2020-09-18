package com.beto.desafio.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CargosDTO implements Serializable {
    public static final long serialVersionUID = 1L;

    private Long id;

    private String descricao;
}
