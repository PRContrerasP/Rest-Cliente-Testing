package com.pablocontreras.resttesting.controller;

import com.pablocontreras.resttesting.exception.ResourceNotFoundException;
import com.pablocontreras.resttesting.model.Cliente;
import com.pablocontreras.resttesting.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClienteController {
    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public List<Cliente> getAllNotes(){
        return clienteRepository.findAll();
    }

    @PostMapping("/clientes")
    public Cliente createCliente(@Valid @RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @GetMapping("/clientes/{id}")
    public Cliente getClienteById(@PathVariable(value = "id") Integer id){
        return clienteRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Cliente","id",id));
    }

    @PutMapping("/clientes/{id}")
    public Cliente updateCliente(@PathVariable(value="id")Integer id,@Valid @RequestBody Cliente clienteDetalle){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Cliente","id",id));
        cliente.setRutCliente(clienteDetalle.getRutCliente());
        cliente.setNombre(clienteDetalle.getNombre());
        cliente.setApellido(clienteDetalle.getApellido());

        Cliente updatedCliente = clienteRepository.save(cliente);
        return updatedCliente;
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable(value = "id") Integer id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Cliente","id",id));
        clienteRepository.delete(cliente);
        return ResponseEntity.ok().build();
    }
}
