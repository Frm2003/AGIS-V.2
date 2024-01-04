package com.br.fatec.AGIS.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@DiscriminatorValue("P")
public class Professor extends Usuario {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long cod;

	@Column(length = 100)
	private String titulacao;
	
	
	public Professor(String cpf, String nome, LocalDate dataNasc, String emailPessoal, String emailCorp, String situacao, String titulacao) {
		super(cpf, nome, dataNasc, emailPessoal, emailCorp, situacao);
		this.titulacao = titulacao;
	}

}
