package br.com.mercadolivre.mapper;

import br.com.commons.dto.UsuarioDTO;
import br.com.mercadolivre.domain.Usuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {

    private final ModelMapper mapper;

    public UsuarioDTO toDTO(Usuario usuario) {
        return mapper.map(usuario, UsuarioDTO.class);
    }

    public Usuario toEntity(UsuarioDTO dto) {
        return mapper.map(dto, Usuario.class);
    }
}
