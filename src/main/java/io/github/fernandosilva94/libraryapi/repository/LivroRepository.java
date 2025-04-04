package io.github.fernandosilva94.libraryapi.repository;

import io.github.fernandosilva94.libraryapi.model.Autor;
import io.github.fernandosilva94.libraryapi.model.GeneroLivro;
import io.github.fernandosilva94.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * @See LivroRepositoryTest
 */

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    List<Livro> findByAutor(Autor autor);

    @Query(" select l from Livro as l order by l.titulo, l.preco ")
    List<Livro> listarTodosOrdenadosPorTituloAndPreco();

    @Query("""
            select l.genero
            from Livro l
            join l.autor a
            where a.nacionalidade = 'Brasileira'
            order by l.genero
            """)
    List<String> listarGeneroAutoresBrasileiros();

    @Query(" select l from Livro l where l.genero = :genero ")
    List<Livro> findByGenero(@Param("genero") GeneroLivro generoLivro);

    @Query(" select l from Livro l where l.genero = ?1 order by ?2 ")
    List<Livro> findByGeneroPosicaoParametro(GeneroLivro generoLivro, String nomePropriedade);
}
