package com.devsuperior.movieflix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.ReviewService;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	private MovieService service;
	
	@Autowired
	private ReviewService reviewService;

	@GetMapping("/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id){
	    MovieDTO dto = service.findById(id);
	    return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<MovieDTO>> findByGenre(
	          	@RequestParam(value = "genreId", defaultValue = "0") Long genreId,
	          	@RequestParam(value = "name", defaultValue = "") String name,
	          	Pageable pageable) {
	   	
	   	Page<MovieDTO> list = service.findByGenre(genreId, pageable);         	
	   	return ResponseEntity.ok().body(list);
	}
	
	
	@GetMapping("/{movieId}/reviews")
	public ResponseEntity<ReviewDTO> findReviews(@PathVariable Long movieId){
	    ReviewDTO dto = reviewService.findReviews(movieId);
	    return ResponseEntity.ok().body(dto);
	}
}
