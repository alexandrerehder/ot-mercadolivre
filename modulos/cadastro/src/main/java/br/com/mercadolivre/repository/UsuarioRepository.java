package br.com.mercadolivre.repository;

import br.com.mercadolivre.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    @Query("SELECT u FROM Usuario a WHERE a.email =:email")
    Optional<Usuario> findByEmail(String email);
}
