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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;




@Entity
@Table(name="events")
public class Event {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message= "Title must not be blank!")
	@Size(min=3, message="Title should be more than 3 characters.")
	private String eventName;
	
	@NotBlank(message= "Address must not be blank!")
	@Size(min=3, message="Address should be more than 3 characters.")
	private String address;
	
	@Future
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message= "Date must not be blank!")
	private Date eventDate;
	
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
    @JoinColumn(name="user_id")
    private User user;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "likes", 
        joinColumns = @JoinColumn(name = "event_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> likers;
	
	@OneToMany(mappedBy="event", fetch = FetchType.LAZY)
	private List<Song> songs;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "event_songs",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
	private List<Song> eventSongs;
	
	@OneToMany(mappedBy="event", fetch = FetchType.LAZY)
	private List<Musician> musicians;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "event_musicians",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "musician_id")
    )
	private List<Musician> eventMusicians;

	public Event() {}

	public Event(
			@NotBlank(message = "Title must not be blank!") @Size(min = 3, message = "Title should be more than 3 characters.") String eventName,
			@NotBlank(message = "Address must not be blank!") @Size(min = 3, message = "Address should be more than 3 characters.") String address,
			@Future @NotNull(message = "Date must not be blank!") Date eventDate, User user, List<User> likers,
			List<Song> songs, List<Song> eventSongs, List<Musician> musicians,List<Musician> eventMusicians) {
		this.eventName = eventName;
		this.address = address;
		this.eventDate = eventDate;
		this.user = user;
		this.likers = likers;
		this.songs = songs;
		this.eventSongs = eventSongs;
		this.musicians = musicians;
		this.eventMusicians = eventMusicians;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getEventDate() { 
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getLikers() {
		return likers;
	}

	public void setLikers(List<User> likers) {
		this.likers = likers;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	
	
	public List<Song> getEventSongs() {
		return eventSongs;
	}

	public void setEventSongs(List<Song> eventSongs) {
		this.eventSongs = eventSongs;
	}

	public List<Musician> getMusicians() {
		return musicians;
	}

	public void setMusicians(List<Musician> musicians) {
		this.musicians = musicians;
	}

	public List<Musician> getEventMusicians() {
		return eventMusicians;
	}

	public void setEventMusicians(List<Musician> eventMusicians) {
		this.eventMusicians = eventMusicians;
	}

	
	
	
	
}





