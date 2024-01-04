package com.br.fatec.AGIS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.fatec.AGIS.model.Curso;
import com.br.fatec.AGIS.repository.CursoRepository;

@Service
public class CursoService {
	@Autowired
	private CursoRepository cursoRepository;

	public List<Curso> selectAll() {
		return cursoRepository.findAll();
	}
	
	public Curso selectId(Long id) throws Exception {
		Optional<Curso> curso = cursoRepository.findById(id);
		if (curso.isEmpty()) {
			throw new Exception("Curso não registrado");
		}

		return curso.get();
	}

	public Curso insert(Curso c) {
		return cursoRepository.save(c);
	}

	public Curso update(Long id, Curso curso) throws Exception {
		Optional<Curso> optional = cursoRepository.findById(id);

		if (optional.isEmpty()) {
			throw new Exception("Curso não registrado");
		}

		Curso cursoNew = optional.get();
		cursoNew.setNome(curso.getNome());
		cursoNew.setCargaHorario(curso.getCargaHorario());
		cursoNew.setSigla(curso.getSigla());
		cursoNew.setEnade(curso.getEnade());
		cursoNew.setTurno(curso.getTurno());

		return cursoRepository.save(cursoNew);
	}

	public Curso delete(Long id) throws Exception {
		Optional<Curso> curso = cursoRepository.findById(id);
		if (curso.isEmpty()) {
			throw new Exception("Curso não registrado");
		}

		var cursoModel = curso.get();
		cursoRepository.delete(cursoModel);

		return cursoModel;
	}
}
