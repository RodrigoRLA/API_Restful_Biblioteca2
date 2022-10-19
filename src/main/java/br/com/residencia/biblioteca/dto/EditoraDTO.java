package br.com.residencia.biblioteca.dto;

import java.util.List;


public class EditoraDTO {

	private Integer codigoEditora;
	private String nome;
	private List<LivroDTO> listalivrosDTO;
	
	
	public EditoraDTO() {		
	}

	public EditoraDTO(Integer codigoEditora, String nome) {
		
		this.codigoEditora = codigoEditora;
		this.nome = nome;
	}

	public Integer getCodigoEditora() {
		return codigoEditora;
	}

	public void setCodigoEditora(Integer codigoEditora) {
		this.codigoEditora = codigoEditora;
	}

	public List<LivroDTO> getListalivrosDTO() {
		return listalivrosDTO;
	}

	public void setListalivrosDTO(List<LivroDTO> listalivrosDTO) {
		this.listalivrosDTO = listalivrosDTO;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
		
}
	

