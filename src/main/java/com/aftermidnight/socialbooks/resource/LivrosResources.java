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

import com.aftermidnight.socialbooks.domain.Comentario;
import com.aftermidnight.socialbooks.domain.Livro;
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
		Livro livro = livrosService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(livro);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<Void> remover(@PathVariable("id") Long id){
		livrosService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id){
		livro.setId(id);
		livrosService.atualizar(livro);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/{id}/comentarios")
	public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long idLivro, @RequestBody Comentario comentario){
		comentario = livrosService.adicionarComentario(idLivro, comentario);

		//sempre vamos bsucar todos os comentarios do livro, por isso a URI de retorno é justamente a uri
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	

	@RequestMapping(method = RequestMethod.GET, value="/{id}/comentarios")
	public ResponseEntity<List<Comentario>> buscarComentario(@PathVariable("id") Long idLivro){
		List<Comentario> comentarios = livrosService.listarComentario(idLivro);
		
		return ResponseEntity.ok(comentarios);
		
	}
	
}
