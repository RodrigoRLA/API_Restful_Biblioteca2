package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.AlunoDTO;
import br.com.residencia.biblioteca.dto.AlunoResumoDTO;
import br.com.residencia.biblioteca.dto.EmprestimoDTO;
import br.com.residencia.biblioteca.dto.EmprestimoResumoDTO;
import br.com.residencia.biblioteca.entity.Aluno;
import br.com.residencia.biblioteca.entity.Emprestimo;
import br.com.residencia.biblioteca.repository.AlunoRepository;
import br.com.residencia.biblioteca.repository.EmprestimoRepository;

@Service
public class AlunoService {

	@Autowired
	AlunoRepository alunoRepository;

	@Autowired
	EmprestimoRepository emprestimoRepository;

	@Autowired
	EmprestimoService emprestimoService;

	//DTO

	public AlunoDTO toDTO(Aluno aluno) {
		AlunoDTO alunoDTO = new AlunoDTO();
		if (aluno != null) {
			if (aluno.getNumeroMatriculaAluno() == null || aluno.getNumeroMatriculaAluno() == 0) {
				alunoDTO.setNome(aluno.getNome());
				alunoDTO.setBairro(aluno.getBairro());
				alunoDTO.setCpf(aluno.getCpf());
				alunoDTO.setDataNascimento(aluno.getDatanascimento());
				alunoDTO.setCidade(aluno.getCidade());
				alunoDTO.setLogradouro(aluno.getLogradouro());
				alunoDTO.setComplemento(aluno.getComplemento());
				alunoDTO.setNumeroLogradouro(aluno.getNumerologradouro());
			} else {
				alunoDTO.setNumeroMatriculaAluno(aluno.getNumeroMatriculaAluno());
				alunoDTO.setNome(aluno.getNome());
				alunoDTO.setBairro(aluno.getBairro());
				alunoDTO.setCpf(aluno.getCpf());
				alunoDTO.setDataNascimento(aluno.getDatanascimento());
				alunoDTO.setCidade(aluno.getCidade());
				alunoDTO.setLogradouro(aluno.getLogradouro());
				alunoDTO.setComplemento(aluno.getComplemento());
				alunoDTO.setNumeroLogradouro(aluno.getNumerologradouro());
			}
		}
		return alunoDTO;
	}

	public Aluno toEntity(AlunoDTO alunoDTO) {
		Aluno aluno = new Aluno();
		if (alunoDTO != null) {
			if (alunoDTO.getNumeroMatriculaAluno() == null || alunoDTO.getNumeroMatriculaAluno() == 0) {
				aluno.setNome(alunoDTO.getNome());
				aluno.setBairro(alunoDTO.getBairro());
				aluno.setCpf(alunoDTO.getCpf());
				aluno.setDataNascimento(alunoDTO.getDataNascimento());
				aluno.setCidade(alunoDTO.getCidade());
				aluno.setLogradouro(alunoDTO.getLogradouro());
				aluno.setComplemento(alunoDTO.getComplemento());
				aluno.setNumeroLogradouro(alunoDTO.getNumeroLogradouro());
			} else {
				aluno.setNumeroMatriculaAluno(alunoDTO.getNumeroMatriculaAluno());
				aluno.setNome(alunoDTO.getNome());
				aluno.setBairro(alunoDTO.getBairro());
				aluno.setCpf(alunoDTO.getCpf());
				aluno.setDataNascimento(alunoDTO.getDataNascimento());
				aluno.setCidade(alunoDTO.getCidade());
				aluno.setLogradouro(alunoDTO.getLogradouro());
				aluno.setComplemento(alunoDTO.getComplemento());
				aluno.setNumeroLogradouro(alunoDTO.getNumeroLogradouro());
			}
		}
		return aluno;
	}

	public List<AlunoDTO> getAllAlunoDTO2() {
		return alunoRepository.findAll().stream()
				.map(entity -> new AlunoDTO(entity.getNumeroMatriculaAluno(), entity.getNome(),
						entity.getDataNascimento(), entity.getCpf(), entity.getLogradouro(),
						entity.getNumeroLogradouro(), entity.getComplemento(), entity.getBairro(), entity.getCidade()))
				.collect(Collectors.toList());
	}

	public List<AlunoDTO> getAllAlunoDTO() {
		List<Aluno> listaAluno = getAllAlunos();
		List<AlunoDTO> listaDTO = new ArrayList<AlunoDTO>();

		for (int i = 0; i < listaAluno.size(); i++) {
			listaDTO.add(toDTO(listaAluno.get(i)));
		}
		return listaDTO;
	}

