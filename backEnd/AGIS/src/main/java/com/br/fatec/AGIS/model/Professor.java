package com.br.fatec.AGIS.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("P")
@NoArgsConstructor
public class Professor extends Usuario {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "professor_cod", unique = true)
	private Long cod;

	@Column(name = "professor_titulacao", length = 100)
	private String titulacao;
	
	public Professor(String cpf, String nome, LocalDate dataNasc, String emailPessoal, String emailCorp, String situacao, Long cod, String titulacao) {
		super(cpf, nome, dataNasc, emailPessoal, emailCorp, situacao);
		this.cod = cod;
		this.titulacao = titulacao;
	}
}
