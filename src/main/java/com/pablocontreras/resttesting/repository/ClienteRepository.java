package com.pablocontreras.resttesting.repository;

import com.pablocontreras.resttesting.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
}
