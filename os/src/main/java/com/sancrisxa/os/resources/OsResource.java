package com.sancrisxa.os.resources;

import com.sancrisxa.os.domain.OS;
import com.sancrisxa.os.dtos.OSDTO;
import com.sancrisxa.os.service.OsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/os")
public class OsResource {

    @Autowired
    private OsService osService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OSDTO> findById(@PathVariable Integer id) {
        OSDTO oSdto = new OSDTO(this.osService.findById(id));

        return ResponseEntity.ok().body(oSdto);
    }

    @GetMapping
    public ResponseEntity<List<OSDTO>> findAll() {
        List<OSDTO> osdtos = this.osService.findAll().stream().map(os -> new OSDTO(os)).collect(Collectors.toList());

        return ResponseEntity.ok().body(osdtos);
    }

    @PostMapping
    public ResponseEntity<OSDTO> create(@Valid @RequestBody OSDTO osdto) {
        OS os = this.osService.create(osdto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(os.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
