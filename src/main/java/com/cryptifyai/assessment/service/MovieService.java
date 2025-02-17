package com.cryptifyai.assessment.service;

import com.cryptifyai.assessment.dto.MovieResponseDto;
import com.cryptifyai.assessment.entity.Movie;
import com.cryptifyai.assessment.exception.CustomMovieException;

import java.util.List;

public interface MovieService {

    public List<MovieResponseDto> getTopFiftyPopularMovies(int page);

    public Movie getMovieDetailsById(Long id) throws CustomMovieException;

    public List<MovieResponseDto> getMovieByTitle(String query, String sortBy, String ascending, String filter, String filterValue) throws CustomMovieException;
}
