package br.com.api.queue.sender;

import br.com.commons.dto.QueueRequestDTO;
import br.com.commons.dto.QueueResponseDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UsuarioSender {

	@Autowired
	private RabbitTemplate template;

	@Value("${ync.direct.exchange.estudos.mercadolivre}")
	private String directExchange;

	public QueueResponseDTO listarUsuarioPorId(QueueRequestDTO request) throws Exception {
		QueueResponseDTO response = new QueueResponseDTO();
		try {
			response = (QueueResponseDTO) template.convertSendAndReceive(directExchange, "filaUsuarioRpcQueue", request);
			return response;
		} catch (Exception e) {
			response.setMensagemRetorno("Erro ao enviar ID.");
			return response;
		}
	}
	
	public QueueResponseDTO cadastrarUsuario(QueueRequestDTO request) throws Exception {
		QueueResponseDTO response = new QueueResponseDTO();
		try {
			response = (QueueResponseDTO) template.convertSendAndReceive(directExchange, "filaUsuarioRpcQueue", request);
			return response;
		} catch (Exception e) {
			response.setMensagemRetorno("Erro ao enviar usuário.");
			return response;
		}
	}
}
