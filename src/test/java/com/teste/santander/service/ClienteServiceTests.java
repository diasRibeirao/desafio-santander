package com.teste.santander.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.teste.santander.domain.Cliente;
import com.teste.santander.service.exceptions.ObjectNotFoundException;

@SpringBootTest()
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
public class ClienteServiceTests {

	private static final Long ID_TESTE = Long.valueOf(5);

	@Autowired
	ClienteService service;

	@Test
	@Order(1)
	@DisplayName("Deve retornar registros ao buscar todos os clientes")
	public void deveRetornarVazioTodosOsClientes() {
		List<Cliente> clientes = service.findAll();
		assertFalse(clientes.isEmpty(), "A lista não deve estar vazia");
	}

	@Test
	@Order(2)
	@DisplayName("Deve inserir um cliente")
	public void deveInserirCliente() {
		Cliente cliente = service
				.insert(new Cliente("Pessoa do Teste", false, BigDecimal.valueOf(9000.20), "002", new Date()));
		assertThat(cliente.getId()).isEqualTo(ID_TESTE);

	}

	@Test
	@Order(3)
	@DisplayName("Buscar um cliente")
	public void deveBuscarUmCliente() {
		Cliente cliente = service.findById(ID_TESTE);
		assertThat(cliente.getId()).isEqualTo(ID_TESTE);
	}

	@Test
	@Order(4)
	@DisplayName("Deve atualizar um cliente")
	public void deveAtualizarCliente() {
		Cliente cliente = service.findById(ID_TESTE);
		cliente.setNome("Pessoa Outro Nome");
		service.update(cliente);

		cliente = service.findById(ID_TESTE);
		assertThat(cliente.getNome()).isEqualTo("Pessoa Outro Nome");
	}

	@Test
	@Order(5)
	@DisplayName("Deve excluir um cliente")
	public void deveDeletar() {
		service.delete(ID_TESTE);

		try {
			service.findById(ID_TESTE);
		} catch (ObjectNotFoundException e) {
			assertEquals("Objeto não encontrado! Id: 5, Tipo: com.teste.santander.domain.Cliente", e.getMessage());
			
		}
	}
}
