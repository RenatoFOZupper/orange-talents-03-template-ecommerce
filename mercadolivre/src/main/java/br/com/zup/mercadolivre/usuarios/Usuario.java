package br.com.zup.mercadolivre.usuarios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails{
	private static final long serialVersionUID = 1L;

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
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();
	
	
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
	

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + email + ", dataDeCriacao=" + dataDeCriacao + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	

}
