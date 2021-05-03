package br.com.zup.mercadolivre.produtodetalhes;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.produtos.Produto;

@RestController
public class ProdutoDetalhesController {
	
	@PersistenceContext
	private EntityManager em;

	@GetMapping(value = "/produtos/{id}")
	@Transactional
	public ResponseEntity<ProdutoDetalhesResponse> detalhesDoProduto(@PathVariable Long id ) {
		Produto produto = em.find(Produto.class, id);
		if (produto == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		ProdutoDetalhesResponse response = new ProdutoDetalhesResponse(produto);
		return ResponseEntity.ok().body(response);
	}
	
}
