package com.pablocontreras.resttesting.converter;

import com.pablocontreras.resttesting.entity.Cliente;
import com.pablocontreras.resttesting.model.mCliente;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("convertidor")
public class Convertidor {
    public List<mCliente> convertirLista(List<Cliente> clientes){
        List<mCliente> mClientes = new ArrayList<>();
        for(Cliente cliente: clientes){
            mClientes.add(new mCliente(cliente));
        }
        return mClientes;
    }
}
