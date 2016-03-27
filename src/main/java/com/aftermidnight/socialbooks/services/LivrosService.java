package com.aftermidnight.socialbooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.aftermidnight.socialbooks.domain.Livro;
import com.aftermidnight.socialbooks.exceptions.RecursoNaoEncontradoException;
import com.aftermidnight.socialbooks.repository.LivrosRepository;

@Service
public class LivrosService {

	@Autowired
	private LivrosRepository livrosRepository;
	
	
	public List<Livro> listar(){	
		return livrosRepository.findAll();
	}
	
	public Livro buscar (Long id){
		Livro livro = livrosRepository.findOne(id);	
		
		if(livro == null ){
			throw new RecursoNaoEncontradoException("O livro não pôde encontrado.");
		}
	
		return livro;
	}
	
	public Livro salvar(Livro livro) {
		livro.setId(null);
		return livrosRepository.save(livro);
	}
	
	public void deletar(Long id) {
		try {
			livrosRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RecursoNaoEncontradoException("O livro não pôde ser encontrado.");
		}
	}
	
	public void atualizar(Livro livro) {
		verificarExistencia(livro);
		livrosRepository.save(livro);
	}
	
	private void verificarExistencia(Livro livro) {
		buscar(livro.getId());
	}
	
	
}
