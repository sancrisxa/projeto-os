package com.sancrisxa.os.resources;

import com.sancrisxa.os.domain.Tecnico;
import com.sancrisxa.os.dtos.TecnicoDTO;
import com.sancrisxa.os.service.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
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

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {

        List<TecnicoDTO> listDTO = this.tecnicoService.findAll().stream().map(tecnico -> new TecnicoDTO(tecnico)).collect(Collectors.toList());

        /*
            List<Tecnico> list = this.tecnicoService.findAll();
            List<TecnicoDTO> listDTO = new ArrayList<>();

            for (Tecnico tecnico : list) {
                listDTO.add(new TecnicoDTO(tecnico));
            }

            list.forEach(tecnico -> listDTO.add(new TecnicoDTO(tecnico)));
        */

        return ResponseEntity.ok().body(listDTO);

    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecnicoDTO) {
        Tecnico tecnico = this.tecnicoService.crate(tecnicoDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(tecnico.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO tecnicoDTO) {
        TecnicoDTO tecnicoDTOUpdated = new TecnicoDTO(this.tecnicoService.update(id, tecnicoDTO));

        return ResponseEntity.ok().body(tecnicoDTOUpdated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.tecnicoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
