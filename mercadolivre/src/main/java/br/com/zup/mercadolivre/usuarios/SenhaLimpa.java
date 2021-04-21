package br.com.zup.mercadolivre.usuarios;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

/**
 * Representa uma senha limpa no sistema
 */

public class SenhaLimpa {

	private String senha;

	public SenhaLimpa(@NotBlank @Length(min = 6) String senha) {
		Assert.hasLength(senha, "senha não pode estar em branco");
		Assert.isTrue(senha.length() >= 6, "senha tem que ter no minimo 6 caracteres");
		this.senha = senha;
	}
	
	public String hash() {
		return this.senha = new BCryptPasswordEncoder().encode(senha);
	}
	
	
	
}
