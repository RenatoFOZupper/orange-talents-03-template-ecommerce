package br.com.zup.mercadolivre.categorias;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaController {
	
	private final CategoriaRepository repository;

	public CategoriaController(CategoriaRepository repository) {
		this.repository = repository;
	}

	@PostMapping(value = "/categorias")
	@Transactional
	public ResponseEntity<Categoria> cadastra(@RequestBody @Valid NovaCategoriaRequest request) {
		Categoria categoria = request.toModel(repository);
		categoria = repository.save(categoria);
		return ResponseEntity.ok().body(categoria);
	}
	
}
