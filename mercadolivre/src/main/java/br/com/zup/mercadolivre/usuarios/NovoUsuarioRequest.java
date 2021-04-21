package br.com.zup.mercadolivre.usuarios;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.compartilhado.SenhaLimpa;

public class NovoUsuarioRequest {

	@Email
	@NotBlank
	private String login;
	
	@NotBlank
	@Size(min = 6)
	private String senha;

	public NovoUsuarioRequest(@Email @NotBlank String login, @NotBlank @Size(min = 6) String senha) {
		this.login = login;
		this.senha = senha;
	}

	public Usuario toModel() {
		return new Usuario(this.login, new SenhaLimpa(senha));
	}
	
}
