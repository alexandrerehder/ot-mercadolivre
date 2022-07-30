package br.com.mercadolivre.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    @NotBlank
    private String nome;
    @Column(unique=true) @NotNull @NotBlank
    private String login;
    @NotNull @NotBlank @Size(min=6)
    private String senha;
    private LocalDateTime dataCriacao = LocalDateTime.now();
}
