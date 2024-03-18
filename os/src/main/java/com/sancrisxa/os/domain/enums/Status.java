package com.sancrisxa.os.domain.enums;

public enum Status {

    ABERTO(0, "ABERTO"),
    ANDAMENTO(1, "ANDAMENTO"),
    ENCERRADO(2, "ENCERRAD");

    private  Integer cod;
    private String descricap;

    Status(Integer cod, String descricap) {
        this.cod = cod;
        this.descricap = descricap;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricap() {
        return descricap;
    }

    public static Status toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Status status : Status.values()) {
            if (cod.equals(status.getCod())) {
                return status;
            }
        }

        throw new IllegalArgumentException("Status Inválida!" + cod);
    }


}
