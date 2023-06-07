package com.teste.santander.service.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.teste.santander.controller.exceptions.FieldMessage;
import com.teste.santander.domain.Cliente;
import com.teste.santander.domain.dto.ClienteAdicionarDTO;
import com.teste.santander.repository.ClienteRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClienteAdicionarValidator implements ConstraintValidator<ClienteAdicionar, ClienteAdicionarDTO> {

	@Autowired
	private ClienteRepository repository;

	@Override
	public void initialize(ClienteAdicionar ann) {
	}

	@Override
	public boolean isValid(ClienteAdicionarDTO clienteoAdicionarDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		Cliente aux = repository.findByNumeroDaConta(clienteoAdicionarDTO.getNumeroDaConta());
		if (aux != null) {
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
