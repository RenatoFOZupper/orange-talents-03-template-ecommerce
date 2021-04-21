package br.com.zup.mercadolivre.usuarios;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
	
	@PersistenceContext
	private EntityManager em;

	@PostMapping(value = "/usuarios")
	@Transactional
	public ResponseEntity<?> cadastra(@RequestBody @Valid NovoUsuarioRequest request) {
		Usuario novoUsuario = request.toModel();
		em.persist(novoUsuario);
		return ResponseEntity.ok().build();
	}
	
}
