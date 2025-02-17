package com.cryptifyai.assessment.aspect;

import com.cryptifyai.assessment.exception.CustomMovieException;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ApiKeyAspect {

    @Value("${apiKey}")
    private String validApiKey;

    private final HttpServletRequest request;

    public ApiKeyAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Before("@annotation(com.cryptifyai.assessment.security.RequireApiKey)")
    public void validateApiKey() throws CustomMovieException {
        if(request.getParameter("api_key") == null){
            throw new CustomMovieException("API Key is missing in the request");
        }
        String apiKey = request.getParameter("api_key");
        if (apiKey == null || !apiKey.equals(validApiKey)) {
            throw new CustomMovieException("Invalid API key");
        }
    }
}
