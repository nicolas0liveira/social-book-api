package com.aftermidnight.socialbooks.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aftermidnight.socialbooks.domain.Livro;
import com.aftermidnight.socialbooks.exceptions.RecursoNaoEncontradoException;
import com.aftermidnight.socialbooks.services.LivrosService;

@RestController
@RequestMapping("/livros")
public class LivrosResources {

	@Autowired
	private LivrosService livrosService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Livro>> listar(){
		List<Livro> livros = livrosService.listar();
		return ResponseEntity.ok().body(livros);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> salvar(@RequestBody Livro livro){ //@RequestBody: pega as infos da requisicao e tenta fazer o bind no objeto
		livrosService.salvar(livro);
		
		//criar a URI do novo objeto que foi salvo
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<?> buscar(@PathVariable("id") Long id){ //@PathVariable: pega o valor da variavel na uri e seta no metodo
		Livro livro;
		 
		 try{
			 livro = livrosService.buscar(id);
		 }catch(RecursoNaoEncontradoException e ){
			 return ResponseEntity.notFound().build();
		 }
		 
		 return ResponseEntity.status(HttpStatus.OK).body(livro);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<Void> remover(@PathVariable("id") Long id){
		try{
			livrosService.deletar(id);
		}catch(RecursoNaoEncontradoException e){
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id){
		try{
			livro.setId(id);
			livrosService.atualizar(livro);
		}catch(RecursoNaoEncontradoException e){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
		
	}
}
