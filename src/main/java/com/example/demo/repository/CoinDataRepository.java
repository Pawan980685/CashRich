package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.user.CoinData;

public interface CoinDataRepository extends JpaRepository<CoinData, Long> {
}
