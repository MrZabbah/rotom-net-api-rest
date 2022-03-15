package es.trident.apirotomnet;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InternalServiceController {
	
	private MailService mailService;
	
	public InternalServiceController(MailService mailService) {
		this.mailService = mailService;
	}
	
	@PostMapping("/mail")
	public ResponseEntity<String> teamMail(@RequestBody Map<String, String> data) {
		String teamData = data.get("teamInformation");
		String destiny = data.get("mail");
		mailService.sendMail(teamData, destiny);
		return new ResponseEntity<String>(teamData, HttpStatus.OK);
	}

}
