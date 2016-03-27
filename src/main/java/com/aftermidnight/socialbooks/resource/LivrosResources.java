package com.aftermidnight.socialbooks.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aftermidnight.socialbooks.domain.Livro;
import com.aftermidnight.socialbooks.repository.LivrosRepository;

@RestController
@RequestMapping("/livros")
public class LivrosResources {

	@Autowired
	private LivrosRepository livrosRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Livro>> listar(){
		List<Livro> livros = livrosRepository.findAll();
		return ResponseEntity.ok().body(livros);
	}
	
	@RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT})
	public ResponseEntity<?> salvar(@RequestBody Livro livro){ //@RequestBody: pega as infos da requisicao e tenta fazer o bind no objeto
		livrosRepository.save(livro);
		
		//criar a URI do novo objeto que foi salvo
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<?> buscar(@PathVariable("id") Long id){ //@PathVariable: pega o valor da variavel na uri e seta no metodo
		 Livro livro = livrosRepository.findOne(id);
		 
		 if(livro == null){
			 return ResponseEntity.notFound().build();
		 }
		 return ResponseEntity.status(HttpStatus.OK).body(livro);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<Void> remover(@PathVariable("id") Long id){
		try{
			livrosRepository.delete(id);
		}catch(EmptyResultDataAccessException e){
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id){
		try{
			livro.setId(id);
			livrosRepository.save(livro);
		}catch(EmptyResultDataAccessException e){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
		
	}
}
