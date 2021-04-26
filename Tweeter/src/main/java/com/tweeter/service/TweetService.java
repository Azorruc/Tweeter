package com.tweeter.service;


import java.util.ArrayList;
import java.util.List;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweeter.model.Tweet;
import com.tweeter.model.Usuario;
import com.tweeter.repository.TweetRepository;
import com.tweeter.repository.UsuarioRepository;

@Service
public class TweetService {
	
	@Autowired
	TweetRepository tweetRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public void saveTweet(Tweet tweet) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(tweet.getUsuario());
		
		if(usuario.isPresent()){
			if(usuario.get().getFollowers()>1500) {
				String idioma = tweet.getLoc();
				
				if(idioma.equals("español") || idioma.equals("frances") || idioma.contentEquals("italiano") ) {
					tweetRepository.save(tweet);
				}
			}
		}

	}
	
	public List<Tweet> findTweets() {
		List<Tweet> tweets = tweetRepository.findAll();
		
		return tweets;
	}
	
	public void validateTweet(Tweet tweet) {
		
		tweet.setVal(true);
		
		tweetRepository.save(tweet);
		
	}
	
	public List<Tweet> findValTweets(String usuario){
		
		return tweetRepository.findAllByUsuarioWhereVal(usuario);
	}
	
	public List<String> getHashtags(){
		List<Tweet> tweets = tweetRepository.findAllGroupByHastag();
		
		List<String> hashtags = new ArrayList<>();
		
		for(Tweet tweet : tweets) {
			hashtags.add(tweet.getHastag());
		}
		
		return hashtags;
	}
}
