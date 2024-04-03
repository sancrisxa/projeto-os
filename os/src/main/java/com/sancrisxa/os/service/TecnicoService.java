package com.sancrisxa.os.service;

import com.sancrisxa.os.domain.Tecnico;
import com.sancrisxa.os.dtos.TecnicoDTO;
import com.sancrisxa.os.exceptions.ObjectNotFoundException;
import com.sancrisxa.os.repositories.TecnicoRepository;
import com.sancrisxa.os.service.exceptions.DataIntegratyViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    public Tecnico findById(Integer id) {

        Optional<Tecnico> tecnicoOptional = this.tecnicoRepository.findById(id);

        return tecnicoOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
    }

    public List<Tecnico> findAll() {
        return this.tecnicoRepository.findAll();
    }

    public Tecnico crate(TecnicoDTO tecnicoDTO) {

        if (this.findByCPF(tecnicoDTO) != null) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }

        Tecnico tecnico = new Tecnico(null, tecnicoDTO.getNome(), tecnicoDTO.getCpf(), tecnicoDTO.getTelefone());
        return tecnicoRepository.save(tecnico);
    }

    private Tecnico findByCPF(TecnicoDTO tecnicoDTO) {

        Tecnico tecnico = this.tecnicoRepository.findByCPF(tecnicoDTO.getCpf());

        if (tecnico != null) {
            return tecnico;
        }

        return null;
    }
}
