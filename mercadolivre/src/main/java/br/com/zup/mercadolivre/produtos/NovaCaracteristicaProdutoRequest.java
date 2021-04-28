package br.com.zup.mercadolivre.produtos;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class NovaCaracteristicaProdutoRequest {

private String nome;
	
	private String descricao;

	public NovaCaracteristicaProdutoRequest(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public CaracteristicaProduto toModel(@NotNull @Valid Produto produto) {
		return new CaracteristicaProduto(nome, descricao, produto);
	}
	
	@Override
	public String toString() {
		return "CaracteristicasDoProduto [nome=" + nome + ", descricao=" + descricao + "]";
	}
	
}
