package br.com.zup.mercadolivre.compra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {

	@NotNull
	private Long idProduto;
	
	@Positive
	@NotNull
	private Integer quantidade;
	
	@NotNull
	private Gateway gateway;

	public NovaCompraRequest(@NotNull Long idProduto, @Positive @NotNull Integer quantidadeInformada, @NotNull Gateway formaPagamentoSelecionado) {
		this.idProduto = idProduto;
		this.quantidade = quantidadeInformada;
		this.gateway = formaPagamentoSelecionado;
	}

	public Long getIdProduto() {
		return idProduto;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}

	public Gateway getGateway() {
		return gateway;
	}
}
