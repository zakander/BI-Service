package com.zakander.stockpredictionservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zakander.stockpredictionservice.beans.StockDateData;

public interface StockDateDataRepository extends JpaRepository<StockDateData, Integer> {
	StockDateData findBySymbolAndDateStr(String symbol, String dateStr);
}
