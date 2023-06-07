package com.teste.santander.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.santander.domain.Cliente;
import com.teste.santander.domain.Transacao;
import com.teste.santander.domain.enums.TipoTransacao;
import com.teste.santander.repository.ClienteRepository;
import com.teste.santander.repository.TransacaoRepository;
import com.teste.santander.service.exceptions.ObjectNotFoundException;
import com.teste.santander.service.exceptions.TransacoesException;

@Service
public class ContaService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private TransacaoRepository transacaoRepository;

	public void sacar(Long clienteId, BigDecimal valorSaque) {
		if (valorSaque.compareTo(BigDecimal.ZERO) <= 0) {
			throw new TransacoesException("Valor para saque inválido");
		}

		Cliente cliente = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! Id: " + clienteId));

		BigDecimal taxa = calcularTaxaSaque(valorSaque, cliente.getPlanoExclusive());
		BigDecimal valorFinal = valorSaque.add(taxa);

		if (valorFinal.compareTo(cliente.getSaldo()) > 0) {
			throw new TransacoesException("Saldo insuficiente para realizar o saque");
		}

		cliente.setSaldo(cliente.getSaldo().subtract(valorFinal));
		clienteRepository.save(cliente);
		transacaoRepository.save(criarTransacao(cliente, TipoTransacao.SAQUE, valorSaque, taxa));
	}

	public void depositar(Long clienteId, BigDecimal valorDeposito) {
		if (valorDeposito.compareTo(BigDecimal.ZERO) <= 0) {
			throw new TransacoesException("Valor para depósito inválido");
		}

		Cliente cliente = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! Id: " + clienteId));

		cliente.setSaldo(cliente.getSaldo().add(valorDeposito));
		clienteRepository.save(cliente);
		transacaoRepository.save(criarTransacao(cliente, TipoTransacao.DEPOSITO, valorDeposito, BigDecimal.ZERO));
	}

	private BigDecimal calcularTaxaSaque(BigDecimal valorSaque, boolean possuiPlanoExclusive) {
		BigDecimal taxa = BigDecimal.ZERO;

		if (valorSaque.compareTo(BigDecimal.valueOf(100.00)) <= 0 || possuiPlanoExclusive) {
			return taxa;
		} else if (valorSaque.compareTo(BigDecimal.valueOf(300.00)) <= 0) {
			taxa = valorSaque.multiply(BigDecimal.valueOf(0.004));
		} else {
			taxa = valorSaque.multiply(BigDecimal.valueOf(0.01));
		}

		return taxa;
	}

	private Transacao criarTransacao(Cliente cliente, TipoTransacao tipo, BigDecimal valor, BigDecimal taxa) {
		return new Transacao(cliente, tipo, valor, taxa);
	}

	public List<Transacao> findByData(Date data) {
		return transacaoRepository.findByData(data);
	}

	public List<Transacao> findAll() {
		return transacaoRepository.findAll();
	}

}
