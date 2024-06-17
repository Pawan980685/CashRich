package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.example.demo.service.CoinDataService;
import com.example.demo.service.UserService;
import com.example.demo.user.CoinData;
import com.example.demo.user.User;

@RestController
@RequestMapping("/api/coins")
public class CoinController {

    @Autowired
    private UserService userService;

    private final String API_KEY = "27ab17d1-215f-49e5-9ca4-afd48810c149";
    private final String API_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest";

    @GetMapping("/latest")
    public ResponseEntity<String> getLatestCoinData(@RequestParam String symbol, @RequestParam Long userId) {
        User user = userService.findById(userId);
        System.out.println(user);
        System.out.println(user);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "?symbol=" + symbol;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, org.springframework.http.HttpMethod.GET, entity, String.class);

        return ResponseEntity.ok(response.getBody());
    }
}
