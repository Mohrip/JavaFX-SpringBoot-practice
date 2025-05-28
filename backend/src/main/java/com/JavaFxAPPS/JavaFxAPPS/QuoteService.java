package com.JavaFxAPPS.JavaFxAPPS;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;
import org.springframework.web.client.RestTemplate;



@Service
public class QuoteService {
    private final RestTemplate restTemplate = new RestTemplate();

     public String getRandomQuote(){
        String url = "https://zenquotes.io/api/random";
        String response = restTemplate.getForObject(url, String.class);
        return response;
     }}


