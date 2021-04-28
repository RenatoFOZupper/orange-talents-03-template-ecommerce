package br.com.zup.mercadolivre.compartilhado;

import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.mercadolivre.produtos.NovoProdutoRequest;

public class ProibeCaracteristicaComNomeIgual implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoProdutoRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}

		NovoProdutoRequest request = (NovoProdutoRequest) target;
		Set<String> nomesIguais = request.buscaCaracteristicasIguais();
		if (!nomesIguais.isEmpty()) {
			errors.reject("caracteristicas", null, "Você tem caracteristicas iguais...");
		}

	}

}
