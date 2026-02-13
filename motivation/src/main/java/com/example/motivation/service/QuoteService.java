package com.example.motivation.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class QuoteService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Set<String> usedQuotes = new HashSet<>();
    private final List<Map<String, Object>> favorites = new ArrayList<>();

    public Map<String, Object> getUniqueQuote() {

        String url = "https://zenquotes.io/api/random";

        while (true) {

            List<Map<String, Object>> response =
                    restTemplate.getForObject(url, List.class);

            Map<String, Object> quote = response.get(0);

            String text = quote.get("q").toString();

            if (!usedQuotes.contains(text)) {

                usedQuotes.add(text);

                return Map.of(
                        "content", quote.get("q"),
                        "author", quote.get("a")
                );
            }
        }
    }

    public void addToFavorites(Map<String, Object> quote) {
        favorites.add(quote);
    }

    public List<Map<String, Object>> getFavorites() {
        return favorites;
    }
}
