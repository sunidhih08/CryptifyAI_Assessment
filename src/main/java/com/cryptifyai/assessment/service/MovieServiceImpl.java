package com.cryptifyai.assessment.service;

import com.cryptifyai.assessment.dto.MovieResponseDto;
import com.cryptifyai.assessment.entity.Movie;
import com.cryptifyai.assessment.exception.CustomMovieException;
import com.cryptifyai.assessment.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<MovieResponseDto> getTopFiftyPopularMovies(int page) {
        List<Movie> movies = movieRepository.findTop50ByOrderByRatingDesc();

        int pageSize=10;
        int offset = (page-1) * pageSize;

        return movies.stream()
                .skip(page != 0 ? offset : 0)
                .limit(page != 0 ? pageSize : 50)
                .map(movie -> new MovieResponseDto(
                        movie.getId(),
                        movie.getTitle(),
                        movie.getReleaseDate(),
                        movie.getPosterUrl(),
                        movie.getRating()
                ))
                .toList();
    }

    @Override
    public List<MovieResponseDto> getMovieByTitle(String query, String sortBy, String ascending, String filter, String filterValue) throws CustomMovieException {
        if(query.isBlank()){
            throw new CustomMovieException("Query must have atleast 1 character");
        }
        if(!(Objects.equals(ascending.toLowerCase(),"true") || Objects.equals(ascending.toLowerCase(),"false"))){
            throw new CustomMovieException("Input parameter ascending must have either true or false value");
        }
        Sort sort = Sort.unsorted();
        Float filter_value;
        boolean sortType = Objects.equals(ascending.toLowerCase(),"true");
        if(sortBy!=null) {
            if ("title".equalsIgnoreCase(sortBy)) {
                sortBy = "title";
            } else if ("releaseDate".equalsIgnoreCase(sortBy) ||
                    "release Date".equalsIgnoreCase(sortBy)) {
                sortBy = "releaseDate";
            } else if ("rating".equalsIgnoreCase(sortBy)) {
                sortBy = "rating";
            }
            else{
                throw new CustomMovieException("Sorting parameter not found!");
            }
            sort = sortType ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        }

        List<Movie> movies = movieRepository.findAllByTitleContaining(query,sort);

        if(filter != null && (!"rating".equalsIgnoreCase(filter))){
            throw new CustomMovieException("Filter parameter only accept rating column as input");
        }

        if ("rating".equalsIgnoreCase(filter) && filterValue == null) {
            throw new CustomMovieException("Please provide filterValue in input parameter");
        }
        if(filter !=null) {
            try {
                filter_value = Float.parseFloat(filterValue);
            } catch (NumberFormatException ignored) {
                throw new CustomMovieException("Please provide filerValue as float value");
            }
            movies = movies.stream().filter(m -> m.getRating() >= Float.parseFloat(filterValue)).toList();
        }


        return movies.stream()
                .map(movie -> new MovieResponseDto(
                        movie.getId(),
                        movie.getTitle(),
                        movie.getReleaseDate(),
                        movie.getPosterUrl(),
                        movie.getRating()
                ))
                .toList();
    }

    @Override
    public Movie getMovieDetailsById(Long id) throws CustomMovieException {
        Optional<Movie> movie = movieRepository.findById(id);
        if(movie.isEmpty()){
            throw new CustomMovieException("Movie with the given id is not found!");
        }
        return movie.get();
    }
}
