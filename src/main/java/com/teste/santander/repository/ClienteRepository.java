package com.teste.santander.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.santander.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Page<Cliente> findAll(Pageable paginacao);

	Cliente findByNumeroDaConta(String numeroDaConta);

}
