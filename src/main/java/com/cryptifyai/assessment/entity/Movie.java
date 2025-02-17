package com.cryptifyai.assessment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "Movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @JsonIgnore
    private Long id;

    @Column(name = "Title")
    private String title;

    @Column(name = "ReleaseDate")
    private Date releaseDate;

    @Column(name = "PosterURL")
    private String posterUrl;

    @Column(name = "Overview")
    private String overview;

    @Column(name = "Genres")
    private String genre;

    @Column(name = "Rating")
    private Float rating;

    @Column(name = "Runtime")
    private String runtime;

    @Column(name = "Language")
    private String language;

}
