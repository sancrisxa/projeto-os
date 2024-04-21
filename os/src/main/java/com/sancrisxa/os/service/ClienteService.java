package com.sancrisxa.os.service;

import com.sancrisxa.os.domain.Cliente;
import com.sancrisxa.os.domain.Pessoa;
import com.sancrisxa.os.dtos.ClienteDTO;
import com.sancrisxa.os.exceptions.ObjectNotFoundException;
import com.sancrisxa.os.repositories.ClienteRepository;
import com.sancrisxa.os.repositories.PessoaRepository;
import com.sancrisxa.os.service.exceptions.DataIntegratyViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {

        Optional<Cliente> clienteOptional = this.clienteRepository.findById(id);

        return clienteOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        return this.clienteRepository.findAll();
    }

    public Cliente crate(ClienteDTO clienteDTO) {

        if (this.findByCPF(clienteDTO) != null) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }

        Cliente cliente = new Cliente(null, clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getTelefone());
        return clienteRepository.save(cliente);
    }

    public Cliente update(Integer id, ClienteDTO clienteDTO) {
        Cliente cliente = this.findById(id);

        if (this.findByCPF(clienteDTO) != null && this.findByCPF(clienteDTO).getId() != id) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }

        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setTelefone(clienteDTO.getTelefone());

        return this.clienteRepository.save(cliente);
    }

    public void delete(Integer id) {
         Cliente cliente = this.findById(id);

        if (cliente.getList().size() > 0) {
            throw new DataIntegratyViolationException("Cliente de serviço possui ordem de serviços, e não pode ser deletado.");
        }

        this.clienteRepository.deleteById(id);
    }

    private Pessoa findByCPF(ClienteDTO clienteDTO) {

        Pessoa pessoa = this.pessoaRepository.findByCPF(clienteDTO.getCpf());

        if (pessoa != null) {
            return pessoa;
        }

        return null;
    }


}
