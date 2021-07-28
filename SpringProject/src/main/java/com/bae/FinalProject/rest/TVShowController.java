package com.bae.FinalProject.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.FinalProject.data.TVShow;
import com.bae.FinalProject.service.TVShowService;



 


@RestController // tells spring to make an instance of the class
public class TVShowController {

	// dependency of service
	private TVShowService service ;
	

	public TVShowController(TVShowService service) {  // adds constructor to add service to the controller
		super();
	 	this.service = service;
	}

	@GetMapping("/") 
	public String hello() {
		return "Hello, World!";
	}

	@PostMapping("/createShow") 
	public ResponseEntity<TVShow> createTVShow(@RequestBody TVShow tvshow) { 
	
		TVShow created =  this.service.createTVShow(tvshow);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@GetMapping("/getAllShows")
	public List<TVShow> getAllTVShows() {
		 return this.service.getAllTVShows();
	}

	@GetMapping("/getShow/{id}")
	public TVShow getTVShow(@PathVariable int id) {
		TVShow found = this.service.getTVShow(id);
		return found;
	}
	
	@GetMapping("/getByName/{name}") // find TV Show by it's name
	public List<TVShow> findByName(@PathVariable String name) {
		return this.service.findByName(name);
	}

	@PutMapping("/updateShow/{id}")
	public ResponseEntity<TVShow> updateTVShow(@PathVariable int id, @RequestBody TVShow newTVShow) {
		TVShow body = this.service.updateTVShow(id, newTVShow) ;
		return new ResponseEntity<TVShow>(body, HttpStatus.ACCEPTED); // accepted status = 202 
	}

	@DeleteMapping("/deleteShow/{id}")
	public ResponseEntity<String> deleteTVShow(@PathVariable int id) {
		String body = this.service.deleteTVShow(id);
		return new ResponseEntity<String>(body, HttpStatus.NO_CONTENT); // no_content status  = 204
	}

}