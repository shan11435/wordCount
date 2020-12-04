package edu.citytech.s23356435.WordCount;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.citytech.edu.cst.word.IWordCount;

public class WordCount implements IWordCount {

	List<String> list;

	public WordCount(String inputFile, String outputFile) {
		Path path = Paths.get(inputFile);
		try {
			Files.newBufferedReader(path, StandardCharsets.UTF_8);
			list = Files.readAllLines(path).stream() // converts all the string values in the array into lowercase
														// letters
					.map(clean) // makes the modifications of the array based on cleanup method
					.collect(Collectors.toList());
			createFile(outputFile);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	public void createFile(String outputFile) {
		try {
			File file = new File(outputFile);
			if(!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			getWordData().forEach((key, value) -> {
				try {
					bw.write(key + ": " + value.toString().replace("]","").replace("[","").replace(" ", "") + "\n");
				}
				catch (IOException e){
					e.printStackTrace();
				}
				
			}); 
			getWordCount().forEach((key, value) -> {
				try {
					bw.write(key + ": " + value.toString().replace("]","").replace("[","") + "\n");
				}
				catch (IOException e){
					e.printStackTrace();
				}
			});
			bw.write(""+distinctCount());
			
					
			
			
			bw.close();
		} catch(Exception e) {
			
		}
		}


	public static Function<String, String> clean = s -> s.toLowerCase().replaceAll("[^a-z ]", "").trim();
	private static BiFunction<HashMap<String, List<Integer>>, Line, HashMap<String, List<Integer>>> accumalator = (map,
			line) -> {
		String[] words = line.getWords();
		for (String key : words) {
			if (key.isEmpty())
				continue;
			if (map.containsKey(key)) {
				map.get(key).add(line.getLineNumber());
			} else {
				List<Integer> li = new ArrayList<>();
				li.add(line.getLineNumber());
				map.put(key, li);
			}
		}

		return map;

	};// adds up all the line numbers in the heroes.txt
		// private static Map<String,Integer> mapCount = new HashMap<>(); function

	private static BiFunction<HashMap<String, Integer>, Line, HashMap<String, Integer>> accumalator2 = (mapCount,
			line) -> {
		String[] words = line.getWords();
		for (String key : words) {
			if (key.isEmpty())
				continue;
			if (mapCount.containsKey(key)) {
				int n = mapCount.get(key) + 1; // add the number of times the word occurs public Map<String,
												// Integer>getWordCount()
				mapCount.put(key, n); // put it into int n
			} else {
				List<Integer> li = new ArrayList<>();
				li.add(line.getLineNumber());
				mapCount.put(key, 1);
			}
		}

		return mapCount;
	};

	@Override
	public Map<String, Integer> getWordCount() {

		var wordCount = IntStream.rangeClosed(1, list.size()).mapToObj(index -> new Line(index, list.get(index - 1)))
				.reduce(new HashMap<String, Integer>(), accumalator2, (a, b) -> b); // prints out the word and how
		// many it occured (public
		// Map<String,List<Integer>>getWordCount())
		return wordCount;
	}

	@Override
	public Map<String, List<Integer>> getWordData() {
		var wordCount = IntStream.rangeClosed(1, list.size()).mapToObj(index -> new Line(index, list.get(index - 1)))
				.reduce(new HashMap<String, List<Integer>>(), accumalator,
						(a, b) -> b);/*
										 * should output string List<Integers> the 1,2,4,7
										 */
		return wordCount;
	}

	@Override
	public int distinctCount() {
		var wordCount = IntStream.rangeClosed(1, list.size()).mapToObj(index -> new Line(index, list.get(index - 1)))
				.reduce(new HashMap<String, Integer>(), accumalator2, (a, b) -> b); // prints out the word and how
		// many it occured (public
		// Map<String,List<Integer>>getWordCount())
		return wordCount.size();
	}
}
