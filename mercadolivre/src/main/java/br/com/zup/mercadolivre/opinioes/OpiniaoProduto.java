package br.com.zup.mercadolivre.opinioes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.zup.mercadolivre.produtos.Produto;
import br.com.zup.mercadolivre.usuarios.Usuario;
import io.jsonwebtoken.lang.Assert;

@Entity
public class OpiniaoProduto {
	
	/*
	 * A restrição óbvia é que a nota é no mínimo 1 e no máximo 5 
	 * Título é obrigatório 
	 * Descrição é obrigatório e tem no máximo 500 caracteres 
	 * usuário é obrigatório 
	 * o produto relacionado é obrigatório
	 */
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Min(value = 1)
	@Max(value = 5)
	@NotNull
	@Column(nullable = false)
	private Integer nota;

	@NotBlank
	@Column(nullable = false)
	private String titulo;

	@NotBlank
	@Length(max = 500)
	@Column(nullable = false)
	private String descricao;

	@Valid
	@ManyToOne
	private Produto produto;

	@Valid
	@ManyToOne
	private Usuario usuarioLogado;
	
	@Deprecated
	public OpiniaoProduto() { }

	public OpiniaoProduto(@Min(1) @Max(5) @NotNull Integer nota, @NotBlank String titulo,
			@NotBlank @Length(max = 500) String descricao, @Valid Produto produto, @Valid Usuario usuarioLogado) {
		Assert.isTrue(descricao.length() <= 500, "Descricao nao pode passar de 500 caracteres");
		Assert.notNull(usuarioLogado, "Usuario nao pode ser nulo");
		Assert.notNull(produto, "Produto nao pode ser nulo");
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.produto = produto;
		this.usuarioLogado = usuarioLogado;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getNota() {
		return nota;
	}

}
