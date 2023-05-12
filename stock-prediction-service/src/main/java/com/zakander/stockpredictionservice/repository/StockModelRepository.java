package com.zakander.stockpredictionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zakander.stockpredictionservice.beans.StockModel;

public interface StockModelRepository extends JpaRepository<StockModel, Long> {
	StockModel findBySymbolAndNumDays(String symbol, int numDays);
}