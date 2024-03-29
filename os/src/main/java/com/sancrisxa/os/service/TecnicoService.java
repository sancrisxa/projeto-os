package com.sancrisxa.os.service;

import com.sancrisxa.os.domain.Tecnico;
import com.sancrisxa.os.exceptions.ObjectNotFoundException;
import com.sancrisxa.os.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    public Tecnico findById(Integer id) {

        Optional<Tecnico> tecnicoOptional = this.tecnicoRepository.findById(id);

        return tecnicoOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
    }

}