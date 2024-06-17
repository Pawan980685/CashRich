package com.example.demo.user;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "coin_data")
public class CoinData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId;
	private String symbol;
	private Double price;
	private LocalDateTime timestamp;

	public CoinData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CoinData(Long id, Long userId, String symbol, Double price, LocalDateTime timestamp) {
		super();
		this.id = id;
		this.userId = userId;
		this.symbol = symbol;
		this.price = price;
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "CoinData [id=" + id + ", userId=" + userId + ", symbol=" + symbol + ", price=" + price + ", timestamp="
				+ timestamp + "]";
	}

}
