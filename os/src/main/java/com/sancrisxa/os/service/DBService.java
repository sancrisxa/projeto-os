package com.sancrisxa.os.service;

import com.sancrisxa.os.domain.Cliente;
import com.sancrisxa.os.domain.OS;
import com.sancrisxa.os.domain.Tecnico;
import com.sancrisxa.os.domain.enums.Prioridade;
import com.sancrisxa.os.domain.enums.Status;
import com.sancrisxa.os.repositories.ClienteRepository;
import com.sancrisxa.os.repositories.OSRepository;
import com.sancrisxa.os.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private OSRepository osRepository;

    public void instaciaDB() {

        Tecnico t1 = new Tecnico(null, "Valdir Cezar", "263.443.150-32", "(88) 98888-8888");
        Tecnico t2 = new Tecnico(null, "Linus Torvalds", "641.760.040-88", "(88) 94545-4545");
        Cliente c1 = new Cliente(null, "Betina Campos", "293.262.810-00", "(77) 97777-7777");

        OS os1 = new OS(null, Prioridade.ALTA, "Create OS", Status.ANDAMENTO, t1, c1);

        t1.getList().add(os1);
        c1.getList().add(os1);

        tecnicoRepository.saveAll(Arrays.asList(t1, t2));
        clienteRepository.saveAll(Arrays.asList(c1));
        osRepository.saveAll(Arrays.asList(os1));
    }
}
