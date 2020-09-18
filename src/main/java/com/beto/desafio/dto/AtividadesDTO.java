package com.beto.desafio.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AtividadesDTO implements Serializable {
    public static final long serialVersionUID = 1L;

    private Long id;

    private String descricao;

}