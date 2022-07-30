package br.com.mercadolivre.service;

import br.com.commons.dto.UsuarioDTO;
import br.com.mercadolivre.domain.Usuario;
import br.com.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;

import static br.com.mercadolivre.shared.EmailValidation.pattern;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

//    @Autowired
//    UsuarioTransformMapper mapper;

    @Transactional
    public UsuarioDTO criarUsuario(UsuarioDTO dto) {
        Usuario usuario = mapper.toEntity(dto);
        Matcher mather = pattern.matcher(usuario.getEmail());

        UsuarioDTO validado = null;
        if (mather.find()) {
            validado = mapper.toDTO(usuarioRepository.save(usuario));
        } else {
            validado = new UsuarioDTO();
        }
        return validado;
    }

    @Transactional
    public UsuarioDTO buscarAutorPorId(UUID id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.isPresent() ? mapper.toDTO(usuario.get()) : new UsuarioDTO();
    }

    public UsuarioDTO buscarAutorPorEmail(UsuarioDTO dto) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(dto.getEmail());
        return usuario.isPresent() ? mapper.toDTO(usuario.get()) : new UsuarioDTO();
    }
}
