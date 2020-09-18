package com.beto.desafio.entities;

import com.beto.desafio.entities.Enum.Sexo;
import com.beto.desafio.entities.Enum.StatusFuncionario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "TB_FUNCIONARIO")
public class Funcionario implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FUNCIONARIO_ID")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "RG")
    private String rg;

    @Column(name = "NASCIMENTO")
    private Instant nascimento;

    @Column(name = "ATESTADO")
    private String atestado;

    @Column(name = "SEXO")
    private Sexo sexo;

    @Column(name = "STATUS_FUNCIONARIO")
    private StatusFuncionario statusFuncionario;

    @OneToOne
    @JoinColumn(name = "CARGOS_ID")
    private Cargos cargos;

    @JsonIgnore
    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<Epi> epis = new ArrayList<>();


}
