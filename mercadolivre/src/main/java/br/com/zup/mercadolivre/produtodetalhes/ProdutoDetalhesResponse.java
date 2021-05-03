package br.com.zup.mercadolivre.produtodetalhes;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.zup.mercadolivre.opinioes.Opinioes;
import br.com.zup.mercadolivre.produtos.Produto;

public class ProdutoDetalhesResponse {

	private String nome;
	private String descricao;
	private BigDecimal preco;
	private Set<Map<String, String>> imagens = new HashSet<>();
	private Set<Map<String, String>> caracteristicas = new HashSet<>();
	private Set<Map<String, String>> perguntas = new HashSet<>();
	private Set<Map<String, String>> opinioes = new HashSet<>();
	private Double mediaNotas;
	private Integer totalOpinioes;
	

	public ProdutoDetalhesResponse(Produto produto) {
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.preco = produto.getValor();
		this.imagens = produto.mapeiaImagens(img -> { 
			return Map.of("links", img.getLink());
		});
		this.caracteristicas = produto.caracteristicasMapeadas(caracteristica -> {
			return Map.of("nome", caracteristica.getNome(), "descricao", caracteristica.getDescricao());
		});
		this.perguntas = produto.perguntasMapeadas(pergunta -> {
			return Map.of("titulo", pergunta.getTitulo());
		});
		
		Opinioes opinioes = produto.getOpinioes();
		
		this.opinioes = opinioes.opinioesMapeadas(opiniao -> {
			return Map.of("nota", opiniao.getNota().toString(), "titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
		});
		
		this.mediaNotas = opinioes.mediaNotas();
		this.totalOpinioes = opinioes.totalNotas();
		
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public BigDecimal getPreco() {
		return preco;
	}
	
	public Set<Map<String, String>> getImagens() {
		return imagens;
	}
	
	public Set<Map<String, String>> getCaracteristicas() {
		return caracteristicas;
	}
	
	public Set<Map<String, String>> getPerguntas() {
		return perguntas;
	}
	
	public Set<Map<String, String>> getOpinioes() {
		return opinioes;
	}
	
	public Double getMediaNotas() {
		return mediaNotas;
	}
	
	public Integer getTotalOpinioes() {
		return totalOpinioes;
	}
	
}
