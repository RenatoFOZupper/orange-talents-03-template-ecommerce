package br.com.zup.mercadolivre.compra;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.produtos.Produto;
import br.com.zup.mercadolivre.usuarios.Usuario;

@Entity
@Table(name = "compras")
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private StatusCompra status = StatusCompra.INICIADA;
	
	@ManyToOne
	@NotNull
	private Produto produto;
	
	@NotNull
	private Integer quantidade;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Gateway gateway;
	
	@ManyToOne
	@NotNull
	private Usuario comprador;
	
	@Deprecated
	public Compra() { }

	public Compra(Produto produtoInformado, Integer quantidadeInformada, Usuario possivelComprador, Gateway formaPagamentoSelecionado) {
		this.produto = produtoInformado;
		this.quantidade = quantidadeInformada;
		this.comprador = possivelComprador;
		this.gateway = formaPagamentoSelecionado;
	}

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Usuario getComprador() {
		return comprador;
	}
	
	public Gateway getGateway() {
		return gateway;
	}
	
	@Override
	public String toString() {
		return "Compra [id=" + id + ", status=" + status + ", produto=" + produto + ", quantidade=" + quantidade
				+ ", gateway=" + gateway + ", comprador=" + comprador + "]";
	}

	public BigDecimal getValor() {
		return produto.getValor();
	}
	
	public StatusCompra getStatus() {
		return status;
	}

	
}
