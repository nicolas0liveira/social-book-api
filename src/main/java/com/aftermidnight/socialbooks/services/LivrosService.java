package com.aftermidnight.socialbooks.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.aftermidnight.socialbooks.domain.Comentario;
import com.aftermidnight.socialbooks.domain.Livro;
import com.aftermidnight.socialbooks.exceptions.LivroNaoEncontradoException;
import com.aftermidnight.socialbooks.repository.ComentariosRepository;
import com.aftermidnight.socialbooks.repository.LivrosRepository;

@Service
public class LivrosService {

	@Autowired
	private LivrosRepository livrosRepository;
	
	@Autowired
	private ComentariosRepository comentariosRepository;
	
	
	public List<Livro> listar(){	
		return livrosRepository.findAll();
	}
	
	public Livro buscar (Long id){
		Livro livro = livrosRepository.findOne(id);	
		
		if(livro == null ){
			throw new LivroNaoEncontradoException("O livro não pôde encontrado.");
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
			throw new LivroNaoEncontradoException("O livro não pôde ser encontrado.");
		}
	}
	
	public void atualizar(Livro livro) {
		verificarExistencia(livro);
		livrosRepository.save(livro);
	}
	
	private void verificarExistencia(Livro livro) {
		buscar(livro.getId());
	}

	public Comentario adicionarComentario(Long idLivro, Comentario comentario) {
		Livro livro = buscar(idLivro);
		comentario.setLivro(livro);
		if(comentario.getData() == null)comentario.setData(new Date());
		return comentariosRepository.save(comentario);
		
	}

	public List<Comentario> listarComentario(Long idLivro) {
		Livro livro = buscar(idLivro);
		return livro.getComentarios();
		
	}
	
	
}
