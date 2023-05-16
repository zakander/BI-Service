package com.zakander.stockpredictionservice.beans;

import java.time.LocalDate;
import java.util.TreeMap;

import com.zakander.stockpredictionservice.predictionmodel.PredictionModel;
import com.zakander.stockpredictionservice.scraper.Scraper;

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
		
		String[] predictions = new String[6];
		predictions[0] = symbol;
		TreeMap<LocalDate, String[]> data = Scraper.scrape(symbol, numDays);
		
		for (int i=0; i<5; i++) {
			
			double[] numData = PredictionModel.extractNumericalData(data, i);
			
			if (model.equals("SMA")) {
				predictions[i+1] = String.format("%.2f", PredictionModel.SMA(numData));
			}
			else {
				predictions[i+1] = String.format("%.2f", PredictionModel.EMA(numData));
			}
		}
		
		return predictions;
	}
}