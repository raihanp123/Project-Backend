package com.bae.FinalProject.data.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.FinalProject.data.TVShow;


@Repository
public interface TVShowRepo extends JpaRepository<TVShow, Integer> {

		List<TVShow> findByName(String name);
	
}
