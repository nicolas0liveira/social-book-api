package com.aftermidnight.socialbooks.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException{

	public RecursoNaoEncontradoException(){
		super();
	}
	
	public RecursoNaoEncontradoException(String msg){
		super(msg);
	}

	public RecursoNaoEncontradoException(String msg, Throwable causa){
		super(msg, causa);
	}
}
