package com.br.fatec.AGIS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.fatec.AGIS.model.Curso;
import com.br.fatec.AGIS.service.CursoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/curso")
@CrossOrigin(origins = "http://127.0.0.1:5500/templates/secretaria/crudCurso.html")
public class CursoController {
	@Autowired
	private CursoService cursoService;
	
	@GetMapping
	public ResponseEntity<String> teste() {
		return ResponseEntity.status(HttpStatus.OK).body("teste");
	}
	
	@PostMapping
	public ResponseEntity<Curso> insert(@RequestBody @Valid Curso curso) {
		System.out.println(curso);
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.insert(curso));
	}
}
