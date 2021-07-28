package com.bae.FinalProject.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.FinalProject.data.TVShow;

import com.fasterxml.jackson.databind.ObjectMapper;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) 
// goes through random ports until it finds a free one
@AutoConfigureMockMvc
// configures the MockMvc 

//loads SQL scripts from the resources folder and executes them before each test is compiled
@Sql(scripts = { "classpath:tvshow-schema.sql",
"classpath:tvshow-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

@ActiveProfiles("test") // sets profile for the test class

public class TVShowControllerIntegrationTest {

	@Autowired // tells Spring to inject the MockMVC object into the class
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper; // gets the class Spring and uses it to convert java to JSON
	
	@Test  // checks the response body and status
	void testCreate() throws Exception {
		TVShow testTVShow = new TVShow("Office", "Comedy",8, 201);
		
		String testTVShowAsJSON = this.mapper.writeValueAsString(testTVShow);
		
		System.out.println(testTVShow); 
		System.out.println(testTVShowAsJSON);
		
		// sets the: response body and the content type header
		RequestBuilder request = post("/createShow").contentType(MediaType.APPLICATION_JSON).content(testTVShowAsJSON);
		
		
		// checks the: response body and status
		ResultMatcher checkStatus = status().is(201); // checks the status is 201

		TVShow testCreatedTVShow = new TVShow("Office", "Comedy", 8, 201);
		testCreatedTVShow.setId(2); // checks id = 1, due to the auto increment
		String testCreatedTVShowAsJSON = this.mapper.writeValueAsString(testCreatedTVShow);

		ResultMatcher checkBody = content().json(testCreatedTVShowAsJSON);
		
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testUpdate() throws Exception {
		
		TVShow firstTVShow = new TVShow("BreakingBad", "Thriller", 10, 32);
		TVShow checkerTVShow = new TVShow("BreakingBad", "Thriller", 10, 32);
		
		
		checkerTVShow.setId(1);
		
		String firstTVShowAsJSON = this.mapper.writeValueAsString(firstTVShow);
		String checkerTVShowAsJSON = this.mapper.writeValueAsString(checkerTVShow);
		
		RequestBuilder request = put("/updateShow/1").contentType(MediaType.APPLICATION_JSON).content(firstTVShowAsJSON);
		
		// checks the: response body and status
		ResultMatcher checkStatus = status().is(202); // checks the status is 202
		
		ResultMatcher checkBody = content().json(checkerTVShowAsJSON);
		
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test
	void testGetGameByName() throws Exception {
		
		List<TVShow> testTVShows = List.of(new TVShow(1,"Friends", "Comedy", 9, 201));
		
		
		RequestBuilder request = get("/getByName/Friends");

		ResultMatcher checkStatus = status().is(200);

		String testTVShowsAsJSON = this.mapper.writeValueAsString(testTVShows);

		ResultMatcher checkBody = content().json(testTVShowsAsJSON);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	
	@Test
	void testGetGameByID() throws Exception {
		
		TVShow testIDTVShow = new TVShow("Friends", "Comedy", 9, 201);
		testIDTVShow.setId(1);
		
		String testIDTVShowAsJSON = this.mapper.writeValueAsString(testIDTVShow);
		
		RequestBuilder request = get("/getShow/1").contentType(MediaType.APPLICATION_JSON).content(testIDTVShowAsJSON);
		
		// checks the: response body and status
		ResultMatcher checkStatus = status().is(200); // checks the status is 200
		
		ResultMatcher checkBody = content().json(testIDTVShowAsJSON);
		
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test
	void testDelete() throws Exception {
		
		RequestBuilder request = delete("/deleteShow/1"); // creating the request
		
		ResultMatcher checkStatus = status().is(204);  // checking the status is 204
		ResultMatcher checkBody = content().string("Deleted: 1"); // checking the body
		
		
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	
	}
}
