package com.aftermidnight.socialbooks.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aftermidnight.socialbooks.domain.DetalheErro;
import com.aftermidnight.socialbooks.exceptions.AutorJaExistenteException;
import com.aftermidnight.socialbooks.exceptions.AutorNaoEncontradoException;
import com.aftermidnight.socialbooks.exceptions.LivroNaoEncontradoException;

/*
 * O Spring oferece um mecanismo para tratar as exceptions, afim de evitar o codigo poluido com os try-cacths.
 * Permite criar um interceptador e um listener para tratar tais exceptions 
 * 
 * Essa classe é justamente o interceptador (@ControllerAdvice)
 */

@ControllerAdvice
public class RessourceExceptionHandler {

	
	@ExceptionHandler(LivroNaoEncontradoException.class)
	public ResponseEntity<DetalheErro> handlerLivroNaoEncontradoException 
		(LivroNaoEncontradoException e ,HttpServletResponse response){
		
		DetalheErro erro = new DetalheErro();
		
		erro.setTitulo("Livro não encontrado");
		erro.setStatus(404L);
		erro.setTimestamp(System.currentTimeMillis());
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");//TODO: Criar uma pagina com detalhes sobre os possíveis erros
		
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(AutorJaExistenteException.class)
	public ResponseEntity<DetalheErro> handlerAutorJaExistenteException 
		(AutorJaExistenteException e ,HttpServletResponse response){
		
		DetalheErro erro = new DetalheErro();
		
		erro.setTitulo("Autor já existente");
		erro.setStatus(409L);
		erro.setTimestamp(System.currentTimeMillis());
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/409");//TODO: Criar uma pagina com detalhes sobre os possíveis erros
		
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	
	@ExceptionHandler(AutorNaoEncontradoException.class)
	public ResponseEntity<DetalheErro> handleAutorNaoEncontradoException
							(AutorNaoEncontradoException e, HttpServletRequest request) {
		
		DetalheErro erro = new DetalheErro();
		erro.setStatus(404L);
		erro.setTitulo("O autor não pôde ser encontrado");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalheErro> handleDataIntegrityViolationException
							(DataIntegrityViolationException e, HttpServletRequest request) {
		
		DetalheErro erro = new DetalheErro();
		erro.setStatus(400l);
		erro.setTitulo("Requisição inválida");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/400");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

}
