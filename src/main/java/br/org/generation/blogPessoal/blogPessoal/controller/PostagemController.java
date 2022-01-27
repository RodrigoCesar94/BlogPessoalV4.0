package br.org.generation.blogPessoal.blogPessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.blogPessoal.blogPessoal.model.Postagem;
import br.org.generation.blogPessoal.blogPessoal.repository.PostagemRepository;

@RestController // indica para o Spring que essa classe será identificada como restController
@RequestMapping("/postagens") // define o nosso endpoint, isto é, o URI pelo qual nossa classe será acessada e
// devemos indicar um parâmetro no qual a requisição passa a consumir essa classe. 


@CrossOrigin(origins = "*",allowedHeaders = "*")
// essa sinal de * colocado em entre aspas duplas define que nossa classe pode
					// ser acessada por qualquer origem o allowedHeaders aumenta este acesso para todos. 

public class PostagemController {

	@Autowired
	private PostagemRepository repository; // lembre-se que isso aqui é uma interface portanto não conseguimos
											// instanciar e
//então usamos injeção de dependência, deixando o trabalho de instanciar a intergace para o Spring através do @AutoWired trazendo os métodos 

	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}") // isso aqui é um método lambda// este parâmetro é enviado
	public ResponseEntity<Postagem> getById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/titulo/{titulo}") // aqui está sendo mapeado um subcaminho
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping // criado endpoint de POST
	// o RequestBosy aqui é porque na postagem você tem 
		// uma serie de informações como título, data(automatica conforme configurado em Model) e texto, ortando você solicitará essas
		// informações via body
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	@PutMapping //criado endpoint de POST. o nome do método em verde, deve ser sempre uma palavra reservada pois invoca um método exclusivo personalizado.
	public ResponseEntity<Postagem> put (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}	
	 
	@DeleteMapping("{id}")
	public void delete (@PathVariable long id) {
	repository.deleteById(id);	
		
	}
	
	
}
