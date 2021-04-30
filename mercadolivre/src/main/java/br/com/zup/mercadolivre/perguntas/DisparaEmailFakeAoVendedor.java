package br.com.zup.mercadolivre.perguntas;

import org.springframework.stereotype.Component;

@Component
public class DisparaEmailFakeAoVendedor implements EnviaEmailVendedor {

	public void envia(Pergunta pergunta) {
		System.out.println("De: usuario@gmail.com");
		System.out.println("Para: admin@gmail.com");
		System.out.println("Mensagem: nova pergunta");
		
	}

	
	
}
