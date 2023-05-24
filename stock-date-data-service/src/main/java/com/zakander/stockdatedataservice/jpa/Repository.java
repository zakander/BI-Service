package com.zakander.stockdatedataservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zakander.stockdatedataservice.beans.StockDateData;

public interface Repository extends JpaRepository<StockDateData, Integer> {
	StockDateData findBySymbolAndDateStr(String symbol, String dateStr);
}
