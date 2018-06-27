package com.pablocontreras.resttesting.repository;

import com.pablocontreras.resttesting.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
@Repository("repositorio")
public interface ClienteRepositorio extends JpaRepository<Cliente,Serializable> {

    public abstract Cliente findById(long id);
    public abstract List<Cliente> findByRut(String rut);
    public abstract Cliente findByApellido(String apellido);
}
