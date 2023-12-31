package com.br.fatec.AGIS.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.fatec.AGIS.dto.TurmaDto;
import com.br.fatec.AGIS.model.Disciplina;
import com.br.fatec.AGIS.model.GradeCurricular;
import com.br.fatec.AGIS.model.Professor;
import com.br.fatec.AGIS.model.Turma;
import com.br.fatec.AGIS.repository.DisciplinaRepository;
import com.br.fatec.AGIS.repository.GradeCurricularRepository;
import com.br.fatec.AGIS.repository.ProfessorRepository;
import com.br.fatec.AGIS.repository.TurmaRepository;

@Service
public class TurmaService {
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private GradeCurricularRepository gradeCurricularRepository;
	
	@Autowired
	private TurmaRepository turmaRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	public Turma insert(TurmaDto t) {
		Optional<Disciplina> disciplina = disciplinaRepository.findById(t.codDisciplina());
		Optional<Professor> professor = professorRepository.findById(t.codProfessor());
		Optional<GradeCurricular> gradeCurricular = gradeCurricularRepository.findById(t.codGradeCurricular());
		
		var turmaModel = new Turma(t, disciplina.get(), professor.get(), gradeCurricular.get());
		
		return turmaRepository.save(turmaModel);
	}
}
