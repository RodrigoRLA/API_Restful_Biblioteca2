package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.EmprestimoDTO;
import br.com.residencia.biblioteca.entity.Emprestimo;
import br.com.residencia.biblioteca.repository.EmprestimoRepository;


@Service
public class EmprestimoService {

	@Autowired
	EmprestimoRepository emprestimoRepository;

	
	//DTO
	
	
	public EmprestimoDTO toDTO(Emprestimo emprestimo) {
		EmprestimoDTO emprestimoDTO = new EmprestimoDTO();		
		if (emprestimo!=null) {
			if (emprestimo.getCodigoEmprestimo() ==null || emprestimo.getCodigoEmprestimo()==0) {
				
				emprestimoDTO.setDataEmprestimo(emprestimo.getDataEmprestimo());
				emprestimoDTO.setDataEntrega(emprestimo.getDataEntrega());
				emprestimoDTO.setValorEmprestimo(emprestimo.getValorEmprestimo());
				
				
			}
			else {
				emprestimoDTO.setCodigoEmprestimo(emprestimo.getCodigoEmprestimo());
				emprestimoDTO.setDataEmprestimo(emprestimo.getDataEmprestimo());
				emprestimoDTO.setDataEntrega(emprestimo.getDataEntrega());
				emprestimoDTO.setValorEmprestimo(emprestimo.getValorEmprestimo());
			}
		}
		return emprestimoDTO;
	}
	
	public Emprestimo toEntity(EmprestimoDTO emprestimoDTO) {
		Emprestimo emprestimo = new Emprestimo();
		if (emprestimoDTO != null) {
			if (emprestimoDTO.getCodigoEmprestimo() == null || emprestimoDTO.getCodigoEmprestimo()==0) {
				emprestimo.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
				emprestimo.setDataEntrega(emprestimoDTO.getDataEntrega());
				emprestimo.setValorEmprestimo(emprestimoDTO.getValorEmprestimo());
			}
			else {
				emprestimo.setCodigoEmprestimo(emprestimoDTO.getCodigoEmprestimo());
				emprestimo.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
				emprestimo.setDataEntrega(emprestimoDTO.getDataEntrega());
				emprestimo.setValorEmprestimo(emprestimoDTO.getValorEmprestimo());
			}
		}
		return emprestimo;
	}
	
	public List<EmprestimoDTO> getAllEmprestimoDTO2(){
		return emprestimoRepository.findAll().stream()
				.map(entity -> new EmprestimoDTO(entity.getCodigoEmprestimo(),entity.getDataEmprestimo(),
						entity.getDataEntrega(),entity.getValorEmprestimo()))
				.collect(Collectors.toList());
	}
	
	
	public List<EmprestimoDTO> getAllEmprestimoDTO(){
		List<Emprestimo> listaEmprestimo = getAllEmprestimo();
		List<EmprestimoDTO> listaDTO = new ArrayList<EmprestimoDTO>();
		
		for (int i=0; i<listaEmprestimo.size();i++) {
			listaDTO.add(toDTO(listaEmprestimo.get(i)));
		}	
		return listaDTO;
	}
	
	public EmprestimoDTO saveEmprestimoDTO (EmprestimoDTO emprestimoDTO) {
		Emprestimo emprestimo = toEntity(emprestimoDTO);
		Emprestimo registroAluno = saveEmprestimo(emprestimo);
		EmprestimoDTO alunoFinal = toDTO(registroAluno);
		return alunoFinal;
	}
	
	
	public EmprestimoDTO updateEmprestimoDTO (EmprestimoDTO emprestimoDTO, Integer id) {
		Emprestimo emprestimoExistente = getEmprestimoById(id);
		EmprestimoDTO emprestimoAtualizadaDTO = new EmprestimoDTO();
		
		if (emprestimoExistente != null) {
			
			emprestimoExistente = toEntity(emprestimoDTO);
			
			Emprestimo alunoAtualizada = emprestimoRepository.save(emprestimoExistente);
			
			emprestimoAtualizadaDTO = toDTO(alunoAtualizada);
		}				
		return emprestimoAtualizadaDTO;
	}
	
	public List<Emprestimo> getAllEmprestimo(){
		return emprestimoRepository.findAll();
	}
	
	public Emprestimo getEmprestimoById (Integer idEmprestimo) {
		return emprestimoRepository.findById(idEmprestimo).orElse(null);
	}
	
	public Emprestimo saveEmprestimo (Emprestimo emprestimo) {
		return emprestimoRepository.save(emprestimo);
	}
	
	public Emprestimo updateEmprestimo(Emprestimo emprestimo, Integer id) {
		Emprestimo emprestimoExistenteNoBanco = emprestimoRepository.findById(id).get();
		
		emprestimoExistenteNoBanco.setDataEmprestimo(emprestimo.getDataEmprestimo());
		emprestimoExistenteNoBanco.setDataEntrega(emprestimo.getDataEntrega());
		emprestimoExistenteNoBanco.setValorEmprestimo(emprestimo.getValorEmprestimo());
	

				
		return emprestimoRepository.save(emprestimoExistenteNoBanco);
	}
	
	public Emprestimo deleteEmprestimo (Integer id) {
		emprestimoRepository.deleteById(id);
		return getEmprestimoById(id);
	}
	
	
}