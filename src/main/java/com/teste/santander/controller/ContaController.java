package com.teste.santander.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teste.santander.domain.Transacao;
import com.teste.santander.domain.dto.TransacaoDTO;
import com.teste.santander.domain.dto.TransacaoDataDTO;
import com.teste.santander.domain.dto.converter.TransacaoConverter;
import com.teste.santander.service.ContaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/contas")
@Tag(name = "Contas")
public class ContaController {

	@Autowired
	private ContaService service;

	@Autowired
	private TransacaoConverter converter;

	@PostMapping(value = "/sacar/{clienteId}")
	@Operation(summary = "Sacar um valor")
	public ResponseEntity<String> sacar(@Valid @PathVariable Long clienteId,
			@Valid @RequestParam BigDecimal valorSaque) {
		service.sacar(clienteId, valorSaque);
		return ResponseEntity.ok().body("Saque realizado com sucesso");
	}

	@PostMapping(value = "/depositar/{clienteId}")
	@Operation(summary = "Depositar um valor")
	public ResponseEntity<String> depositar(@Valid @PathVariable Long clienteId,
			@Valid @RequestParam BigDecimal valorDeposito) {
		service.depositar(clienteId, valorDeposito);
		return ResponseEntity.ok().body("Depósito realizado com sucesso");
	}

	@GetMapping("/transacoes/data")
	@Operation(summary = "Histórico de transações por data")
	public ResponseEntity<List<TransacaoDTO>> transacoesByData(
			@Valid @DateTimeFormat(pattern = "dd/MM/yyyy") @Parameter(schema = @Schema(type = "string", format = "dd/MM/yyyy", example = "06/06/2023")) @RequestParam Date data) {
		List<TransacaoDTO> list = converter.Parse(service.findByData(data));
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/transacoes")
	@Operation(summary = "Histórico de transações de cada movimentação por data")
	public ResponseEntity<List<TransacaoDataDTO>> transacoes(@PageableDefault(size = 10, sort = { "data" }) Pageable paginacao) {
		List<Transacao> transacoes = service.findAll();
		
		Map<Date, TransacaoDataDTO> map = new HashMap<>();
		transacoes.forEach(t -> {
			map.computeIfAbsent(t.getData(), d -> new TransacaoDataDTO(d)).add(converter.Parse(t));
		});
		
		return ResponseEntity.ok(new ArrayList<>(map.values()));
	}

}
