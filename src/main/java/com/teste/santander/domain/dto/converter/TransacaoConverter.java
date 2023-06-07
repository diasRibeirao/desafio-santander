package com.teste.santander.domain.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.teste.santander.domain.Transacao;
import com.teste.santander.domain.dto.TransacaoDTO;

@Service
public class TransacaoConverter {

	public TransacaoDTO Parse(Transacao origin) {
		if (origin == null)
			return null;

		return new TransacaoDTO(origin.getId(), origin.getCliente().getNome(), origin.getTipo(), origin.getValor(),
				origin.getTaxa(), origin.getData());
	}

	public List<TransacaoDTO> Parse(List<Transacao> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> Parse(obj)).collect(Collectors.toList());
	}

}
