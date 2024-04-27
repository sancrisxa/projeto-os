package com.sancrisxa.os.resources;

import com.sancrisxa.os.domain.Cliente;
import com.sancrisxa.os.dtos.ClienteDTO;
import com.sancrisxa.os.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
        Cliente cliente = this.clienteService.findById(id);
        ClienteDTO clienteDTO = new ClienteDTO(cliente);
        return ResponseEntity.ok().body(clienteDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {

        List<ClienteDTO> listDTO = this.clienteService.findAll().stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());

        /*
            List<Cliente> list = this.clienteService.findAll();
            List<ClienteDTO> listDTO = new ArrayList<>();

            for (Cliente cliente : list) {
                listDTO.add(new ClienteDTO(cliente));
            }

            list.forEach(cliente -> listDTO.add(new ClienteDTO(cliente)));
        */

        return ResponseEntity.ok().body(listDTO);

    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = this.clienteService.crate(clienteDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteDTOUpdated = new ClienteDTO(this.clienteService.update(id, clienteDTO));

        return ResponseEntity.ok().body(clienteDTOUpdated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.clienteService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
