package com.zakander.stockpredictionservice.scrapertests;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
		TreeMap<LocalDate, String[]> data = Scraper.scrapeHistory("^NYA", 20);
		
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
	
	@Test
	void testCurrency() {
		TreeMap<LocalDate, String[]> data = Scraper.scrapeHistory("AUDNZD=X", 20);
		
		TreeMap<LocalDate, String[]> expectedValuesArrs = new TreeMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
		
		expectedValuesArrs.put(LocalDate.parse("15 May 2023", formatter),
								new String[] {"1.0727", "1.0763",
								"1.0721", "1.0727", "1.0727"});
		
		expectedValuesArrs.put(LocalDate.parse("12 May 2023", formatter),
								new String[] {"1.0634", "1.0741",
								"1.0635", "1.0634", "1.0634"});
		
		expectedValuesArrs.put(LocalDate.parse("11 May 2023", formatter),
								new String[] {"1.0644", "1.0647",
								"1.0604", "1.0644", "1.0644"});
		
		expectedValuesArrs.put(LocalDate.parse("10 May 2023", formatter),
								new String[] {"1.0675", "1.0685",
								"1.0632", "1.0675", "1.0675"});
		
		expectedValuesArrs.put(LocalDate.parse("09 May 2023", formatter),
								new String[] {"1.0699", "1.0708",
								"1.0661", "1.0699", "1.0699"});
		
		expectedValuesArrs.put(LocalDate.parse("08 May 2023", formatter),
								new String[] {"1.0711", "1.0737",
								"1.0681", "1.0711", "1.0711"});
		
		int i = 0;
		for (LocalDate date : expectedValuesArrs.keySet()) {
			String[] expectedValues = expectedValuesArrs.get(date);
			String[] actualValues = data.get(date);
			Assertions.assertArrayEquals(expectedValues, actualValues,
								"Values of date " + date + " do not match");
			if (i == 6) {break;}
		}
	}
	
	@Test
	void testCrypto() {
		TreeMap<LocalDate, String[]> data = Scraper.scrapeHistory("ADA-AUD", 20);
		
		TreeMap<LocalDate, String[]> expectedValuesArrs = new TreeMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
		
		expectedValuesArrs.put(LocalDate.parse("15 May 2023", formatter),
								new String[] {"0.548830", "0.560509",
								"0.547189", "0.557794", "0.557794"});
		
		expectedValuesArrs.put(LocalDate.parse("12 May 2023", formatter),
								new String[] {"0.545582", "0.545582",
								"0.524579", "0.537412", "0.537412"});
		
		expectedValuesArrs.put(LocalDate.parse("11 May 2023", formatter),
								new String[] {"0.537619", "0.550849",
								"0.526939", "0.545554", "0.545554"});
		
		expectedValuesArrs.put(LocalDate.parse("10 May 2023", formatter),
								new String[] {"0.539444", "0.544950",
								"0.534666", "0.537645", "0.537645"});
		
		expectedValuesArrs.put(LocalDate.parse("09 May 2023", formatter),
								new String[] {"0.558599", "0.563117",
								"0.528787", "0.539493", "0.539493"});
		
		expectedValuesArrs.put(LocalDate.parse("08 May 2023", formatter),
								new String[] {"0.562169", "0.567194",
								"0.557903", "0.558616", "0.558616"});
		
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
