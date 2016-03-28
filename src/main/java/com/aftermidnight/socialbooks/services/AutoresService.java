package com.aftermidnight.socialbooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aftermidnight.socialbooks.domain.Autor;
import com.aftermidnight.socialbooks.exceptions.AutorJaExistenteException;
import com.aftermidnight.socialbooks.exceptions.AutorNaoEncontradoException;
import com.aftermidnight.socialbooks.repository.AutoresRepository;

@Service
public class AutoresService {

	@Autowired
	private AutoresRepository autoresRepository;
	
	public List<Autor> listar() {
		return autoresRepository.findAll();
	}	
	
	public Autor salvar(Autor autor) {
		if(autor.getId() != null) {
			Autor a = autoresRepository.findOne(autor.getId());
			
			if(a != null) {
				throw new AutorJaExistenteException("O autor já existe.");
			}
		}
		return autoresRepository.save(autor);
	}
	
	public Autor buscar(Long id) {
		Autor autor = autoresRepository.findOne(id);
		
		if(autor == null) {
			throw new AutorNaoEncontradoException("O autor não pôde ser encontrado.");
		}
		return autor;
	}
}