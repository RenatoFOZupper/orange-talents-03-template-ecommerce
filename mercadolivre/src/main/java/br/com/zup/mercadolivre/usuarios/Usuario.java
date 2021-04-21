package br.com.zup.mercadolivre.usuarios;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*
	 * Login, senha e data de criacao nao podem ser nulos e senha deve conter um
	 * minimo de 6 caracteres
	 */
	
	@NotNull
	@Email
	@Column(nullable = false, unique = true)
	private String email;
	
	@NotNull
	@Length(min = 6)
	@Column(nullable = false)
	private String senha;
	
	@PastOrPresent
	@Column(nullable = false)
	private LocalDateTime dataDeCriacao = LocalDateTime.now();
	
	
	/*
	 * @Deprecated -- Hibernate Only 
	 */
	public Usuario() {
	}

	public Usuario(@NotNull @Email String email, @Valid @NotNull @Length(min = 6) SenhaLimpa senhaLimpa) {
		Assert.isTrue(StringUtils.hasLength(email), "email não pode estar em branco");
		Assert.notNull(senhaLimpa, "o objeto do tipo senha limpa não pode ser nulo");
		this.email = email;
		this.senha = senhaLimpa.hash();
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + email + ", dataDeCriacao=" + dataDeCriacao + "]";
	}
	

}
