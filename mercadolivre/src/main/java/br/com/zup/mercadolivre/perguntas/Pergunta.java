package br.com.zup.mercadolivre.perguntas;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.zup.mercadolivre.produtos.Produto;
import br.com.zup.mercadolivre.usuarios.Usuario;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String titulo;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime criadoEm;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private Produto produto;

	@ManyToOne
	private Usuario usuarioDaPergunta;

	public Pergunta(@NotBlank String titulo, @NotBlank @Valid Produto produto,
			@NotBlank @Valid Usuario usuarioDaPergunta) {
		super();
		this.titulo = titulo;
		this.criadoEm = LocalDateTime.now();
		this.produto = produto;
		this.usuarioDaPergunta = usuarioDaPergunta;
	}

	@Override
	public String toString() {
		return "Pergunta [id=" + id + ", titulo=" + titulo + ", criadoEm=" + criadoEm + ", produto=" + produto
				+ ", usuarioDaPergunta=" + usuarioDaPergunta.getEmail() + "]";
	}

	public String getTitulo() {
		return titulo;
	}

	public Produto getProduto() {
		return produto;
	}

}
