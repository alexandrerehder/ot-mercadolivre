package br.com.mercadolivre.queue.listener;

import br.com.commons.dto.QueueRequestDTO;
import br.com.commons.dto.QueueResponseDTO;
import br.com.commons.dto.UsuarioDTO;
import br.com.mercadolivre.service.UsuarioService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Log4j2
@Component
public class UsuarioListener {

	@Autowired
	private UsuarioService usuarioService;

	@RabbitListener(queues = "${ync.fila.usuario.rpc.queue}")
	public QueueResponseDTO processaEnvioAutor(QueueRequestDTO request) throws Exception {
		QueueResponseDTO response = new QueueResponseDTO();

		switch (request.getCrudMethod()) {

			case GET:
				try {
					UUID id = (UUID) request.getObjeto();
					log.info("ID recebido:" + "\n" + id);

					UsuarioDTO autorPorId = usuarioService.buscarAutorPorId(id);

					if(Objects.isNull(autorPorId.getId())) {
						log.info("Autor não encontrado");

						response.setMensagemRetorno("Autor não encontrado");
						response.setErro(false);
						response.setObjeto("Data/Horário da transação: " + LocalDateTime.now());
					}else {
						log.info("Autor referente ao ID:" + "\n" + autorPorId);

						response.setMensagemRetorno("Autor encontrado");
						response.setErro(false);
						response.setObjeto(autorPorId);
					}

				}catch (Exception e) {
					response.setMensagemRetorno(e.getMessage());
					response.setErro(true);
					response.setObjeto(e);
					log.error("Falha ao buscar autor: " + response);
				}

				break;

			default:
				response.setMensagemRetorno("Erro interno!");
				response.setErro(true);

				break;
		}
		return response;
	}

}
