package com.sancrisxa.os.service;

import com.sancrisxa.os.domain.Cliente;
import com.sancrisxa.os.domain.OS;
import com.sancrisxa.os.domain.Tecnico;
import com.sancrisxa.os.domain.enums.Prioridade;
import com.sancrisxa.os.domain.enums.Status;
import com.sancrisxa.os.dtos.OSDTO;
import com.sancrisxa.os.exceptions.ObjectNotFoundException;
import com.sancrisxa.os.repositories.OSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OsService {

    @Autowired
    private OSRepository osRepository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public OS findById(Integer id) {
        Optional<OS> osOptional = osRepository.findById(id);

        return osOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id + ", Tipo: " + OS.class.getName()));
    }

    public List<OS> findAll() {
        return this.osRepository.findAll();
    }

    public OS create(OSDTO osdto) {

        OS os = this.fromDTO(osdto);
        return this.osRepository.save(os);
    }

    public OS update(OSDTO osdto) {

        this.findById(osdto.getId());

        OS os = this.fromDTO(osdto);

        return this.osRepository.save(os);
    }

    private OS fromDTO(OSDTO osdto) {
        OS os = new OS();
        os.setId(osdto.getId());
        os.setObservacoes(osdto.getObservacoes());
        os.setPrioridade(Prioridade.toEnum(osdto.getPrioridade()));
        os.setStatus(Status.toEnum(osdto.getStatus()));

        Tecnico tecnico = this.tecnicoService.findById(osdto.getTecnico());
        Cliente cliente = this.clienteService.findById(osdto.getCliente());

        os.setTecnico(tecnico);
        os.setCliente(cliente);

        if (os.getStatus().getCod().equals(2)) {
            os.setDataFechamento(LocalDateTime.now());
        }

        return os;
    }
}
