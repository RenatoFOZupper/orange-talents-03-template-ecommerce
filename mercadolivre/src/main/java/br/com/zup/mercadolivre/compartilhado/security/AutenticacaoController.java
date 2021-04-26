package br.com.zup.mercadolivre.compartilhado.security;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutenticacaoController {
	
	private final AuthenticationManager authManager;
	private final TokenService tokenService;

	public AutenticacaoController(AuthenticationManager authManager, TokenService tokenService) {
		this.authManager = authManager;
		this.tokenService = tokenService;
	}



	@PostMapping(value = "/auth")
	public ResponseEntity<TokenResponse> autenticar(@RequestBody @Valid LoginRequest request) {
		UsernamePasswordAuthenticationToken dadosLogin = request.converter();
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			
			return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
		} catch (AuthenticationException e) {
			
			return ResponseEntity.badRequest().build();
		}
		
	}
	
}
