package com.aftermidnight.socialbooks.exceptions;

public class LivroNaoEncontradoException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public LivroNaoEncontradoException(){
		super();
	}
	
	public LivroNaoEncontradoException(String msg){
		super(msg);
	}

	public LivroNaoEncontradoException(String msg, Throwable causa){
		super(msg, causa);
	}
}
