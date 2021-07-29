package com.bae.FinalProject.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.bae.FinalProject.data.TVShow;



@Service

public class TVShowServiceList implements TVShowService{
	
	private List<TVShow> tvshows = new ArrayList<>();

	@Override
	public TVShow createTVShow(TVShow game) { 
		this.tvshows.add(game);
		return this.tvshows.get(this.tvshows.size()-1);
	}

	@Override
	public TVShow getTVShow(int id) {
		TVShow found = this.tvshows.get(id);
		return found;
	}
	
	@Override
	public List<TVShow> getAllTVShows() {
		return this.tvshows;
	}
	
	@Override
	public String deleteTVShow(int id) {
		this.tvshows.remove(id);

		return "Deleted TV Show at id " + id;
	}
	
	@Override
    public TVShow updateTVShow(int id, TVShow tvshow) {
      
        this.tvshows.remove(id);
        
        this.tvshows.add(id, tvshow);
        
        return this.tvshows.get(id);
    }

	@Override
	public List<TVShow>findByName(String name) {
		
		return null;
	}

}
	

