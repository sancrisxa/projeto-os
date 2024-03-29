package com.sancrisxa.os.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
public abstract class Pessoa implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @CPF
    private String cpf;
    private String tefone;

    public Pessoa() {
    }

    public Pessoa(Integer id, String nome, String cpf, String tefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.tefone = tefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTefone() {
        return tefone;
    }

    public void setTefone(String tefone) {
        this.tefone = tefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id) && Objects.equals(nome, pessoa.nome) && Objects.equals(cpf, pessoa.cpf) && Objects.equals(tefone, pessoa.tefone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpf, tefone);
    }
}
