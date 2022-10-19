package br.com.residencia.biblioteca.dto;

import java.util.List;

public class AlunoResumoDTO {

	private Integer numeroMatriculaAluno;
	private String nome;
	private String cpf;
	private List<EmprestimoResumoDTO> listaEmprestimoResumoDTO;

	public AlunoResumoDTO() {
		
	}

	public AlunoResumoDTO(Integer numeroMatriculaAluno, String nome, String cpf,
			List<EmprestimoResumoDTO> listaEmprestimoResumoDTO) {

		this.numeroMatriculaAluno = numeroMatriculaAluno;
		this.nome = nome;
		this.cpf = cpf;
		this.listaEmprestimoResumoDTO = listaEmprestimoResumoDTO;
	}

	public Integer getNumeroMatriculaAluno() {
		return numeroMatriculaAluno;
	}

	public void setNumeroMatriculaAluno(Integer numeroMatriculaAluno) {
		this.numeroMatriculaAluno = numeroMatriculaAluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<EmprestimoResumoDTO> getListaEmprestimoResumoDTO() {
		return listaEmprestimoResumoDTO;
	}

	public void setListaEmprestimoResumoDTO(List<EmprestimoResumoDTO> listaEmprestimoResumoDTO) {
		this.listaEmprestimoResumoDTO = listaEmprestimoResumoDTO;
	}

}
