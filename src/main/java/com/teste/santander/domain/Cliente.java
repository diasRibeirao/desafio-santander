package com.teste.santander.domain;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "CLIENTES")
@Entity(name = "Cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "PLANO_EXCLUSIVE")
	private Boolean planoExclusive;
	
	@Column(name = "SALDO", precision = 38, scale = 2)
	private BigDecimal saldo;
	
	@Column(name = "NUMERO_DA_CONTA")
	private String numeroDaConta;

	@Column(name = "DATA_DE_NASCIMENTO")
	private Date dataDeNascimento;

	public Cliente(String nome, Boolean planoExclusive, BigDecimal saldo, String numeroDaConta,
			Date dataDeNascimento) {
		this.nome = nome;
		this.planoExclusive = planoExclusive;
		this.saldo = saldo;
		this.numeroDaConta = numeroDaConta;
		this.dataDeNascimento = dataDeNascimento;
	}
}
