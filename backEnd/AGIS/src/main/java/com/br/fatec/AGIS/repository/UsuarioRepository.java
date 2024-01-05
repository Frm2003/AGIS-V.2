package com.br.fatec.AGIS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.fatec.AGIS.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
	@Query(value = "select count(professor_cod) from Usuario where professor_cod is not null", nativeQuery = true)
	long countProfessor();
	
	@Query(value = "select cpf, data_nasc, email_corp, email_pessoal, nome, situacao, professor_cod, professor_titulacao from Usuario where tipo_entidade like 'P'", nativeQuery = true)
	List<Usuario> selectAllProfessores();
}
