package com.teste.santander.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teste.santander.domain.enums.TipoTransacao;

public record TransacaoDTO(
		Long id, 
		String cliente, 
		TipoTransacao tipo, 
		BigDecimal valor,
		BigDecimal taxa,
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") 
		Date data) {

}
