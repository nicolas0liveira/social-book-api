package com.aftermidnight.socialbooks.handler;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aftermidnight.socialbooks.domain.DetalheErro;
import com.aftermidnight.socialbooks.exceptions.RecursoNaoEncontradoException;

/*
 * O Spring oferece um mecanismo para tratar as exceptions, afim de evitar o codigo poluido com os try-cacths.
 * Permite criar um interceptador e um listener para tratar tais exceptions 
 * 
 * Essa classe é justamente o interceptador (@ControllerAdvice)
 */

@ControllerAdvice
public class RessourceExceptionHandler {

	
	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<DetalheErro> handlerRecursoNaoEncontradoException 
		(RecursoNaoEncontradoException e ,HttpServletResponse response){
		
		DetalheErro detalheErro = new DetalheErro();
		
		detalheErro.setTitulo("Recurso não encontrado");
		detalheErro.setStatus(404L);
		detalheErro.setTimestamp(System.currentTimeMillis());
		detalheErro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");//TODO: Criar uma pagina com detalhes sobre os possíveis erros
		
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalheErro);
	}
}
