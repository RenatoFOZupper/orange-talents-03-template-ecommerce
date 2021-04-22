package br.com.zup.mercadolivre.categorias;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import br.com.zup.mercadolivre.compartilhado.annotations.UniqueValue;

public class NovaCategoriaRequest {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "categoria já cadastrado..")
	private String nome;
	private String categoriaMae;
	
	public NovaCategoriaRequest(@Valid String nome, String categoriaMae) {
		super();
		this.nome = nome;
		this.categoriaMae = categoriaMae;
	}

	
	public Categoria toModel(CategoriaRepository repository) {
		Categoria categoria = new Categoria(nome);
		Optional<Categoria> categoriaParente = repository.findByNome(categoriaMae);
		
		if (categoriaParente.isPresent()) {
			categoria.setParente(categoriaParente.get());
		}
		return categoria;
	}
	
}
