package com.beto.desafio.entities.Enum;

public enum StatusFuncionario {

    ATIVO(0, "Ativo"),
    INATIVO(1, "Inativo");

    private int cod;
    private String descricao;

    private StatusFuncionario(int cod, String descricao){
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod(){
        return cod;
    }

    public String getDescricao(){
        return descricao;
    }

    public static StatusFuncionario toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (StatusFuncionario x : StatusFuncionario.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
