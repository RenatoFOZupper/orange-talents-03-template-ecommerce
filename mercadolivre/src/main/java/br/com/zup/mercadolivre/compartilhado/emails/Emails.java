package br.com.zup.mercadolivre.compartilhado.emails;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import br.com.zup.mercadolivre.compra.Compra;
import br.com.zup.mercadolivre.perguntas.Pergunta;

@Component
public class Emails {

	private Mailer mailer;
	
	public Emails(Mailer mailer) {
		this.mailer = mailer;
	}

	public void novaPergunta(@NotNull @Valid Pergunta pergunta) {
		mailer.send("<html>...</html>","Nova pergunta...",pergunta.getPotencialInteressado().getEmail(),
				"novapergunta@nossomercadolivre.com",pergunta.getDonoProduto().getEmail());

	}
	
	public void novaCompra(@NotNull @Valid Compra compra) {
		mailer.send("<html>...</html>","Nova possível compra...",compra.getComprador().getEmail(),
				"novacompra@nossomercadolivre.com", compra.getProduto().getDono().getEmail());

	}
}
	
