package com.sancrisxa.os.domain.enums;

public enum Prioridade {

    BAIXA(0, "BAIXA"),
    MEDIA(1, "MEDIA"),
    ALTA(2, "ALTA");

    private  Integer cod;
    private String descricap;

    Prioridade(Integer cod, String descricap) {
        this.cod = cod;
        this.descricap = descricap;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricap() {
        return descricap;
    }

    public static Prioridade toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Prioridade prioridade : Prioridade.values()) {
            if (cod.equals(prioridade.getCod())) {
                return prioridade;
            }
        }

        throw new IllegalArgumentException("Prioridade Inválida!" + cod);
    }
}
