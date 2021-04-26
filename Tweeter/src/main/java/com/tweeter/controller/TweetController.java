package com.tweeter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tweeter.model.Tweet;
import com.tweeter.service.TweetService;

@RestController
@RequestMapping("/tweet")
public class TweetController {

	@Autowired
	TweetService tweetService;
	
	@PostMapping("")
	@ResponseBody
	public void createTweet(@RequestBody Tweet tweet){
		
		tweetService.saveTweet(tweet);
		
	}
	
	@GetMapping("")
	@ResponseBody
	public List<Tweet> getTweets(){
		
		return tweetService.findTweets();
	}
	
	@PostMapping("/validar")
	@ResponseBody
	public void validateTweet(@RequestBody Tweet tweet) {
		
		tweetService.validateTweet(tweet);
	}
	
	@GetMapping("/validados")
	@ResponseBody
	public List<Tweet> getValTweetsByUser(@RequestParam String usuario){
		
		return tweetService.findValTweets(usuario);
	}
	
	@GetMapping("hastags")
	@ResponseBody
	public List<String> getHashtags(){
		
		return tweetService.getHashtags();
	}
}
