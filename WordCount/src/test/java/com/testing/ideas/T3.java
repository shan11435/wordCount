package com.testing.ideas;

import static java.util.stream.Collectors.toList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.citytech.s23356435.WordCount.WordCount;

public class T3 {
	@Test
	public void whenReadSmallFileJava7_thenCorrect()
	  throws IOException {
	    Path path = Paths.get("/Users/tahsinahmed/Desktop/CST3650/Assignment#8/heroes.txt");
		List<String> list = Files.readAllLines(path)
				                 .stream()
				                 .map(WordCount.clean)
				                 .collect(toList());
		System.out.println(list);
	  
  }
	
}

