package com.cryptifyai.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDto {

    private Long movieId;
    private String title;
    private Date releaseDate;
    private String posterUrl;
    private Float averageRating;

}
