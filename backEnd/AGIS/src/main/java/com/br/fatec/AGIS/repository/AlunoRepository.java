package com.br.fatec.AGIS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.fatec.AGIS.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, String>{
	
}
