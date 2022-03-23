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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

	@Operation(summary = "Send an email with one pkmnTeam")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Email correctly sent") })
	@PostMapping("/mail")
	public ResponseEntity<String> teamMail(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Email and Team information", content = @Content(mediaType = "application/json", schema = @Schema(example = "{"
					+ "mail: string, " + " teamInformation: string" + "}"))) @RequestBody Map<String, String> data) {
		String teamData = data.get("teamInformation");
		String destiny = data.get("mail");
		
		mailService.sendMail(teamData, destiny);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@Operation(summary = "Post tweet into @RotomNetForum")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Tweet correctly published", content = @Content()),
			@ApiResponse(responseCode = "208", description = "Tweet duplicated", content = @Content) })
	@PostMapping("/{user}/card/{pokemon}/tweet")
	public ResponseEntity<String> tweeting(@Parameter(description = "client username") @PathVariable String user,
			@Parameter(description = "card's pokemon name") @PathVariable String pokemon,
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Rarety and card amount information", content = @Content(mediaType = "application/json", schema = @Schema(example = "{"
					+ "shiny: string" + " cardAmount: string" + "}"))) @RequestBody Map<String, String> data) {
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
