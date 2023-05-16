package com.zakander.stockpredictionservice.beans;

import java.time.LocalDate;
import java.util.TreeMap;

import com.zakander.stockpredictionservice.predictionmodel.PredictionModel;
import com.zakander.stockpredictionservice.scraper.Scraper;
import com.zakander.stockpredictionservice.scraper.Scraper.StockType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="stock_prediction")
public class StockModel {
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="symbol")
	private String symbol;
	
	@Column(name="num_days")
	private int numDays;
	
	public StockModel() {}
	
	public StockModel(Long id, String symbol, int numDays) {
		this.id = id;
		this.symbol = symbol;
		this.numDays = numDays;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getNumDays() {
		return numDays;
	}

	public void setNumDays(int numDays) {
		this.numDays = numDays;
	}
	
	public String[] getPredictions(String symbol, int numDays, String model) {
		if (model.equals("SMA")) {
			return PredictionModel.SMA(symbol, numDays);
		}
		return PredictionModel.EMA(symbol, numDays);
	}
}