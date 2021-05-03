package br.com.zup.mercadolivre.compra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.compartilhado.emails.Emails;
import br.com.zup.mercadolivre.produtos.Produto;
import br.com.zup.mercadolivre.usuarios.Usuario;

@RestController
public class CompraController {

	@PersistenceContext
	private EntityManager em;
	
	private final Emails emails;
	
	public CompraController(Emails emails) {
		this.emails = emails;
	}

	@PostMapping(value = "/compra")
	@Transactional
	public String efetuaCompra(@RequestBody @Valid NovaCompraRequest request, @AuthenticationPrincipal Usuario possivelComprador) throws BindException {
		
		Produto produtoInformado = em.find(Produto.class, request.getIdProduto());
		Integer quatidadeInformada = request.getQuantidade();
		Gateway formaPagamentoSelecionado = request.getGateway();
		
		boolean abateEstoque = produtoInformado.abateEstoque(request.getQuantidade());
		if (abateEstoque) {
			Compra compra = new Compra(produtoInformado, quatidadeInformada, possivelComprador, formaPagamentoSelecionado);
			em.persist(compra);
			emails.novaCompra(compra);
			String urlRedirecionamento = "retorno-" + formaPagamentoSelecionado + "/";
			return formaPagamentoSelecionado + ".com?" +compra.getId() + "&redirectUrl=" + urlRedirecionamento;
		}else {
			BindException qtdIndisponivel = new BindException(request, "request");
			qtdIndisponivel.reject(null, "A quantidade selecionada excede o limite disponível no estoque = " + produtoInformado.getQuantidade());
			throw qtdIndisponivel;
		}

	}
}

