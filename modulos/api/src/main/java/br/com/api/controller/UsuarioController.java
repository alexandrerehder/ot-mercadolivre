package br.com.api.controller;

import br.com.api.queue.sender.UsuarioSender;
import br.com.commons.dto.CrudMethod;
import br.com.commons.dto.QueueRequestDTO;
import br.com.commons.dto.QueueResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Log4j2
@RestController
@RequestMapping(value = "/v1")
public class UsuarioController {

    @Autowired
    private UsuarioSender usuarioSender;

    @PostMapping(value = "public/autor/buscar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<QueueResponseDTO> buscarUsuario(@PathVariable("id") UUID id) {
        QueueResponseDTO response = new QueueResponseDTO();
        try {
            QueueRequestDTO request = new QueueRequestDTO();
            request.setObjeto(id);
            request.setCrudMethod(CrudMethod.GET);

            response = usuarioSender.listarUsuarioPorId(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Erro ao enviar usuário para o Kafka", e);
            response.setMensagemRetorno("Erro ao enviar usuário para o Kafka");
            return ResponseEntity.ok(response);
        }
    }
}
