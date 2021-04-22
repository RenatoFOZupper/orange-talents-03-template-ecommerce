package br.com.zup.mercadolivre.categorias;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	
	public Optional<Categoria> findByNome(String nome);
}
