package br.com.zup.mercadolivre.usuarios;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.compartilhado.annotations.EmailUnico;

public class NovoUsuarioRequest {

	@Email
	@NotBlank
	@EmailUnico(domainClass = Usuario.class, fieldName = "email")
	private String email;
	
	@NotBlank
	@Size(min = 6)
	
	private String senha;

	public NovoUsuarioRequest(@Valid @Email @NotBlank String email, @NotBlank @Size(min = 6) String senha) {
		this.email = email;
		this.senha = senha;
	}

	public Usuario toModel() {
		return new Usuario(email, new SenhaLimpa(senha));
	}
	
}
