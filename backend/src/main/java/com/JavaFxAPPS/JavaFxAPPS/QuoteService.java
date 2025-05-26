package com.JavaFxAPPS.JavaFxAPPS;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;


@Service
public class QuoteService {
    private final List<String> quotes = List.of(
            "The only limit to our realization of tomorrow is our doubts of today.",
            "The future belongs to those who believe in the beauty of their dreams.",
            "Do not wait to strike till the iron is hot, but make it hot by striking.",
            "Success usually comes to those who are too busy to be looking for it.",
            "The way to get started is to quit talking and begin doing.",
            "Your time is limited, so don’t waste it living someone else’s life.",
            "Life is what happens when you’re busy making other plans.",
            "The purpose of our lives is to be happy.",
            "Get busy living or get busy dying.",
            "You have within you right now, everything you need to deal with whatever the world can throw at you.",
            "Believe you can and you’re halfway there.",
            "The only impossible journey is the one you never begin.",
            "Act as if what you do makes a difference. It does.",
            "Success is not how high you have climbed, but how you make a positive difference to the world.",
            "What lies behind us and what lies before us are tiny matters compared to what lies within us.",
            "The way to get started is to quit talking and begin doing."
    );

    private final Random random = new Random();

    public String getRandomQuote() {
        return quotes.get(random.nextInt(quotes.size()));
    }

}
