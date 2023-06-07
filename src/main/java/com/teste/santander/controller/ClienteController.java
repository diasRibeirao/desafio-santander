package com.teste.santander.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.teste.santander.domain.Cliente;
import com.teste.santander.domain.dto.ClienteAdicionarDTO;
import com.teste.santander.domain.dto.ClienteAtualizarDTO;
import com.teste.santander.domain.dto.ClienteDTO;
import com.teste.santander.domain.dto.converter.ClienteConverter;
import com.teste.santander.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@Autowired
	private ClienteConverter converter;

	@GetMapping
	@Operation(summary = "Listar todos os clientes")
	public ResponseEntity<List<ClienteDTO>> findAll(@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		List<ClienteDTO> list = converter.Parse(service.findAll());
		return ResponseEntity.ok().body(list);
	}

	@Operation(summary = "Buscar um cliente pelo id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> find(@PathVariable Long id) {
		ClienteDTO obj = converter.Parse(service.findById(id));
		return ResponseEntity.ok().body(obj);
	}

	@Operation(summary = "Adicionar um cliente")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteAdicionarDTO clienteAdicionarDTO) {
		Cliente cliente = converter.ParseAdicionarDTO(clienteAdicionarDTO);
		cliente = service.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@Operation(summary = "Atualizar um cliente")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteAtualizarDTO clienteAtualizarDTO,
			@Valid @PathVariable Long id) {
		Cliente cliente = converter.ParseAtualizarDTO(clienteAtualizarDTO);
		cliente.setId(id);
		cliente = service.update(cliente);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Deletar um cliente")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
