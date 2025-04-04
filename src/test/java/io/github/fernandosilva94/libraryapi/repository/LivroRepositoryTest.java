package io.github.fernandosilva94.libraryapi.repository;

import io.github.fernandosilva94.libraryapi.model.Autor;
import io.github.fernandosilva94.libraryapi.model.GeneroLivro;
import io.github.fernandosilva94.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("90887-84875");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("As Cronicas de um dev");
        livro.setDataPublicacao(LocalDate.of(2025, 3, 19));

        Autor autor = new Autor();
        autor.setNome("Fernando");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1991, 11, 30));

        livro.setAutor(autor);

        Livro livroSalvo = livroRepository.save(livro);
        System.out.println("conferindo livro salvo: " + livroSalvo);
    }
    @Test
    void atualizarAutorDoLivro() {
        UUID idLivro = UUID.fromString("5423cc2b-4031-4113-b44a-b13d742755ee");
        var livroAtt = livroRepository.findById(idLivro).orElse(null);

        UUID idAutor = UUID.fromString("ec2d3648-a3e5-44b9-81e7-ce32f6eb07a0");
        var autorAtt = autorRepository.findById(idAutor).orElse(null);

        livroAtt.setAutor(autorAtt);

        livroRepository.save(livroAtt);
    }
    @Test
    void listarLivrosAutor() {
        var id = UUID.fromString("ec2d3648-a3e5-44b9-81e7-ce32f6eb07a0");
        var autor = autorRepository.findById(id).get();

        List<Livro> livrosLista =  livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);
        autor.getLivros().forEach(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQL() {
        var resultado = livroRepository.listarTodosOrdenadosPorTituloAndPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGeneroLivroAutoresBrasileiro() {
        var resultado = livroRepository.listarGeneroAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarLivroPorGenero() {
        var resultado = livroRepository.findByGenero(GeneroLivro.FICCAO);
        resultado.forEach(System.out::println);
    }

    @Test
    void listarLivroPorGeneroPosicaoParametro() {
        var resultado = livroRepository.findByGeneroPosicaoParametro(GeneroLivro.FICCAO, "preco");
        resultado.forEach(System.out::println);
    }
}