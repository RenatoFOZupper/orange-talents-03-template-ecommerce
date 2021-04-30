package br.com.zup.mercadolivre.perguntas;

import javax.persistence.EntityManager;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zup.mercadolivre.produtos.Produto;
import br.com.zup.mercadolivre.usuarios.Usuario;

public class NovaPerguntaRequest {

	@JsonProperty
	private String titulo;
	
	@Deprecated
	public NovaPerguntaRequest() {
	}

	@JsonCreator
	public NovaPerguntaRequest(String titulo) {
		super();
		this.titulo = titulo;
	}
	
	public Pergunta toModel(EntityManager em, Produto produto, Usuario usuarioLogado) {
		
		
		return new Pergunta(this.titulo, produto, usuarioLogado);
	}
	
	
}
