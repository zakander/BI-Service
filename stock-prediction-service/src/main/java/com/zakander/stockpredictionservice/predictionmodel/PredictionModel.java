package com.zakander.stockpredictionservice.predictionmodel;

import java.time.LocalDate;
import java.util.TreeMap;

import com.zakander.stockpredictionservice.beans.StockPredictionsData.ModelType;
import com.zakander.stockpredictionservice.scraper.Scraper;

/*
 * The PredictionModel utility class is used to generate stock price
 * predictions using two universal models: Simple Moving Average
 * (SMA) and Exponential Moving Average (EMA).
 * This class utilises the scraping model (Scraper.scraper) to extract
 * the historical stock pricing data necessary to
 * generate a set of predictions for the open, high, low, close and
 * adjusted closing prices.
 */
public class PredictionModel {	
	
	public static double[] extractNumericalData(TreeMap<LocalDate, String[]> data, int index) {
		double[] numData = new double[data.size()];
		int i = 0;
		for (String[] values : data.values()) {
			numData[i++] = Double.parseDouble(values[index]);
		}
		return numData;
	}
	
	/* This method calculates the Simple Moving Average (SMA)
	 * of a set of stock price data attributes.
	 * The SMA is calculated by simple taking the average of
	 * all previous (consecutive) data points over the input
	 * number of days before the present date. */
	public static double SMA(double[] data) {
		double sum = 0.0;
		for (double num : data) {
			sum += num;
		}
		
		return sum/data.length;
	}
	
	/* This method calculates the Exponential Moving Average (EMA)
	 * of a set of stock price data attributes.
	 * The EMA is calculated using a recursive formula */
	public static double EMA(double[] data) {
		double EMA = data[0];
		double k = 2.0 / (data.length + 1);
		
		for (int i=1; i<data.length; i++) {
			// Resursive EMA formula
			EMA = data[i]*k + EMA*(1-k);
		}
		
		return EMA;
	}
	
	public static String[] getPredictions(String symbol, int numDays, ModelType modelType) {
		
		String[] predictions = new String[5];
		TreeMap<LocalDate, String[]> data = Scraper.scrapeHistory(symbol, numDays);
		
		for (int i=0; i<5; i++) {
			
			double[] numData = extractNumericalData(data, i);
			
			if (modelType.equals(ModelType.SMA)) {
				predictions[i] = String.format("%.2f", SMA(numData));
			}
			else {
				predictions[i] = String.format("%.2f", EMA(numData));
			}
		}
		
		return predictions;
	}
}
