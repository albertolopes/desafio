package com.beto.desafio.entities.Enum;

public enum StatusEPI {

    USA(0, "Usa"),
    NAO_USA(1, "Não usa");

    private int cod;
    private String descricao;

    private StatusEPI(int cod, String descricao){
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod(){
        return cod;
    }

    public String getDescricao(){
        return descricao;
    }

    public static Sexo toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (Sexo x : Sexo.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
