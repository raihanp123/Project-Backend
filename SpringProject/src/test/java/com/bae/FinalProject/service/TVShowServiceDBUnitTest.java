package com.bae.FinalProject.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.bae.FinalProject.data.TVShow;
import com.bae.FinalProject.data.repos.TVShowRepo;


@SpringBootTest
@ActiveProfiles("test")  // sets profile for the test class

public class TVShowServiceDBUnitTest {

	@Autowired
	private TVShowServiceDB service;
	
	@MockBean
	private TVShowRepo repo;
	
	@Test
	void testUpdate()  {
		
		int id = 1;
		
		TVShow testTVShow = new TVShow(id, "Wandavision", "Supernatural", 9, 9);
		TVShow testNewTVShow = new TVShow (id, "Loki", "Fantasy", 10, 6);
		
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(testTVShow)); 
		
		Mockito.when(this.repo.save(new TVShow(id, "Loki", "Fantasy", 10, 6))).thenReturn(testNewTVShow);
		
		
		TVShow actual = this.service.updateTVShow(id, testNewTVShow);
		
		assertThat(actual).isEqualTo(testNewTVShow);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(new TVShow (id, "Loki", "Fantasy", 10, 6));
		
	}
	
	@Test
	void testDeletePass() {
		int id = 1;

		Mockito.when(this.repo.existsById(id)).thenReturn(false);

		assertThat(this.service.deleteTVShow(id)).isEqualTo("Deleted: " + id);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}

	@Test
	void testDeleteFails() {
		int id = 1;

		Mockito.when(this.repo.existsById(id)).thenReturn(true);

		assertThat(this.service.deleteTVShow(id)).isEqualTo("Not deleted: " + id);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}


	
	@Test
	void testCreate() {
		
	TVShow newTVShow = new TVShow("South Park", "Comedy", 10, 287);	
	TVShow createdTVShow = new TVShow(1, "South Park", "Comedy", 10, 287);	
	
	Mockito.when(this.repo.save(newTVShow)).thenReturn(createdTVShow);
	
	assertThat(this.service.createTVShow(newTVShow)).isEqualTo(createdTVShow);
	
	Mockito.verify(this.repo, Mockito.times(1)).save(newTVShow);
	
	}
	
	@Test
	void testgetAll() {
		
		int id = 1;
        TVShow testTVShow = new TVShow (id, "Loki", "Fantasy", 10, 6);
       
        List<TVShow> tvshows = List.of(testTVShow);

        Mockito.when(this.repo.findAll()).thenReturn(tvshows);
        
        assertThat(this.service.getAllTVShows()).isEqualTo(tvshows);
        
        Mockito.verify(this.repo, Mockito.times(1)).findAll();
		
	}
	
	@Test
	void testfindTVShowByID() {
		
		int id = 1;
        
		TVShow newID = new TVShow (id, "Loki", "Fantasy", 10, 6);
       
       
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(newID)); 
        
        assertThat(this.service.getTVShow(id)).isEqualTo(newID);
		
	}
	
	@Test
	void testfindGameByName() {
		
		int id = 1;
        
		TVShow newName = new TVShow (id, "Batfink", "Action", 10, 100);
		
		 List<TVShow> tvshows = List.of(newName);
       
       String search = "Batfink";
       
       Mockito.when(this.repo.findByName(search)).thenReturn(tvshows);
       
        
        assertThat(this.service.findByName(search)).isEqualTo(tvshows);
		
	}
}
