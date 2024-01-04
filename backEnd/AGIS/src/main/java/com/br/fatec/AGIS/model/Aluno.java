package com.br.fatec.AGIS.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;

@Entity
@DiscriminatorValue("A")
public class Aluno extends Usuario{
	@Column(unique = true, length = 15)
	private String ra;
	
	@Column(name = "nome_social", length = 100)
	private String nomeSocial;
	
	@Column(name = "data_conclusao_2_grau", columnDefinition = "DATE")
	private LocalDate dataConc2grau;
	
	@Column(name = "instituicao_conclusao_2_grau", length = 100)
	private String instConc2grau;
	
	@Column(name = "pontuacao_vestibular")
	private int ptVestibular;
	
	@Column(name = "posicao_vestibular")
	private int posVestibular;
	
	@Column(name = "data_matricula", columnDefinition = "DATE")
	private LocalDate dataMatricula;

	@Column(name = "data_limite_matricula", columnDefinition = "DATE")
	private LocalDate dataLimiteMatricula;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Curso.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_curso")
	private Curso curso;
	
	@Builder
	public Aluno(String cpf, String nome, LocalDate dataNasc, String emailPessoal, String emailCorp, String situacao,
			String ra, String nomeSocial, LocalDate dataConc2grau, String instConc2grau, int ptVestibular,
			int posVestibular, LocalDate dataMatricula, LocalDate dataLimiteMatricula, Curso curso) {
		super(cpf, nome, dataNasc, emailPessoal, emailCorp, situacao);
		this.ra = ra;
		this.nomeSocial = nomeSocial;
		this.dataConc2grau = dataConc2grau;
		this.instConc2grau = instConc2grau;
		this.ptVestibular = ptVestibular;
		this.posVestibular = posVestibular;
		this.dataMatricula = dataMatricula;
		this.dataLimiteMatricula = dataLimiteMatricula;
		this.curso = curso;
	}
	
}
