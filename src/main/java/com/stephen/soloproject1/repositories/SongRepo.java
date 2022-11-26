package com.stephen.soloproject1.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stephen.soloproject1.models.Event;
import com.stephen.soloproject1.models.Song;



@Repository
public interface SongRepo extends CrudRepository<Song, Long> {
	public List<Song>findAll();
	public List<Song>findAllBySongsEvent(Event event);
	public List<Song>findAllBySongsEventNotContains(Event event);
	public List<Song>findBySongsEventId(Long id);
}
