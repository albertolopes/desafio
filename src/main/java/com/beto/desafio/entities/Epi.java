package com.beto.desafio.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "TB_EPI")
public class Epi implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EPI")
    private Long id;

    @OneToOne
    @JoinColumn(name = "ATIVIDADES_ID")
    private Atividades atividades;

    @Column(name = "CA")
    private Integer ca;

    @ManyToOne
    @JoinColumn(name="FUNCIONARIO_ID")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name="EQUIPAMENTOS_ID")
    private Equipamentos Equipamentos;

}
