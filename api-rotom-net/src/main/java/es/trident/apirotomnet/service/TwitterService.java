package es.trident.apirotomnet.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.stereotype.Service;

import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Service
public class TwitterService {
	//private static final Path IMAGE_FOLDER = Paths.get(System.getProperty("user.dir"), "images");
	private Twitter twitter;
	
	public TwitterService() throws MalformedURLException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("BHaYFxRLSi7vbRQHvqyrulmAQ")
		.setOAuthConsumerSecret("lPgSXHcBN1oNkKSXHaOEq1Q6V3tDza92dm8DbURBB8baPXoOqb")
		.setOAuthAccessToken("1503651814859751425-Yg7qvU1phsWVUduvdzZjmRvlKnmb0y")
		.setOAuthAccessTokenSecret("jbOGvvpoaRIsSgVJd2EpNsjWQKqRYBflZRNOBpEmYrRny");
		
		TwitterFactory twitterfactory = new TwitterFactory(cb.build());
		twitter = twitterfactory.getInstance();
		
		try {
			postTweet("dddwrtwwtd", "eeeeº", 3);
		} catch (TwitterException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void postTweet(String username, String pokemon, int cardAmount) throws TwitterException, IOException {
		StatusUpdate status = new StatusUpdate("¡Nueva adquisición!\n"
				+ "¡["+username+"] ha conseguido a " + pokemon + " y suma un total de " + cardAmount + " cartas coleccionadas!");

		//Path default_path = IMAGE_FOLDER.resolve(pokemon + "-min.gif");
		//status.setMedia(new File(default_path.toUri()));

		//status.setMedia(file);
		twitter.updateStatus(status);
	}
}
