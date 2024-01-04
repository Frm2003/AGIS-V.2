package com.br.fatec.AGIS.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_entidade")
public class Usuario {
	@Id
	@Column(nullable = false, unique = true, length = 11)
	protected String cpf;

	@Column(nullable = false, length = 100)
	protected String nome;

	@Column(nullable = false, name = "data_nasc", columnDefinition = "DATE")
	protected LocalDate dataNasc;

	@Column(nullable = false, name = "email_pessoal", length = 30)
	protected String emailPessoal;

	@Column(nullable = false, name = "email_corp", length = 30)
	protected String emailCorp;

	@Column(nullable = false, length = 20)
	private String situacao;

	public Usuario(String cpf, String nome, LocalDate dataNasc, String emailPessoal, String emailCorp, String situacao) {
		this.cpf = cpf;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.emailPessoal = emailPessoal;
		this.emailCorp = emailCorp;
		this.situacao = situacao;
	}

}
