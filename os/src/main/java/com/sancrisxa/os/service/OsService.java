package com.sancrisxa.os.service;

import com.sancrisxa.os.domain.OS;
import com.sancrisxa.os.exceptions.ObjectNotFoundException;
import com.sancrisxa.os.repositories.OSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OsService {

    @Autowired
    private OSRepository osRepository;

    public OS findById(Integer id) {
        Optional<OS> osOptional = osRepository.findById(id);

        return osOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id + ", Tipo: " + OS.class.getName()));
    }
}
