package com.sancrisxa.os.service;

import com.sancrisxa.os.domain.Pessoa;
import com.sancrisxa.os.domain.Tecnico;
import com.sancrisxa.os.dtos.TecnicoDTO;
import com.sancrisxa.os.exceptions.ObjectNotFoundException;
import com.sancrisxa.os.repositories.PessoaRepository;
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

    @Autowired
    private PessoaRepository pessoaRepository;

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

    public Tecnico update(Integer id, TecnicoDTO tecnicoDTO) {
        Tecnico tecnico = this.findById(id);

        if (this.findByCPF(tecnicoDTO) != null && this.findByCPF(tecnicoDTO).getId() != id) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }

        tecnico.setNome(tecnicoDTO.getNome());
        tecnico.setCpf(tecnicoDTO.getCpf());
        tecnico.setTelefone(tecnicoDTO.getTelefone());

        return this.tecnicoRepository.save(tecnico);
    }

    public void delete(Integer id) {
        Tecnico tecnico = this.findById(id);

        if (tecnico.getList().size() > 0) {
            throw new DataIntegratyViolationException("Tecnico de serviço possui ordem de serviços, e não pode ser deletado.");
        }

        this.tecnicoRepository.deleteById(id);
    }

    private Pessoa findByCPF(TecnicoDTO tecnicoDTO) {

        Pessoa pessoa = this.pessoaRepository.findByCPF(tecnicoDTO.getCpf());

        if (pessoa != null) {
            return pessoa;
        }

        return null;
    }


}
