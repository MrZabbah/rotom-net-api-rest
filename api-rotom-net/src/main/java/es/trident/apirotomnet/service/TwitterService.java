package es.trident.apirotomnet.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Service
public class TwitterService {
	private static final Path IMAGE_FOLDER = Paths.get(System.getProperty("user.dir"), "images");
	private Twitter twitter;
	
	public TwitterService() throws MalformedURLException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		TwitterFactory twitterfactory;
		
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("---------------------------------")
		.setOAuthConsumerSecret("------------------------------")
		.setOAuthAccessToken("---------------------------------")
		.setOAuthAccessTokenSecret("---------------------------");
		
		twitterfactory = new TwitterFactory(cb.build());
		twitter = twitterfactory.getInstance();
	}
	
	public Status postTweet(String username, String pokemon, boolean shiny, int cardAmount) throws TwitterException, IOException {
		StatusUpdate status;
		String message;
		String pokemonName = pokemon;
		Path default_path;
		
		if (shiny) {
			pokemonName += "_s";
			message = "New special acquisition!\n"
					+ "--["+username+"]--  has acquired a shiny " + pokemon + " and has collected a total of " + cardAmount + " cards!";
		} else {
			message = "New acquisition!\n"
					+ "--["+username+"]--  has acquired a(n) " + pokemon + " and has collected a total of " + cardAmount + " cards!";
		}
		
		default_path = IMAGE_FOLDER.resolve(pokemonName + ".jpg");
		status = new StatusUpdate(message);
		status.setMedia(new File(default_path.toUri()));
		return twitter.updateStatus(status);
	}
}
