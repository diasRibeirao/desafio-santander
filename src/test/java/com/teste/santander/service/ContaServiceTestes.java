package com.teste.santander.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.teste.santander.service.exceptions.ObjectNotFoundException;
import com.teste.santander.service.exceptions.TransacoesException;

@SpringBootTest()
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
public class ContaServiceTestes {

	private static final Long ID_TESTE = Long.valueOf(1);

	@Autowired
	ContaService service;

	@Test
	@Order(1)
	@DisplayName("Deve ocorrer erro ao sacar com um valor inválido")
	public void deveOcorrerErroAoSacarComValorInvalido() {
		TransacoesException exception = assertThrows(TransacoesException.class, () -> {
			service.sacar(ID_TESTE, BigDecimal.valueOf(0));
		});

		assertAll(() -> assertEquals("Valor para saque inválido", exception.getMessage()));
	}

	@Test
	@Order(2)
	@DisplayName("Deve ocorrer erro ao informar um cliente inválido para saque")
	public void deveOcorrerErroAoInformarClienteInvalidoParaSaque() {
		ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
			service.sacar(11L, BigDecimal.valueOf(10));
		});

		assertAll(() -> assertEquals("Cliente não encontrado! Id: 11", exception.getMessage()));
	}

	@Test
	@Order(3)
	@DisplayName("Deve ocorrer erro de saldo insuficiente")
	public void deveOcorrerErroDeSaldoInsuficiente() {
		TransacoesException exception = assertThrows(TransacoesException.class, () -> {
			service.sacar(ID_TESTE, BigDecimal.valueOf(100000000));
		});

		assertAll(() -> assertEquals("Saldo insuficiente para realizar o saque", exception.getMessage()));
	}

	@Test
	@Order(4)
	@DisplayName("Deve sacar com um valor válido")
	public void deveSacarComValorValido() {
		assertDoesNotThrow(() -> service.sacar(ID_TESTE, BigDecimal.valueOf(10)));
	}

	@Test
	@Order(5)
	@DisplayName("Deve ocorrer erro ao depositar um valor inválido")
	public void deveOcorrerErroAoDepositarValorInvalido() {
		TransacoesException exception = assertThrows(TransacoesException.class, () -> {
			service.depositar(ID_TESTE, BigDecimal.valueOf(0));
		});

		assertAll(() -> assertEquals("Valor para depósito inválido", exception.getMessage()));
	}
	
	@Test
	@Order(6)
	@DisplayName("Deve ocorrer erro ao informar um cliente inválido para depositar")
	public void deveOcorrerErroAoInformarClienteInvalidoParaDepositar() {
		ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
			service.depositar(11L, BigDecimal.valueOf(10));
		});

		assertAll(() -> assertEquals("Cliente não encontrado! Id: 11", exception.getMessage()));
	}
	
	@Test
	@Order(7)
	@DisplayName("Deve depositar com um valor válido")
	public void deveDepositarComValorValido() {
		assertDoesNotThrow(() -> service.depositar(ID_TESTE, BigDecimal.valueOf(10)));
	}

}
