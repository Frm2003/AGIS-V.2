package com.br.fatec.AGIS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.fatec.AGIS.dto.ProfessorDto;
import com.br.fatec.AGIS.model.Professor;
import com.br.fatec.AGIS.model.Usuario;
import com.br.fatec.AGIS.repository.UsuarioRepository;

@Service
public class ProfessorService {
	@Autowired
	private UsuarioRepository professorRepository;
	
	public List<Usuario> selectAll() {
		return professorRepository.selectAllProfessores();
	}
	
	public Professor insert(ProfessorDto professorDto) {
		var professorModel = new Professor(
				professorDto.cpf(),
				professorDto.nome(),
				professorDto.dataNasc(),
				professorDto.emailPessoal(),
				"prof@agis.com",
				"ativo",
				professorRepository.countProfessor() + 1,
				professorDto.titulacao());
		return professorRepository.save(professorModel);
	}
}
