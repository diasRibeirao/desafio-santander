package com.teste.santander.domain.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.teste.santander.domain.Cliente;
import com.teste.santander.domain.dto.ClienteAdicionarDTO;
import com.teste.santander.domain.dto.ClienteAtualizarDTO;
import com.teste.santander.domain.dto.ClienteDTO;

@Service
public class ClienteConverter {

	public ClienteDTO Parse(Cliente origin) {
		if (origin == null)
			return null;

		return new ClienteDTO(origin.getId(), origin.getNome(), origin.getPlanoExclusive(), origin.getSaldo(),
				origin.getNumeroDaConta(), origin.getDataDeNascimento());
	}

	public List<ClienteDTO> Parse(List<Cliente> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> Parse(obj)).collect(Collectors.toList());
	}

	public Cliente ParseAdicionarDTO(ClienteAdicionarDTO origin) {
		if (origin == null)
			return null;

		return new Cliente(origin.getNome(), origin.getPlanoExclusive(), origin.getSaldo(), origin.getNumeroDaConta(),
				origin.getDataDeNascimento());
	}

	public Cliente ParseAtualizarDTO(ClienteAtualizarDTO origin) {
		if (origin == null)
			return null;

		return new Cliente(origin.getNome(), origin.getPlanoExclusive(), origin.getSaldo(), origin.getNumeroDaConta(),
				origin.getDataDeNascimento());
	}

}