package com.cryptifyai.assessment.repository;

import com.cryptifyai.assessment.entity.Movie;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findTop50ByOrderByRatingDesc();

    List<Movie> findAllByTitleContaining(String query, Sort sort);
}
