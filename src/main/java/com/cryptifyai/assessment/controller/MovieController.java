package com.cryptifyai.assessment.controller;

import com.cryptifyai.assessment.dto.MovieResponseDto;
import com.cryptifyai.assessment.entity.Movie;
import com.cryptifyai.assessment.exception.CustomMovieException;
import com.cryptifyai.assessment.security.RequireApiKey;
import com.cryptifyai.assessment.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequireApiKey
    @GetMapping("/popular")
    public ResponseEntity<List<MovieResponseDto>> getPopularMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam String api_key)  throws CustomMovieException {
        List<MovieResponseDto> movies = movieService.getTopFiftyPopularMovies(page);
        return ResponseEntity.ok(movies);
    }

    @RequireApiKey
    @GetMapping("/search")
    public ResponseEntity<List<MovieResponseDto>> getMovieByTitle(
            @RequestParam String query,
            @RequestParam(required = false) String sort_by,
            @RequestParam(defaultValue = "true",required = false) String ascending,
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String filterValue,
            @RequestParam String api_key) throws CustomMovieException {
        List<MovieResponseDto> moviesByTitle = movieService.getMovieByTitle(query, sort_by,
                ascending, filter, filterValue);
        return ResponseEntity.ok(moviesByTitle);
    }

    @RequireApiKey
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieDetailsById(
            @PathVariable("id") Long id,
            @RequestParam String api_key) throws CustomMovieException {
       Movie movie = movieService.getMovieDetailsById(id);
       return ResponseEntity.ok(movie);
    }
}
