package com.teste.santander.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.santander.domain.Cliente;
import com.teste.santander.repository.ClienteRepository;
import com.teste.santander.service.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente findById(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente insert(Cliente cliente) {
		return repository.save(cliente);
	}

	public Cliente update(Cliente cliente) {
		return repository.save(cliente);
	}

	public void delete(@Valid Long id) {
		repository.deleteById(id);
	}

}
