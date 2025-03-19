package io.github.fernandosilva94.libraryapi.repository;

import io.github.fernandosilva94.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
}
