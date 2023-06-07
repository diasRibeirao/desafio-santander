package com.teste.santander.domain.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TransacaoDataDTO {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date data;
	private List<TransacaoDTO> transacoes;

	public TransacaoDataDTO(Date data) {
		this.data = data;
		this.transacoes = new ArrayList<>();
	}

	public void add(TransacaoDTO t) {
		this.getTransacoes().add(t);
	}

}
