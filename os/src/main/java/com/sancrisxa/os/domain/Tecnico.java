package com.sancrisxa.os.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tecnico extends Pessoa{

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<OS> list = new ArrayList<>();

    private static final long serialVersionUID = 1L;

    public Tecnico() {
    }

    public Tecnico(Integer id, String nome, String cpf, String tefone) {
        super(id, nome, cpf, tefone);
    }

    public List<OS> getList() {
        return list;
    }

    public void setList(List<OS> list) {
        this.list = list;
    }
}
