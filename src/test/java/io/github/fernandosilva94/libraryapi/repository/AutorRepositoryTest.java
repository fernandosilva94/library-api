package io.github.fernandosilva94.libraryapi.repository;

import io.github.fernandosilva94.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {
    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        var autorSalvo = autorRepository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("95e2b495-0879-46a0-9d4c-e43394323a4b");

        Optional<Autor> autor = autorRepository.findById(id);
        if (autor.isPresent()) {
            Autor autorEncontrado = autor.get();
            System.out.println("Dados do autor: " + autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1977, 11, 30));
            autorRepository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTodosTest() {
        List<Autor> lista = autorRepository.findAll();
        lista.forEach(System.out:: println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de autores: " + autorRepository.count());
    }

    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("95e2b495-0879-46a0-9d4c-e43394323a4b");
        autorRepository.deleteById(id);
    }
}
