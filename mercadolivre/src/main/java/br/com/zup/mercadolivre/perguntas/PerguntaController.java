package br.com.zup.mercadolivre.perguntas;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
public class PerguntaController {
	
	private final Emails emails;
	
	public PerguntaController(Emails emails) {
		this.emails = emails;
	}

	@PersistenceContext
	private EntityManager em;
	
	@PostMapping(value = "/produtos/{id}/perguntas")
	@Transactional
	public ResponseEntity<?> cadastra(@PathVariable("id") Long idProduto, @RequestBody NovaPerguntaRequest request, @AuthenticationPrincipal Usuario usuarioLogado) {
		Produto produto = em.find(Produto.class, idProduto);
		if (produto == null) { return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); }
		
		Pergunta pergunta = request.toModel(produto, usuarioLogado);
		em.persist(pergunta);
		emails.novaPergunta(pergunta);
		return ResponseEntity.ok().build();
	}
	
}
