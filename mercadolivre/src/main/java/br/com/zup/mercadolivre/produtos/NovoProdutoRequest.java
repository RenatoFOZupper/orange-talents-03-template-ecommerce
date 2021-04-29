package br.com.zup.mercadolivre.produtos;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import br.com.zup.mercadolivre.categorias.Categoria;
import br.com.zup.mercadolivre.compartilhado.annotations.ExistsId;
import br.com.zup.mercadolivre.compartilhado.annotations.UniqueValue;
import br.com.zup.mercadolivre.usuarios.Usuario;

public class NovoProdutoRequest {

	@NotBlank
	@UniqueValue(domainClass = Produto.class, fieldName = "nome")
	private String nome;

	@NotNull
	@Positive
	private BigDecimal valor;

	@NotNull
	@Positive
	private Integer quantidade;

	@NotBlank
	@Size(max = 1000)
	private String descricao;

	@NotNull
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;

	@Size(min = 3)
	@Valid
	private Set<NovaCaracteristicaProdutoRequest> caracteristicas = new HashSet<>();

	public NovoProdutoRequest(@NotBlank String nome, @Positive BigDecimal valor, @Positive Integer quantidade,
			@NotBlank @Size(max = 1000) String descricao, Long idCategoria,
			@Size(min = 3) @Valid Set<NovaCaracteristicaProdutoRequest> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.caracteristicas.addAll(caracteristicas);
	}

	public Set<NovaCaracteristicaProdutoRequest> getCaracteristicas() {
		return caracteristicas;
	}

	public Produto toModel(EntityManager em, Usuario dono) {
		Categoria categoria = em.find(Categoria.class, idCategoria);
		return new Produto(nome, valor, quantidade, descricao, categoria, caracteristicas, dono);
	}

	@Override
	public String toString() {
		return "NovoProdutoRequest [nome=" + nome + ", valor=" + valor + ", quantidade=" + quantidade + ", descricao="
				+ descricao + ", idCategoria=" + idCategoria + ", caracteristicas=" + caracteristicas + "]";
	}

	public Set<String> buscaCaracteristicasIguais() {
		HashSet<String> nomesIguais = new HashSet<>();
		HashSet<String> resultados = new HashSet<>();
		for (NovaCaracteristicaProdutoRequest caracteritica : caracteristicas) {
			String nome = caracteritica.getNome();
			if (!nomesIguais.add(nome)) {
				resultados.add(nome);
			}
		}
		return resultados;
	}

}
