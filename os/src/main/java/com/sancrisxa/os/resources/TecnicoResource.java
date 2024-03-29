package com.sancrisxa.os.resources;

import com.sancrisxa.os.domain.Tecnico;
import com.sancrisxa.os.dtos.TecnicoDTO;
import com.sancrisxa.os.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        Tecnico tecnico = this.tecnicoService.findById(id);
        TecnicoDTO tecnicoDTO = new TecnicoDTO(tecnico);
        return ResponseEntity.ok().body(tecnicoDTO);
    }
}
