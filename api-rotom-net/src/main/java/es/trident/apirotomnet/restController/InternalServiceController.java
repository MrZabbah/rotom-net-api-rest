package es.trident.apirotomnet.restController;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.trident.apirotomnet.service.MailService;
import es.trident.apirotomnet.service.TwitterService;
import twitter4j.Status;
import twitter4j.TwitterException;

@RestController
public class InternalServiceController {

	private MailService mailService;
	private TwitterService twitterService;

	public InternalServiceController(MailService mailService, TwitterService twitterService) {
		this.mailService = mailService;
		this.twitterService = twitterService;
	}

	@PostMapping("/mail")
	public ResponseEntity<String> teamMail(@RequestBody Map<String, String> data) {
		String teamData = data.get("teamInformation");
		String destiny = data.get("mail");
		mailService.sendMail(teamData, destiny);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@PostMapping("/{user}/card/{pokemon}/tweet")
	public ResponseEntity<String> tweeting(@PathVariable String user, @PathVariable String pokemon,
			@RequestBody Map<String, String> data) {
		ResponseEntity<String> response;
		Status s;

		try {
			s = twitterService.postTweet(user, pokemon, Boolean.parseBoolean(data.get("shiny")),
					Integer.parseInt(data.get("cardAmount")));
			response = ResponseEntity.ok(Long.toString(s.getId()));
		} catch (TwitterException | IOException e) {
			// TODO Auto-generated catch block
			response = new ResponseEntity<String>(HttpStatus.ALREADY_REPORTED);
		}
		
		return response;
	}
}
