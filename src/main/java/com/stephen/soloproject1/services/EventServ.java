package com.stephen.soloproject1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stephen.soloproject1.models.Event;
import com.stephen.soloproject1.models.Song;
import com.stephen.soloproject1.models.User;
import com.stephen.soloproject1.repositories.EventRepo;

@Service
public class EventServ {
	@Autowired EventRepo eventRepo;
	
	public List<Event>allEvents(){
		return eventRepo.findAll();
	}
	
	public Event createEvent(Event event) {
		return eventRepo.save(event);
	}
	
	public Event findsEventId(Long id) {
		Optional<Event>optEvent = eventRepo.findById(id);
		if(optEvent.isPresent()) {
			return optEvent.get();
		}else {
			return null;
		}
	}
	
	public Event updateEvent(Event event) {
		return eventRepo.save(event);
	}
	
	public void deleteEvent(Long id) {
		eventRepo.deleteById(id);
	}
	
	public Event findLikerId(Long likerId) {
		Optional<Event>optEventLikers = eventRepo.findLikersById(likerId);
		if(optEventLikers.isPresent()) {
			return optEventLikers.get();
		}else {
			return null;
		}
	}
	
	public void likeEvent(Event event, User userId) {
		List<User> likers = event.getLikers();
		likers.add(userId);
		eventRepo.save(event);
	}
	
	public void unLikeEvent(Event event, User userId) {
		List<User> likers = event.getLikers();
		likers.remove(userId);
		eventRepo.save(event);
	}
	
	public List<Event>getAssignedSongs(Song song){
		return eventRepo.findAllByEventSongs(song);
	}
	
	public List<Event>getUnAssignedSongs(Song song){
		return eventRepo.findAllByEventSongsNotContains(song);
	}
}
