package com.sancrisxa.os.domain;

public class Cliente extends Pessoa{
    public Cliente() {
    }

    public Cliente(Integer id, String nome, String cpf, String tefone) {
        super(id, nome, cpf, tefone);
    }
}
