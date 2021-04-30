package br.com.zup.mercadolivre.perguntas;

import br.com.zup.mercadolivre.produtos.Produto;
import br.com.zup.mercadolivre.usuarios.Usuario;

public interface EnviaEmailVendedor {

	void envia(Produto produto, Pergunta pergunta, Usuario usuarioLogado);
	
}
