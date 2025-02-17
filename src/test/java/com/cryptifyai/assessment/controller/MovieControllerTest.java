package com.cryptifyai.assessment.controller;
import com.cryptifyai.assessment.dto.MovieResponseDto;
import com.cryptifyai.assessment.entity.Movie;
import com.cryptifyai.assessment.exception.CustomMovieException;
import com.cryptifyai.assessment.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    private MockMvc mockMvc;

    @MockitoBean
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    void testGetPopularMovies() throws Exception {
        List<MovieResponseDto> movies = Arrays.asList(
                new MovieResponseDto(1L, "Movie1", new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01").getTime()), "url1", 8.5f),
                new MovieResponseDto(2L, "Movie2", new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("2022-02-01").getTime()), "url2", 7.5f)
        );
        when(movieService.getTopFiftyPopularMovies(0)).thenReturn(movies);

        mockMvc.perform(get("/movies/popular").param("page", "0").param("api_key", "test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Movie1"));
    }

    @Test
    void testGetMovieByTitle() throws Exception {
        List<MovieResponseDto> movies = List.of(
                new MovieResponseDto(1L, "Movie1", new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01").getTime()), "url1", 8.5f)
        );
        when(movieService.getMovieByTitle("Movie1", null, "true", null, null)).thenReturn(movies);

        mockMvc.perform(get("/movies/search").param("query", "Movie1").param("ascending", "true").param("api_key", "test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Movie1"));
    }

    @Test
    void testGetMovieDetailsById() throws Exception {
        Movie movie = new Movie(1L, "Movie1", new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01").getTime()), "url1","","", 8.5f,"","");
        when(movieService.getMovieDetailsById(1L)).thenReturn(movie);

        mockMvc.perform(get("/movies/1").param("api_key", "test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Movie1"));
    }
}
