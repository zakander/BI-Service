package com.zakander.stockpredictionservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zakander.stockpredictionservice.beans.StockPredictionsData;

public interface StockPredictionsDataRepository extends JpaRepository<StockPredictionsData, Integer> {
	StockPredictionsData findBySymbolAndNumDays(String symbol, int numDays);
	void deleteBySymbolAndNumDays(String symbol, int numDays);
}
