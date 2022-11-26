package com.stephen.soloproject1.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="songs")
public class Song {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message= "Title must not be blank!")
	@Size(min=1, message="Title should be more than 1 characters.")
	private String songTitle;
	
	@NotBlank(message= "Artist must not be blank!")
	@Size(min=3, message="Artist should be more than 3 characters.")
	private String artist;

	@NotBlank(message= "Lyrics must not be blank!")
	@Size(min=3, message="Lyrics should be more than 3 characters.")
	@Column(columnDefinition = "LONGTEXT")
	private String lyrics;
	
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="event_id")
	private Event event;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "event_songs",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
	private List<Event> songsEvent;

	public Song() {}
	
	public Song(
			@NotBlank(message = "Title must not be blank!") @Size(min = 1, message = "Title should be more than 1 characters.") String songTitle,
			@NotBlank(message = "Artist must not be blank!") @Size(min = 3, message = "Artist should be more than 3 characters.") String artist,
			@NotBlank(message = "Lyrics must not be blank!") @Size(min = 3, message = "Lyrics should be more than 3 characters.") String lyrics,
			Event event, List<Event> songsEvent) {
		this.songTitle = songTitle;
		this.artist = artist;
		this.lyrics = lyrics;
		this.event = event;
		this.songsEvent = songsEvent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<Event> getSongsEvent() {
		return songsEvent;
	}

	public void setSongsEvent(List<Event> songsEvent) {
		this.songsEvent = songsEvent;
	}
	
	
	
}
