package com.example.demo.service;

import java.time.LocalDateTime;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo.repository.CoinDataRepository;
import com.example.demo.user.CoinData;

@Service
public class CoinDataService {

	@Autowired
	private CoinDataRepository coinDataRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${coinmarketcap.api.url}")
	private String apiUrl;

	@Value("${coinmarketcap.api.key}")
	private String apiKey;

	public CoinData fetchCoinData(String symbol, Long userId) {
		String url = String.format("%s?symbol=%s", apiUrl, symbol);
		// HttpHeaders headers = new HttpHeaders();
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.set("X-CMC_PRO_API_KEY", apiKey);
		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		JSONObject jsonResponse = new JSONObject(response.getBody());
		JSONObject data = jsonResponse.getJSONObject("data").getJSONObject(symbol);
		JSONObject quote = data.getJSONObject("quote").getJSONObject("USD");

		CoinData coinData = new CoinData();
		coinData.setUserId(userId);
		coinData.setSymbol(symbol);
		coinData.setPrice(quote.getDouble("price"));
		coinData.setTimestamp(LocalDateTime.now());

		return coinDataRepository.save(coinData);
	}
}
