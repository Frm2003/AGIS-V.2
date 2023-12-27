package com.br.fatec.AGIS.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@lombok.Data
@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class Disciplina {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long cod;
	
	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(nullable = false)
	private int qtdAulas; 
	
	@Column(nullable = false)
	private int semestre; 
	
	@ManyToOne(cascade = CascadeType.REMOVE, targetEntity = Curso.class, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "codCurso")
	private Curso curso;
}
