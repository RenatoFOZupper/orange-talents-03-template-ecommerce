package br.com.zup.mercadolivre.produtos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.compartilhado.ProibeCaracteristicaComNomeIgualValidator;
import br.com.zup.mercadolivre.usuarios.Usuario;

@RestController
public class ProdutoController {

	@PersistenceContext
	EntityManager em;

	@InitBinder
	public void init(WebDataBinder web) {
		web.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
	}

	@PostMapping(value = "/produtos")
	@Transactional
	public ResponseEntity<String> cadastra(@RequestBody @Valid NovoProdutoRequest request,
			@AuthenticationPrincipal Usuario dono) {
		Produto produto = request.toModel(em, dono);
		em.persist(produto);
		return ResponseEntity.ok().build();
	}

}
