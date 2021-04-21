package br.com.zup.mercadolivre.compartilhado;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaLimpa {

	private String senha;

	public SenhaLimpa(String senha) {
		this.senha = senha;
	}
	
	public String hash() {
		return this.senha = new BCryptPasswordEncoder().encode(senha);
	}
	
	
	
}
