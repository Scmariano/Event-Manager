package com.stephen.soloproject1.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stephen.soloproject1.models.Event;
import com.stephen.soloproject1.models.Musician;
import com.stephen.soloproject1.models.Song;
import com.stephen.soloproject1.models.User;
import com.stephen.soloproject1.services.EventServ;
import com.stephen.soloproject1.services.MusicianServ;
import com.stephen.soloproject1.services.SongServ;
import com.stephen.soloproject1.services.UserServ;

@Controller
public class MainController {
	@Autowired UserServ userServ;
	@Autowired EventServ eventServ;
	@Autowired SongServ songServ;
	@Autowired MusicianServ musicianServ;
	
	// View all the Event
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}else {
			
			model.addAttribute("user", userServ.findById(userId));
			model.addAttribute("events", eventServ.allEvents());
			return "dashboard.jsp";
		}
	}
	
	//GetMapping for rendering a page to add an Event
	@GetMapping("/events/new")
	public String newEvent(@ModelAttribute("events") Event event, HttpSession session, Model model) {
		User loggedUser = userServ.findById((Long) session.getAttribute("userId"));
		if(loggedUser == null) {
			return "redirect:/logout";
		}else {
			model.addAttribute("user", loggedUser);
			return "newEvent.jsp";
		}
	}
	
	// Create an Event
	@PostMapping("/events/create")
	public String createEvent(@Valid @ModelAttribute("events")Event event, BindingResult result,
			HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}else {
			if(result.hasErrors()) {
				return "newEvent.jsp";
			}else {
				eventServ.createEvent(event);
				return "redirect:/dashboard";
			}
		}
	}
	
	// Show an Event with a Set List of Songs and Musicians
    @GetMapping("/events/{id}")
    public String showEvents(@PathVariable("id") Long id,
            HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");

        if(userId == null) {
            return "redirect:/logout";
        } else {
            model.addAttribute("event", eventServ.findsEventId(id));          
            model.addAttribute("user", userServ.findById(userId));
            model.addAttribute("like", eventServ.findLikerId(userId));
            model.addAttribute("assignedSongs", songServ.getAssignedEvent(eventServ.findsEventId(id)));
            model.addAttribute("assignedMusicians", musicianServ.getAssignedMusician(eventServ.findsEventId(id)));
            return "showEvent.jsp";
        }
    
    }
    
 // Delete the entire event
 	@RequestMapping("/events/{id}/delete")
 	public String destroyEvent(@PathVariable("id")Long id, HttpSession session) {
 	    Long userId = (Long) session.getAttribute("userId");
         if(userId == null) {
             return "redirect:/logout";
         }
         //execute 
         eventServ.deleteEvent(id);
         return "redirect:/dashboard";
 	}
    
  //GetMapping for rendering a new page for Adding a Song
    @GetMapping("/songs/new/{eventId}")
    public String newSong( @PathVariable("eventId") Long eventId, HttpSession session, Model model,
            @ModelAttribute("song") Song song) {
        Long userId = (Long) session.getAttribute("userId");
        if(userId == null) {
            return "redirect:/logout";
        }else {
            model.addAttribute("event", eventServ.findsEventId(eventId));
            model.addAttribute("user", userServ.findById(userId));
            model.addAttribute("unAssignedSongs", songServ.getUnAssignedEvent(eventServ.findsEventId(eventId)));
            return "newSong.jsp";
        }
    }
    
    
  //Create a Song!
    @PostMapping("/songs/create/{eventId}")
    public String createSong(@Valid @ModelAttribute("song") Song song, BindingResult result, HttpSession session,
            @PathVariable Long eventId) {
        Long userId = (Long) session.getAttribute("userId"); 
        Event thisEvent = eventServ.findsEventId(eventId);
        if(userId == null) {
            return "redirect:/logout";
        }
        if(result.hasErrors()) {
            return "newSong.jsp";
        }
        else {
        	song.setEvent(thisEvent);
            songServ.createSong(song);
            thisEvent.getEventSongs().add(song);
            return "redirect:/songs/new/" + eventId;
        }
    }
    
    // Add song to the list
    @GetMapping("/songs/{songId}/{eventId}/add")
    public String addSong(@PathVariable("songId")Long songId,
    		@PathVariable("eventId")Long eventId,
    		HttpSession session) {
    	Long userId = (Long) session.getAttribute("userId");
    	Song song = songServ.findSongId(songId);
    	Event event = eventServ.findsEventId(eventId);
    	
    	 if(userId == null) {
             return "redirect:/logout";
         }else {
        	 event.getEventSongs().add(song);
        	 eventServ.updateEvent(event);        	
        	 return "redirect:/songs/new/" + eventId ;
         }
    }
    
    
    // Remove song to the list
    @GetMapping("/songs/{songId}/{eventId}/remove")
    public String removeSong(
    		@PathVariable("songId")Long songId,
    		@PathVariable("eventId")Long eventId,
    		HttpSession session) {
    	Long userId = (Long) session.getAttribute("userId");
    	Song song = songServ.findSongId(songId);
    	Event event = eventServ.findsEventId(eventId);
    	
    	if(userId == null) {
            return "redirect:/logout";
        }else {
        	event.getEventSongs().remove(song);
        	eventServ.updateEvent(event);
        	return "redirect:/events/" + eventId;
        }
    }
    
    //Show song
    @GetMapping("/songs/{id}")
    public String showSong(@PathVariable("id")Long id,
    		Model model, HttpSession session) {
    	User loggedUser = userServ.findById((Long) session.getAttribute("userId"));
		if(loggedUser == null) {
			return "redirect:/logout";
		}else {
			model.addAttribute("song", songServ.findSongId(id));
			return "showSong.jsp";
		}
    }
    
    //Edit song
    @GetMapping("/songs/edit/{id}")
	public String editSong(@PathVariable("id")Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId==null) {
			return "redirect:/logout";
		}else {
			model.addAttribute("song", songServ.findSongId(id));
			return "editSong.jsp";
		}
	}
    
    //Update song
    @PutMapping("/songs/update/{id}")
	public String updateSong(@Valid @ModelAttribute("song") Song song, BindingResult result, HttpSession session,
			@PathVariable Long id) {
		Long userId = (Long) session.getAttribute("userId");
		
		if(userId == null) {
			return "redirect:/logout";
		}
		
		if (result.hasErrors()) {
    		return "editSong.jsp";
    	}else {
    		songServ.updateSong(song);
    		return "redirect:/songs/" + id;
    	}
	}
    
    
    // Delete song in the database
    @RequestMapping("/songs/{id}/{eventId}/delete" )
	public String destroySong( @PathVariable("id")Long id, 
	        @PathVariable("eventId")Long eventId, HttpSession session) {
	    Long userId = (Long) session.getAttribute("userId");

	    if(userId == null) {
            return "redirect:/logout";
        }else {
            songServ.deleteSongId(id);
            return "redirect:/songs/new/" + eventId ;
        }
	}
    
  //GetMapping for rendering a page for creating a Musician
  	@GetMapping("/musicians/new/{eventId}")
  	public String newMusician(@ModelAttribute("musician") Musician musician,
  	        @PathVariable("eventId")Long eventId, HttpSession session,
  			Model model) {
  		Long userId = (Long) session.getAttribute("userId");
  		if(userId == null) {
  			return "redirect:/logout";
  		}else {
  			model.addAttribute("event", eventServ.findsEventId(eventId));
  			model.addAttribute("user", userServ.findById(userId));
  			model.addAttribute("musicians", musicianServ.getUnAssignedMusician(eventServ.findsEventId(eventId)));
  			return "newMusician.jsp";
  		}
  	}
  	
  	
  //Create a Musician
  	@PostMapping("/musicians/create/{eventId}")
  	public String createMusician(@Valid @ModelAttribute("musician")Musician musician, BindingResult result, HttpSession session,
  			 @PathVariable Long eventId) {
  		Long userId = (Long) session.getAttribute("userId");
  		Event thisEvent = eventServ.findsEventId(eventId);
  		if(userId == null) {
  			return "redirect:/logout";
  		}
  		if(result.hasErrors()) {
  			return "newMusician.jsp";
  		}else {
  			musician.setEvent(thisEvent);
  			musicianServ.createMusician(musician);
  			thisEvent.getEventMusicians().add(musician);
  			return "redirect:/musicians/new/" + eventId;
  		}
  		
  	}
  	  	
 
  	// Add Musician to the list
  	@GetMapping("/musicians/{musicianId}/{eventId}/add")
  	public String addMusician(
  			@PathVariable("musicianId")Long musicianId,
  			@PathVariable("eventId")Long eventId,
    		HttpSession session) {
  		Long userId = (Long) session.getAttribute("userId");
  		Event event = eventServ.findsEventId(eventId);
  		Musician musician = musicianServ.findMusicianId(musicianId);
  		
  		if(userId == null) {
            return "redirect:/logout";
        }else {
        	event.getEventMusicians().add(musician);
        	eventServ.updateEvent(event);
        	return "redirect:/musicians/new/" + eventId;
        }
  		
  	}
  	
  	// Remove musician to the list
  	@GetMapping("/musicians/{musicianId}/{eventId}/remove")
  	public String removeMusician(
  			@PathVariable("musicianId")Long musicianId,
  			@PathVariable("eventId")Long eventId,
    		HttpSession session) {
  		Long userId = (Long) session.getAttribute("userId");
  		Event event = eventServ.findsEventId(eventId);
  		Musician musician = musicianServ.findMusicianId(musicianId);
  		
  		if(userId == null) {
            return "redirect:/logout";
        }else {
        	event.getEventMusicians().remove(musician);
        	eventServ.updateEvent(event);
        	return "redirect:/events/" + eventId;
        }
  		
  	}
  	
 // A Delete function on deleting a Musician
 	@RequestMapping("musicians/{id}/{eventId}/delete")
 	public String destroyMusician(@PathVariable("id") Long id, 
 	        @PathVariable("eventId")Long eventId,
 	        HttpSession session) {
 	    Long userId = (Long) session.getAttribute("userId");

         if(userId == null) {
             return "redirect:/logout";
         }else {
             musicianServ.deleteMusiciansId(id);
             return "redirect:/musicians/new/" + eventId;
         }
 	}
   
 
    
    //GetMapping for liking an event
  	@GetMapping("/events/{id}/like")
  	public String likeEvent(@PathVariable("id") Long id, HttpSession session) {
  		Event event = eventServ.findsEventId(id);
  		Long userId = (Long) session.getAttribute("userId");
  		User loggedUser = userServ.findById(userId);
  		eventServ.likeEvent(event, loggedUser);
  		return "redirect:/dashboard";
  	}
  	
  	//GetMapping for unliking an event
  	@GetMapping("/events/{id}/unLike")
  	public String unLikeEvent(@PathVariable("id") Long id, HttpSession session) {
  		Event event = eventServ.findsEventId(id);
  		Long userId = (Long) session.getAttribute("userId");
  		User loggedUser = userServ.findById(userId);
  		eventServ.unLikeEvent(event, loggedUser);
  		return "redirect:/dashboard";
  	}
}
