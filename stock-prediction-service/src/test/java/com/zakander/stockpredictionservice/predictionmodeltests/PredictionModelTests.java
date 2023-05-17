package com.zakander.stockpredictionservice.predictionmodeltests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.zakander.stockpredictionservice.predictionmodel.PredictionModel;

public class PredictionModelTests {
	
	private static final double MAX_ERROR = 0.02;
	
	@Test
	void testSMA() {
		double[] sampleData1 = new double[] {17, 7, 14, 18, 20, 13, 12, 9};
		double expected = 13.75;
		double actual = PredictionModel.SMA(sampleData1);
		Assertions.assertEquals(expected, actual, "expected value is " + expected);
		
		double[] sampleData2 = new double[] {25.09, 2.76, 48.59, 68.97, 19.70,
											85.08, 51.43, 78.33, 49.40, 49.70};
		expected = 47.90;
		actual = PredictionModel.SMA(sampleData2);
		double error = Math.abs(expected - actual);
		Assertions.assertTrue(error <= MAX_ERROR, "expected value is roughly " + expected);
	}
	
	@Test
	void testEMA() {
		double[] sampleData1 = new double[] {17, 7, 14, 18, 20, 13, 12, 9};
		
		double expected = 13.53;
		double actual = PredictionModel.EMA(sampleData1);
		double error = Math.abs(expected - actual);
		Assertions.assertTrue(error <= 0.01, "expected value is roughly " + expected);
		
		double[] sampleData2 = new double[] {25.09, 2.76, 48.59, 68.97, 19.70,
											85.08, 51.43, 78.33, 49.40, 49.70};
		expected = 49.42;
		actual = PredictionModel.EMA(sampleData2);
		error = Math.abs(expected - actual);
		System.out.println("Actual value is " + actual);
		Assertions.assertTrue(error <= MAX_ERROR, "expected value is roughly " + expected);
	}
}
