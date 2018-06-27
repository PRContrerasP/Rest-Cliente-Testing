package com.pablocontreras.resttesting.service;

import com.pablocontreras.resttesting.converter.Convertidor;
import com.pablocontreras.resttesting.entity.Cliente;
import com.pablocontreras.resttesting.model.mCliente;
import com.pablocontreras.resttesting.repository.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("servicio")
public class ClienteService {
    @Autowired
    @Qualifier("repositorio")
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    public boolean crear(Cliente cliente){
        try {
            clienteRepositorio.save(cliente);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean actualizar(Cliente cliente){
        try {
            clienteRepositorio.save(cliente);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean borrar(long id){
        try {
            Cliente cliente  = clienteRepositorio.findById(id);
            clienteRepositorio.delete(cliente);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean findCliente(long id){
        try {
            Cliente cliente  = clienteRepositorio.findById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }



    public List<mCliente> listar(){
        return convertidor.convertirLista(clienteRepositorio.findAll());
    }
}
