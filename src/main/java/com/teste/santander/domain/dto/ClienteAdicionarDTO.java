package com.teste.santander.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teste.santander.service.validation.ClienteAdicionar;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@ClienteAdicionar
@Data
public class ClienteAdicionarDTO {

	@NotEmpty(message = "O nome é obrigatório!")
	@Size(min = 5, max = 100, message = "O nome deve possuir entre {min} e {max} caracteres!")
	private String nome;

	@NotNull(message = "O plano exclusive é obrigatório!")
	private Boolean planoExclusive;

	@NotNull(message = "O saldo é obrigatório!")
	private BigDecimal saldo;

	@NotEmpty(message = "O número da conta é obrigatório!")
	@Size(min = 5, max = 20, message = "O número da conta deve possuir entre {min} e {max} caracteres!")
	private String numeroDaConta;

	@NotNull(message = "A data de nascimento é obrigatório!")
	@Schema(type = "string", pattern = "dd/MM/yyyy", example = "22/07/1983")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataDeNascimento;

}
