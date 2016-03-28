package com.aftermidnight.socialbooks.exceptions;


public class AutorJaExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1869300553614629710L;

	public AutorJaExistenteException(String mensagem) {
		super(mensagem);
	}
	
	public AutorJaExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
