package com.zakander.stockpredictionservice.predictionmodel;

import java.time.LocalDate;
import java.util.TreeMap;

import com.zakander.stockpredictionservice.scraper.Scraper;

/*
 * The PredictionModel utility class is used to generate stock
 * price predictions using two universal models: Simple Moving
 * Average (SMA) and Exponential Moving Average (EMA).
 * This class utilises the scraping model (Scraper.scraper)
 * to extract the historical stock pricing data necessary to
 * generate a set of predictions for the open, high, low,
 * close and adjusted closing prices.
 */
public class PredictionModel {	
	/*
	 * This method calculates the Simple Moving Average (SMA)
	 * of a set of stock price data attributes.
	 * The SMA is calculated by simple taking the average of
	 * all previous (consecutive) data points over the input
	 * number of days before the present date.
	 */
	public static String[] SMA(String symbol, int numDays) {
		
		TreeMap<LocalDate, String[]> data = Scraper.scrape(symbol, numDays);
		Double openSum = 0.0;
		Double highSum = 0.0;
		Double lowSum = 0.0;
		Double closeSum = 0.0;
		Double adjCloseSum = 0.0;
		
		for (String[] values : data.values()) {
			openSum += Double.parseDouble(values[0]);
			highSum += Double.parseDouble(values[0]);
			lowSum += Double.parseDouble(values[0]);
			closeSum += Double.parseDouble(values[0]);
			adjCloseSum += Double.parseDouble(values[0]);
		}
		
		openSum /= data.size();
		highSum /= data.size();
		lowSum /= data.size();
		closeSum /= data.size();
		adjCloseSum /= data.size();
		
		return new String[] {
				symbol,
				String.format("%.2f", openSum),
				String.format("%.2f", highSum),
				String.format("%.2f", lowSum),
				String.format("%.2f", closeSum),
				String.format("%.2f", adjCloseSum)
		};
	}
	
	
	/*
	 * This method calculates the Exponential Moving Average (EMA)
	 * of a set of stock price data attributes.
	 * The EMA is calculated using a recursive formula
	 */
	public static String[] EMA(String symbol, int numDays) {
		
		TreeMap<LocalDate, String[]> data = Scraper.scrape(symbol, numDays);
		Double[] EMAVals = new Double[5];
		double k = 2.0 / (data.size() + 1); // weighted factor for EMA
		boolean firstChecked = false; // set to true after first iteration
		
		for (String[] inputValues : data.values()) {
			
			/*
			 * First iteration is used for initialisation;
			 * This is essentially the base case of the
			 * recursive formula computation
			 */
			if (!firstChecked) {
				for (int i=0; i<5; i++) {
					EMAVals[i] = Double.parseDouble(inputValues[i]);
				}
				firstChecked = true;
				continue;
			}
			
			/*
			 * Below is the recursive formula for calculating the
			 * current EMA value.
			 */
			for (int i=0; i<5; i++) {
				double currPrice = Double.parseDouble(inputValues[i]);
				EMAVals[i] = currPrice*k + EMAVals[i]*(1-k);
			}
			
		}
		
		/*
		 * Need to convert all numeric values to strings
		 * and keep two decimal places
		 */
		return new String[] {
				symbol,
				String.format("%.2f", EMAVals[0]),
				String.format("%.2f", EMAVals[1]),
				String.format("%.2f", EMAVals[2]),
				String.format("%.2f", EMAVals[3]),
				String.format("%.2f", EMAVals[4]),
		};
	}
}
