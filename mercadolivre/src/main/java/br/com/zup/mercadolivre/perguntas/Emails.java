package br.com.zup.mercadolivre.perguntas;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

@Service
public class Emails {

	private Mailer mailer;
	
	public Emails(Mailer mailer) {
		this.mailer = mailer;
	}

	public void novaPergunta(@NotNull @Valid Pergunta pergunta) {
		mailer.send("<html>...</html>","Nova pergunta...",pergunta.getPotencialInteressado().getEmail(),
				"novapergunta@nossomercadolivre.com",pergunta.getDonoProduto().getEmail());

	}
}
	
