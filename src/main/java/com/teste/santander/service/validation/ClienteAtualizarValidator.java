package com.teste.santander.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.teste.santander.controller.exceptions.FieldMessage;
import com.teste.santander.domain.Cliente;
import com.teste.santander.domain.dto.ClienteAtualizarDTO;
import com.teste.santander.repository.ClienteRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClienteAtualizarValidator implements ConstraintValidator<ClienteAtualizar, ClienteAtualizarDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repository;

	@Override
	public void initialize(ClienteAtualizar ann) {
	}

	@Override
	public boolean isValid(ClienteAtualizarDTO clienteAtualizarDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = Long.parseLong(map.get("id"));

		Cliente aux = repository.findByNumeroDaConta(clienteAtualizarDTO.getNumeroDaConta());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("numeroDaConta", "Número da conta já cadastrado"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}

		return list.isEmpty();
	}

}
