package com.tweeter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweeter.model.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
	
	public List<Tweet> findByUsuario(String usuario);
	
	public List<Tweet> findByHastag(String hastag);
	
	public List<Tweet> findAllGroupByHastag();
	
	public List<Tweet> findAllByUsuarioWhereVal(String usuario);
}
