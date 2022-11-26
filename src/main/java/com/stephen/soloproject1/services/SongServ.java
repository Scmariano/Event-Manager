package com.stephen.soloproject1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stephen.soloproject1.models.Event;
import com.stephen.soloproject1.models.Song;
import com.stephen.soloproject1.repositories.SongRepo;



@Service
public class SongServ {
	@Autowired SongRepo songRepo;
	
	public Song createSong(Song song) {
		return songRepo.save(song);
	}
	
	public List<Song> allSongs() {
		return songRepo.findAll();
	}
	
	public Song findSongId(Long id) {
		Optional<Song>optSong = songRepo.findById(id);
		if(optSong.isPresent()) {
			return optSong.get();
		}else {
			return null;
		}
	}
	
	public Song updateSong(Song song) {
		return songRepo.save(song);
	}
	
	public void deleteSongId(Long id) {
	    songRepo.deleteById(id);
	}
	
	public void deleteSong(Song song) {
		songRepo.delete(song);
	} 
	
	public List<Song>getAssignedEvent(Event event){
		return songRepo.findAllBySongsEvent(event);
	}
	
	public List<Song>getUnAssignedEvent(Event event){
		return songRepo.findAllBySongsEventNotContains(event);
	}

	public List<Song>eventSongsId(Long eventId){
		return songRepo.findBySongsEventId(eventId);
	}

	
}
