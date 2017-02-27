package rs.ac.uns.ftn.informatika.jpa.websockets;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import rs.ac.uns.ftn.informatika.jpa.domain.PurchaseOrder;

@Component
public class Producer {

	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	
	/*
	 * Implementira SimpMessagesSendingOperations klasu koja sadrzi metode za slanje poruka korisnicima.
	 */
	@Autowired
	private SimpMessagingTemplate template;
	
	public void sendMessageTo(String topic, String message) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append(dateFormatter.format(new Date()));
		builder.append("] ");
		builder.append(message);
		this.template.convertAndSend("/topic/" + topic, builder.toString());
	}
	
	public void sendAcceptSignalFromCookToWaiter(String topic, String message) {
		this.template.convertAndSend("/topic/" + topic, message);
	}
	
	public void sendPreparedSignalFromCookToWaiter(String topic, String message) {
		this.template.convertAndSend("/topic/" + topic, message);
	}
	
	public void sendAcceptSignalFromBarmanToWaiter(String topic, String message) {
		this.template.convertAndSend("/topic/" + topic, message);
	}
	
	public void sendPreparedSignalFromBarmanToWaiter(String topic, String message) {
		this.template.convertAndSend("/topic/" + topic, message);
	}
	
	public void sendAcceptSignalFromManagerToProvider(String topic, String message) {
		this.template.convertAndSend("/topic/" + topic, message);
	}

}
