package com.teste.santander.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.teste.santander.domain.enums.TipoTransacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "TRANSACOES")
@Entity(name = "Transacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "CLIENTE_ID")
	private Cliente cliente;

	@Column(name = "TIPO")
	@Enumerated(EnumType.STRING)
	private TipoTransacao tipo;

	@Column(name = "VALOR", precision = 38, scale = 2)
	private BigDecimal valor;
	
	@Column(name = "TAXA", precision = 38, scale = 2)
	private BigDecimal taxa;

	@Column(name = "DATA")
	private Date data;

	public Transacao(Cliente cliente, TipoTransacao tipo, BigDecimal valor, BigDecimal taxa) {
		this.cliente = cliente;
		this.tipo = tipo;
		this.valor = valor;
		this.taxa = taxa;
		this.data = new Date();
	}

}
