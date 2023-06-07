package com.teste.santander.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.santander.domain.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

	List<Transacao> findByData(Date data);

}
