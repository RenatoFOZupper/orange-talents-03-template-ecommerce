package br.com.zup.mercadolivre.perguntas;

import org.springframework.stereotype.Component;

import br.com.zup.mercadolivre.produtos.Produto;
import br.com.zup.mercadolivre.usuarios.Usuario;

@Component
public class DisparaEmailFakeAoVendedor implements EnviaEmailVendedor {

	public void envia(Produto produto, Pergunta pergunta, Usuario usuarioLogado) {
		System.out.println("De: " + usuarioLogado.getEmail());
		System.out.println("Para: " + produto.getDono().getEmail());
		System.out.println("Mensagem: " + pergunta.getTitulo());
		
	}

	
	
}
