package br.com.zup.mercadolivre.produtos;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.usuarios.Usuario;

@RestController
public class ImagemController {

	private final Uploader uploader;

	public ImagemController(Uploader uploader) {
		this.uploader = uploader;
	}
	
	@PersistenceContext
	private EntityManager em;

	@PostMapping(value = "/produtos/{id}/imagens")
	@Transactional
	public ResponseEntity<String> adicionaImagens(@PathVariable Long id, 
			@Valid NovaImagemRequest request, @AuthenticationPrincipal Usuario dono) {

		Produto produto = em.find(Produto.class, id);
		if (produto == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("produto " + id + " não encontrado");
		}

		if (!produto.pertenceAoUsuario(dono)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		Set<String> links = uploader.send(request.getImagens());
		produto.anexa(links);
		em.merge(produto);
		return ResponseEntity.ok().body("Efetuado upload com sucesso!!");
		
	}
}
