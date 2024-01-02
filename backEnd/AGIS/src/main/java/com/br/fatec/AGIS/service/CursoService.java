package com.br.fatec.AGIS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.fatec.AGIS.model.Curso;
import com.br.fatec.AGIS.repository.CursoRepository;

@Service
public class CursoService {
	@Autowired
	private CursoRepository cursoRepository;
	
	public Curso insert(Curso c) {
		return cursoRepository.save(c);
	}
}