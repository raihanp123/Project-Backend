package com.bae.FinalProject.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.bae.FinalProject.data.TVShow;
import com.bae.FinalProject.data.repos.TVShowRepo;



@Service
@Primary
public class TVShowServiceDB implements TVShowService{

	
	private TVShowRepo repo;

	public TVShowServiceDB(TVShowRepo repo) {
		super();
		this.repo = repo;
	}

	
	public TVShow createTVShow(TVShow tvshow) { 
		
		System.out.println("created: " + tvshow); // used for Mockito
		
		return this.repo.save(tvshow);
		
	}


	public List<TVShow> getAllTVShows() {
		return this.repo.findAll();
	}

	
	public TVShow getTVShow(int id) {
		System.out.println("Listed: " + id); // used for Mockito
		return this.repo.findById(id).get();
		
		
	}

	public TVShow updateTVShow(int id, TVShow tvshow) {
		// pull out existing record
		TVShow found = this.repo.findById(id).get();
				
				System.out.println("FOUND: " + found); // used for Mockito
 
				// modify record
				found.setName(tvshow.getName());
				found.setGenre(tvshow.getGenre());
				found.setRating(tvshow.getRating());
				found.setEpisodes(tvshow.getEpisodes());
				

				System.out.println("FOUND AFTER UPDATE: " + found); // used for Mockito
				
				// save it back to overwrite it
				TVShow updated = this.repo.save(found);

				System.out.println("UPDATED: " + updated); // used for Mockito
				
				return updated;
			}
    

	
	public String deleteTVShow(int id) {
		this.repo.deleteById(id);

		
		if (this.repo.existsById(id)) {
			
			return "Not deleted: " + id;
		} 
		else {
		
			return "Deleted: " + id;  // print following if ID is deleted
	}
	
	}
	
	
	
	
	public List<TVShow> findByName(String name) {
		
		return this.repo.findByName(name);
	}


	
	
}
