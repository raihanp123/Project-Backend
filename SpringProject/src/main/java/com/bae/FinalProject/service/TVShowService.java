package com.bae.FinalProject.service;

import java.util.List;

import com.bae.FinalProject.data.TVShow;

public interface TVShowService {

	public TVShow createTVShow(TVShow tvshow);

	public List<TVShow> getAllTVShows();

	public TVShow getTVShow(int id);

	public TVShow updateTVShow(int id, TVShow newTVShow);

	public String deleteTVShow(int id);

	List<TVShow> findByName(String name);


}