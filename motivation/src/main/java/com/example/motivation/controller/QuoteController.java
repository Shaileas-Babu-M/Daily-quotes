package com.example.motivation.controller;

import com.example.motivation.service.QuoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class QuoteController {

    private final QuoteService service;
    private Map<String, Object> currentQuote;

    public QuoteController(QuoteService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {

        currentQuote = service.getUniqueQuote();

        model.addAttribute("text", currentQuote.get("content"));
        model.addAttribute("author", currentQuote.get("author"));
        model.addAttribute("favorites", service.getFavorites());

        return "index";
    }

    @PostMapping("/favorite")
    public String addFavorite() {
        service.addToFavorites(currentQuote);
        return "redirect:/";
    }
}
