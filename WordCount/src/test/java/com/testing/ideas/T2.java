package com.testing.ideas;

import java.io.IOException;
import org.junit.jupiter.api.Test;

import edu.citytech.s23356435.WordCount.WordCount;

public class T2 {
	
	
	@Test
	void testSize1() throws IOException {
		WordCount w = new WordCount("/Users/tahsinahmed/Desktop/CST3650/Assignment#8/heroes.txt","/Users/tahsinahmed/Desktop/CST3650/Assignment#8/countReport.txt");
		System.out.println(w.distinctCount());

	}
}
