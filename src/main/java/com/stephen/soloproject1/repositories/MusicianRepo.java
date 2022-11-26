package com.stephen.soloproject1.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stephen.soloproject1.models.Event;
import com.stephen.soloproject1.models.Musician;


@Repository
public interface MusicianRepo extends CrudRepository<Musician, Long>{
	public List<Musician>findAll();
	public List<Musician>findAllByMusiciansEvents(Event event);
	public List<Musician>findAllByMusiciansEventsNotContains(Event event);
	public List<Musician>findByMusiciansEventsId(Long id);
}
