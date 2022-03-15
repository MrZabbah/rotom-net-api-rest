package es.trident.apirotomnet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMail(String textData, String destiny) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(destiny);
		mail.setSubject("[RotomNet] Team for Pokemon Showdown");
		String finalText = textData+"\nCopy and paste the text above to import your random team to Pokemon Showdown.\nGreetings, RotomNet.";
		mail.setText(finalText);
		mailSender.send(mail);
	}
}
