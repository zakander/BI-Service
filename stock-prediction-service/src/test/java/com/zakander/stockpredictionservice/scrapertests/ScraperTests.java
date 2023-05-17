package com.zakander.stockpredictionservice.scrapertests;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.zakander.stockpredictionservice.scraper.Scraper;

/*
 * This will only be able to test for data up until a
 * certain date in the past.
 */
public class ScraperTests {
	
	@Test
	void testIndex() {
		TreeMap<LocalDate, String[]> data = Scraper.scrape("^NYA", 20);
		
		TreeMap<LocalDate, String[]> expectedValuesArrs = new TreeMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
		
		expectedValuesArrs.put(LocalDate.parse("15 May 2023", formatter),
								new String[] {"15246.36", "15343.64",
								"15228.80", "15322.56", "15322.56"});
		
		expectedValuesArrs.put(LocalDate.parse("12 May 2023", formatter),
								new String[] {"15263.07", "15319.64",
								"15167.78", "15246.36", "15246.36"});
		
		expectedValuesArrs.put(LocalDate.parse("11 May 2023", formatter),
								new String[] {"15349.17", "15349.17",
								"15176.64", "15263.07", "15263.07"});
		
		expectedValuesArrs.put(LocalDate.parse("10 May 2023", formatter),
								new String[] {"15352.81", "15441.99",
								"15228.42", "15349.17", "15349.17"});
		
		expectedValuesArrs.put(LocalDate.parse("09 May 2023", formatter),
								new String[] {"15391.27", "15393.16",
								"15297.09", "15352.81", "15352.81"});
		
		expectedValuesArrs.put(LocalDate.parse("08 May 2023", formatter),
								new String[] {"15380.87", "15448.71",
								"15353.68", "15391.27", "15391.27"});
		
		int i = 0;
		for (LocalDate date : expectedValuesArrs.keySet()) {
			String[] expectedValues = expectedValuesArrs.get(date);
			String[] actualValues = data.get(date);
			Assertions.assertArrayEquals(expectedValues, actualValues,
								"Values of date " + date + " do not match");
			if (i == 6) {break;}
		}
	}
}
