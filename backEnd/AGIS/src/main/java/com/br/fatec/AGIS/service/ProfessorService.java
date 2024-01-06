package com.br.fatec.AGIS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.fatec.AGIS.dto.ProfessorDto;
import com.br.fatec.AGIS.model.Professor;
import com.br.fatec.AGIS.model.Usuario;
import com.br.fatec.AGIS.repository.ProfessorRepository;

@Service
public class ProfessorService {
	@Autowired
	private ProfessorRepository professorRepository;
	
	public List<Professor> selectAll() {
		return professorRepository.findAll();
	}
	
	public Professor insert(ProfessorDto professorDto) {
		var professorModel = new Professor();
		Usuario user = new Usuario();
		
		user.setCpf(professorDto.cpf());
		user.setNome(professorDto.nome());
		user.setDataNasc(professorDto.dataNasc());
		user.setEmailPessoal(professorDto.emailPessoal());
		user.setEmailCorp("teste");
		user.setSituacao("ativo");
		
		professorModel.setTitulacao(professorDto.titulacao());
		professorModel.setUsuario(user);
		
		return professorRepository.save(professorModel);
	}
}