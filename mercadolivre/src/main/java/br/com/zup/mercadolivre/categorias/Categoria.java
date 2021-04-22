package br.com.zup.mercadolivre.categorias;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

@Entity
@Table(name = "categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(unique = true, nullable = false)
	private String nome;
	
	@ManyToOne
	private Categoria categoriaMae;
	
	/*
	 * @Deprecated - Hibernate Only
	 */
	public Categoria() { }

	public Categoria(@NotNull String nome) {
		super();
		Assert.notNull(nome, "o nome não pode ser nulo");
		this.nome = nome;
	}

	public void setParente(Categoria categoriaMae) {
		this.categoriaMae = categoriaMae;
	}
	
}
