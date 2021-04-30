package br.com.zup.mercadolivre.opinioes;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.zup.mercadolivre.produtos.Produto;
import br.com.zup.mercadolivre.usuarios.Usuario;

public class NovaOpiniaoProdutoRequest {

	@Min(value = 1)
	@Max(value = 5)
	@NotNull
	private Integer nota;

	@NotBlank
	private String titulo;

	@NotBlank
	@Length(max = 500)
	private String descricao;

	public NovaOpiniaoProdutoRequest(@Min(1) @Max(5) @NotBlank Integer nota, @NotBlank String titulo,
			@NotBlank @Length(max = 500) String descricao) {
		this.nota = nota;
		this.titulo = titulo;		this.descricao = descricao;
	}
	
	// Método que converte um objeto NovaOpiniaoProdutoRequest para OpiniaoProduto

	public OpiniaoProduto toModel(@Valid Produto produto, @Valid Usuario usuarioLogado) {
		return new OpiniaoProduto(this.nota, this.titulo, this.descricao, produto, usuarioLogado);
	}

}
