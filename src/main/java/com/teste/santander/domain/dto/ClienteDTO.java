package com.teste.santander.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ClienteDTO (
		Long id, 
		String nome, 
		Boolean planoExclusive,  
		BigDecimal saldo, 
		String numeroDaConta ,
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
		Date dataDeNascimento) {
	
}