	public AlunoDTO saveAlunoDTO(AlunoDTO alunoDTO) {
		Aluno aluno1 = toEntity(alunoDTO);
		Aluno registroAluno = saveAluno(aluno1);
		AlunoDTO alunoFinal = toDTO(registroAluno);
		return alunoFinal;
	}

	public AlunoDTO updateAlunoDTO(AlunoDTO alunoDTO, Integer id) {
		Aluno alunoExistente = getAlunoById(id);
		AlunoDTO alunoAtualizadaDTO = new AlunoDTO();

		if (alunoExistente != null) {

			alunoExistente = toEntity(alunoDTO);

			Aluno alunoAtualizada = alunoRepository.save(alunoExistente);

			alunoAtualizadaDTO = toDTO(alunoAtualizada);
		}
		return alunoAtualizadaDTO;
	}

	// 

	public AlunoResumoDTO getAlunoByIdDTO(Integer id) {

		Aluno aluno = alunoRepository.findById(id).orElse(null);
		AlunoDTO alunoDTO = toDTO(aluno);
		AlunoResumoDTO alunoResumoDTO = new AlunoResumoDTO();

		List<Emprestimo> listaEmprestimos = emprestimoRepository.findByAluno(aluno);
		List<EmprestimoResumoDTO> listaEmprestimosResumoDTO = new ArrayList<>();
		for (Emprestimo emprestimo : listaEmprestimos) {
			EmprestimoResumoDTO emprestimoResumoDTO = new EmprestimoResumoDTO();
			EmprestimoDTO emprestimoDTO = emprestimoService.toDTO(emprestimo);

			emprestimoResumoDTO.setCodigoEmprestimo(emprestimoDTO.getCodigoEmprestimo());
			emprestimoResumoDTO.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
			emprestimoResumoDTO.setDataEntrega(emprestimoDTO.getDataEntrega());

			listaEmprestimosResumoDTO.add(emprestimoResumoDTO);
		}
		alunoResumoDTO.setNumeroMatriculaAluno(alunoDTO.getNumeroMatriculaAluno());
		alunoResumoDTO.setNome(alunoDTO.getNome());
		alunoResumoDTO.setCpf(alunoDTO.getCpf());
		alunoResumoDTO.setListaEmprestimoResumoDTO(listaEmprestimosResumoDTO);

		return alunoResumoDTO;
	}

	public List<AlunoResumoDTO> getAllAlunoResumoDTO() {
		List<Aluno> listaAluno = alunoRepository.findAll();
		List<AlunoResumoDTO> listaAlunoResumoDTO = new ArrayList<>();

		for (Aluno aluno : listaAluno) {
			// Conversão para DTO
			AlunoResumoDTO alunoResumoDTO = getAlunoByIdDTO(aluno.getNumeroMatriculaAluno());

			listaAlunoResumoDTO.add(alunoResumoDTO);

		}

		return listaAlunoResumoDTO;
	}

	// 

	public List<Aluno> getAllAlunos() {
		return alunoRepository.findAll();
	}

	public Aluno getAlunoById(Integer idAluno) {
		return alunoRepository.findById(idAluno).orElse(null);
	}

	public Aluno saveAluno(Aluno aluno) {
		return alunoRepository.save(aluno);
	}

	public Aluno updateAluno(Aluno aluno, Integer id) {
		Aluno alunoExistenteNoBanco = alunoRepository.findById(id).get();

		alunoExistenteNoBanco.setBairro(aluno.getBairro());
		alunoExistenteNoBanco.setCidade(aluno.getCidade());
		alunoExistenteNoBanco.setComplemento(aluno.getComplemento());
		alunoExistenteNoBanco.setCpf(aluno.getCpf());
		alunoExistenteNoBanco.setDataNascimento(aluno.getDataNascimento());
		alunoExistenteNoBanco.setLogradouro(aluno.getLogradouro());
		alunoExistenteNoBanco.setNome(aluno.getNome());
		alunoExistenteNoBanco.setNumeroLogradouro(aluno.getNumeroLogradouro());

		return alunoRepository.save(alunoExistenteNoBanco);
	}

	public Aluno deleteAluno(Integer id) {
		alunoRepository.deleteById(id);
		return getAlunoById(id);
	}
}