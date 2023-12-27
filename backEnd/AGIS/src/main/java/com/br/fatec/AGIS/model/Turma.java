package com.br.fatec.AGIS.model;

import java.sql.Time;

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
public class Turma {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long cod;
	
	@Column(nullable = false, columnDefinition = "TIME")
	private Time horarioInicio;
	
	@Column(nullable = false, columnDefinition = "TIME")
	private Time horarioFim;
	
	@Column(nullable = false, length = 20)
	private String diaDaSemana;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Disciplina.class, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "codDisciplina")
	private Disciplina disciplina;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Professor.class, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "codProfessor")
	private Professor professor;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = GradeCurricular.class, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "codGrade")
	private GradeCurricular gradeCurricular;
}
