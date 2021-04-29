package br.com.zup.mercadolivre.opinioes;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.produtos.Produto;
import br.com.zup.mercadolivre.usuarios.Usuario;

@RestController
public class OpiniaoController {

	@PersistenceContext
	private EntityManager em;

	@PostMapping(value = "/produtos/{id}/opinioes")
	@Transactional
	public ResponseEntity<?> adiciona(@RequestBody @Valid NovaOpiniaoProdutoRequest request, @PathVariable Long id,
			@AuthenticationPrincipal Usuario usuarioLogado) {
		Produto produto = em.find(Produto.class, id);

		if (produto == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto inválido...");

		OpiniaoProduto opiniaoProduto = request.toModel(em, produto, usuarioLogado);
		em.persist(opiniaoProduto);
		return ResponseEntity.ok().body("Opinião cadastrada com sucesso!!");
	}

}
