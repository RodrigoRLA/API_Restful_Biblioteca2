package br.com.residencia.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.residencia.biblioteca.dto.AlunoDTO;
import br.com.residencia.biblioteca.dto.AlunoResumoDTO;
import br.com.residencia.biblioteca.entity.Aluno;
import br.com.residencia.biblioteca.service.AlunoService;



@RestController
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	AlunoService alunoService;
	
	
	//DTO	
	
	@GetMapping("/dto")
	public ResponseEntity<List<AlunoDTO>> getAllAlunosDTO(){ 
		return new ResponseEntity<>(alunoService.getAllAlunoDTO(),HttpStatus.OK);
	}
	
	@PostMapping("/dto")
	public ResponseEntity<AlunoDTO> saveAlunoDTO (@RequestBody AlunoDTO livroDTO) {
		return new ResponseEntity<>(alunoService.saveAlunoDTO(livroDTO),HttpStatus.OK);
	}

	@PutMapping("/dto/{id}")
	public ResponseEntity<AlunoDTO> updateLivroDTO (@RequestBody AlunoDTO alunoDTO, @PathVariable Integer id) {
		return new ResponseEntity<>(alunoService.updateAlunoDTO(alunoDTO,id),HttpStatus.OK);
	}
	
	@GetMapping("/resumo/{id}")
	public ResponseEntity<AlunoResumoDTO> getAlunoByIdDTO(@PathVariable Integer id) {
		AlunoResumoDTO aluno = alunoService.getAlunoByIdDTO(id);
		
		if (aluno != null) {
			return new ResponseEntity<>(aluno,HttpStatus.OK);			
		}
		else {
			return new ResponseEntity<>(aluno,HttpStatus.NOT_FOUND);
		}
		
		
	}
	@GetMapping("/dto/all")
	public ResponseEntity<List<AlunoResumoDTO>> getAllAlunoDTO() {
		return new ResponseEntity<>(alunoService.getAllAlunoResumoDTO(),HttpStatus.OK);
		
	}
	
	//
	
	@GetMapping
	public ResponseEntity<List<Aluno>> getAllAlunos(){ 
		return new ResponseEntity<>(alunoService.getAllAlunos(),HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> getAlunoById(@PathVariable Integer id) {
		Aluno aluno = alunoService.getAlunoById(id);
		
		if (aluno != null) {
			return new ResponseEntity<>(aluno,HttpStatus.OK);			
		}
		else {
			return new ResponseEntity<>(aluno,HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@PostMapping
	public ResponseEntity<Aluno> saveAluno (@RequestBody Aluno obj) {
		return new ResponseEntity<>(alunoService.saveAluno(obj),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Aluno> updateAluno (@RequestBody Aluno obj, @PathVariable Integer id) {
		return new ResponseEntity<>(alunoService.updateAluno(obj,id),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Aluno> deleteAluno (@PathVariable Integer id) {
		
		Aluno aluno = alunoService.getAlunoById(id);
		
		if (aluno == null) {
			return new ResponseEntity<>(alunoService.deleteAluno(id),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(alunoService.deleteAluno(id),HttpStatus.OK);
	}
	
}