package br.com.zup.mercadolivre.opinioes;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Opinioes {

	private Set<OpiniaoProduto> opinioes;

	public Opinioes(Set<OpiniaoProduto> opinioes) {
		this.opinioes = opinioes;
	}
	
	//Function responsavel por mapear as opinioes de um produto

	public <T> Set<T> opinioesMapeadas(Function<OpiniaoProduto, T> function) {
		return this.opinioes.stream().map(function).collect(Collectors.toSet());
	}
	
	//Calcula a nota media baseado nas notas das opinioes do produto

	public Double mediaNotas() {
		Set<Integer> notas = opinioesMapeadas(opiniao -> opiniao.getNota());
		return notas.stream().mapToInt(nota -> nota).average().orElse(0.0);
	}
	
	//calcula o total de notas de um produto

	public Integer totalNotas() {
		return this.opinioes.size();
	}

}
