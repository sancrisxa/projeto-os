package com.sancrisxa.os.dtos;

import com.sancrisxa.os.domain.Tecnico;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serial;
import java.io.Serializable;

public class TecnicoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;

    @CPF
    private String cpf;
    private String tefone;

    public TecnicoDTO() {
    }

    public TecnicoDTO(Tecnico tecnico) {
        this.id = tecnico.getId();
        this.nome = tecnico.getNome();
        this.cpf = tecnico.getCpf();
        this.tefone = tecnico.getTefone();
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
}
