package com.stephen.soloproject1.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stephen.soloproject1.models.Event;
import com.stephen.soloproject1.models.Musician;
import com.stephen.soloproject1.models.Song;



@Repository
public interface EventRepo extends CrudRepository <Event, Long> {
	public List<Event>findAll();
	public List<Event>findAllByEventSongs(Song song);
	public List<Event>findAllByEventSongsNotContains(Song song);
	public List<Event>findAllByEventMusicians(Musician musician);
	Optional<Event>findLikersById(Long likerId);
	
}
