package com.aftermidnight.socialbooks.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonInclude(Include.NON_NULL)//para mostrar apenas quando não o campo não for null (Frmw Jackson, que o Spring usa para serializar a informação. Ele quem usa essa anotação)
	private Long id;
	
	private String nome;
	
	@JsonInclude(Include.NON_NULL)
	private Date publicacao;
	
	@JsonInclude(Include.NON_NULL)
	private String editora;
	
	@JsonInclude(Include.NON_NULL)
	private String resumo;
	
	@Transient
	@JsonInclude(Include.NON_NULL)
	private List<Comentario> cometarios;
	
	@JsonInclude(Include.NON_NULL)
	private String autor;

	public Livro(){
		super();
	}
	
	
	public Livro(String nome){
		super();
		this.nome = nome;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Date publicacao) {
		this.publicacao = publicacao;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public List<Comentario> getCometarios() {
		return cometarios;
	}

	public void setCometarios(List<Comentario> cometarios) {
		this.cometarios = cometarios;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	
}
