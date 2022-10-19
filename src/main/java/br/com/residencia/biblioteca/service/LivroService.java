package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.LivroDTO;
import br.com.residencia.biblioteca.entity.Livro;
import br.com.residencia.biblioteca.repository.LivroRepository;


@Service
public class LivroService {

	@Autowired
	LivroRepository livroRepository;

	public List<Livro> getAllLivro() {
		return livroRepository.findAll();
	}

	public Livro getLivroById(Integer idLivro) {
		return livroRepository.findById(idLivro).get();
	}

	public Livro saveLivro(Livro livro) {
		return livroRepository.save(livro);
	}

	public Livro updateLivro(Livro livro, Integer id) {
		Livro livroExistenteNoBanco = livroRepository.findById(id).get();

		livroExistenteNoBanco.setCodigoISBN(livro.getCodigoISBN());
		livroExistenteNoBanco.setDataLancamento(livro.getDataLancamento());
		livroExistenteNoBanco.setNomeAutor(livro.getNomeAutor());
		livroExistenteNoBanco.setNomeLivro(livro.getNomeLivro());

		return livroRepository.save(livroExistenteNoBanco);
	}

	public Livro deleteLivro(Integer id) {
		livroRepository.deleteById(id);
		return getLivroById(id);
	}

	///////

	public List<LivroDTO> getAllLivroDTO() {
		List<Livro> listaLivro = livroRepository.findAll();
		List<LivroDTO> listaLivroDTO = new ArrayList<>();

		for (Livro livro : listaLivro) {

			LivroDTO livroDTO = toDTO(livro);

			listaLivroDTO.add(livroDTO);
		}
		return listaLivroDTO;

	}

	public LivroDTO saveLivroDTO(LivroDTO livroDTO) {
		Livro livro = toEntidade(livroDTO);
		Livro novoLivro = livroRepository.save(livro);

		LivroDTO livroAtualizadoDTO = toDTO(novoLivro);

		return livroAtualizadoDTO;
	}

	public LivroDTO updateLivroDTO(LivroDTO livroDTO, Integer id) {

		Livro livroExistenteNoBanco = getLivroById(id);
		LivroDTO livroAtualizadoDTO = new LivroDTO();
		if (livroExistenteNoBanco != null) {

			livroExistenteNoBanco = toEntidade(livroDTO);
			Livro livroAtualizado = livroRepository.save(livroExistenteNoBanco);

			livroAtualizadoDTO = toDTO(livroAtualizado);
		}
		return livroAtualizadoDTO;
	}

	public Livro toEntidade(LivroDTO livroDTO) {

		Livro livro = new Livro();

		livro.setNomeLivro(livroDTO.getNomeLivro());
		livro.setNomeAutor(livroDTO.getNomeAutor());
		livro.setDataLancamento(livroDTO.getDataLancamento());
		livro.setCodigoISBN(livroDTO.getCodigoISBN());
		return livro;
	}

	public LivroDTO toDTO(Livro livro) {

		LivroDTO livroDTO = new LivroDTO();

		livroDTO.setCodigoLivro(livro.getCodigoLivro());
		livroDTO.setNomeLivro(livro.getNomeLivro());
		livroDTO.setNomeAutor(livro.getNomeAutor());
		livroDTO.setDataLancamento(livro.getDataLancamento());
		livroDTO.setCodigoISBN(livro.getCodigoISBN());

		return livroDTO;
	}
	
	
}
