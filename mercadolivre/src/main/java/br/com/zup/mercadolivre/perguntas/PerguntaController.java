package br.com.zup.mercadolivre.perguntas;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
	
	private final EnviaEmailVendedor enviaEmailVendedor;
	
	public PerguntaController(EnviaEmailVendedor enviaEmailVendedor) {
		super();
		this.enviaEmailVendedor = enviaEmailVendedor;
	}

	@PersistenceContext
	private EntityManager em;
	
	@PostMapping(value = "/produtos/{id}/perguntas")
	@Transactional
	public ResponseEntity<?> cadastra(@PathVariable("id") Long idProduto, @RequestBody NovaPerguntaRequest request, @AuthenticationPrincipal Usuario usuarioLogado) {
		Produto produto = em.find(Produto.class, idProduto);
		
		Pergunta pergunta = request.toModel(em, produto, usuarioLogado);
		em.persist(pergunta);
		enviaEmailVendedor.envia(produto, pergunta, usuarioLogado);
		return ResponseEntity.ok().body(pergunta.toString());
	}
	
}
