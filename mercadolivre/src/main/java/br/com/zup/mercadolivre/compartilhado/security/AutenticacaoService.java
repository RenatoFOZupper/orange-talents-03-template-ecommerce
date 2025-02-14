package br.com.zup.mercadolivre.compartilhado.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.zup.mercadolivre.usuarios.Usuario;
import br.com.zup.mercadolivre.usuarios.UsuarioRepository;

@Component
public class AutenticacaoService implements UserDetailsService{
	
	private final UsuarioRepository repository;
	
	public AutenticacaoService(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = repository.findByEmail(username);
		
		if (usuario.isPresent()) {
			return usuario.get();
		}
		
		throw new UsernameNotFoundException("Dados inválidos");
	}

	
	
}
