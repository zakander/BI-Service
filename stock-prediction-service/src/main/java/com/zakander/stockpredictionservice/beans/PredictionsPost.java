package com.zakander.stockpredictionservice.beans;

import com.zakander.stockpredictionservice.beans.StockPredictionsData.ModelType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class PredictionsPost {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private ModelType modelType;
	private String symbol;
	private int numDays;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public ModelType getModelType() {
		return modelType;
	}
	
	public void setModelType(ModelType modelType) {
		this.modelType = modelType;
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
}
