package com.pablocontreras.resttesting.controller;

import com.pablocontreras.resttesting.entity.Cliente;
import com.pablocontreras.resttesting.model.mCliente;
import com.pablocontreras.resttesting.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClienteController {
    @Autowired
    @Qualifier("servicio")
    private ClienteService clienteService;

    @PutMapping("/clientes")
    public boolean agregarCliente(@RequestBody @Valid Cliente cliente){
        return clienteService.crear(cliente);
    }
    @GetMapping("/clientes")
    public List<mCliente> getClientes(){
        return clienteService.listar();
    }
}
