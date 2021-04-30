package br.com.zup.mercadolivre.perguntas;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import br.com.zup.mercadolivre.produtos.Produto;
import br.com.zup.mercadolivre.usuarios.Usuario;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String titulo;

	private LocalDate criadoEm;

	@ManyToOne
	private Produto produto;

	@ManyToOne
	private Usuario usuarioDaPergunta;

	public Pergunta(@NotBlank String titulo, @NotBlank @Valid Produto produto,
			@NotBlank @Valid Usuario usuarioDaPergunta) {
		this.titulo = titulo;
		this.criadoEm = LocalDate.now();
		this.produto = produto;
		this.usuarioDaPergunta = usuarioDaPergunta;
	}

}
