package com.stephen.soloproject1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stephen.soloproject1.models.Event;
import com.stephen.soloproject1.models.Musician;
import com.stephen.soloproject1.repositories.MusicianRepo;

@Service
public class MusicianServ {
	@Autowired MusicianRepo	musicianRepo;
	
	public Musician createMusician(Musician musician) {
		return musicianRepo.save(musician);
	}
	
	public List<Musician> allMusicians(){
		return musicianRepo.findAll();
	}
	
	public void deleteMusicians(Musician musician) {
        musicianRepo.delete(musician);
    } 
	
	public void deleteMusiciansId(Long id) {
	    musicianRepo.deleteById(id);
	}
	
	public List<Musician>eventMusicianId(Long id){
		return musicianRepo.findByMusiciansEventsId(id);
	}
	
	public List<Musician>getAssignedMusician(Event event){
	    return musicianRepo.findAllByMusiciansEvents(event);
	}
	public List<Musician>getUnAssignedMusician(Event event){
		return musicianRepo.findAllByMusiciansEventsNotContains(event);
	}

	public Musician findMusicianId (Long id) {
		Optional<Musician>optMusician = musicianRepo.findById(id);
		if(optMusician.isPresent()) {
			return optMusician.get();
		}else {
			return null;
		}
	}
}
