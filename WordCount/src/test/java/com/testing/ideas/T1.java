package com.testing.ideas;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class T1 {
	
	
	private static List<String> fileData = new ArrayList<>();
	
	@BeforeAll
	public static void start() {
		fileData.add("Captain America");
		fileData.add("Superpowers: Strength, agility, stamina, healing ability, expert tactician");
		fileData.add(", martial artist, indestructible shield.");
		fileData.add("The First Avenger by age, Captain America actually joined the team in");
		fileData.add("their early years after he broke out of a block of ice from the Arctic.");
		fileData.add("Before becoming Captain America, Steve Rogers was weak and sickly,"); 
		fileData.add("given treatment that granted him superhuman condition. His extraordinary");
		fileData.add("bones and muscles make him capable of breaking wood and steel with a");
		fileData.add("single hit, while his shield makes him resistant to destruction.");
		fileData.add("With incredible lung capacity, Cap has high endurance and is a"); 
		fileData.add("skilled martial artist.");
	}
	
	
	@Test
	void testSize1() {
		Function<String, String> cleanup = e -> {
			String v = e.replace(":","").replace(",","").replace(".","") //.replace deletes all the characters in the file.add
						.trim(); //removes the indentation from martial artist
			return v;
		};
		Map<String,Integer> mapCount = new HashMap<>(); //finds each characters of the array
		
		Consumer<String[]> logic = c -> {
			 for (String key : c) {
				if(mapCount.containsKey(key)) //if the word is found 
				{
					int n = mapCount.get(key) + 1; //add the number of times the word occurs
					mapCount.put(key,n); //put it into int n
				}
				else //if it's only found once
				{
					mapCount.put(key, 1); //output the word is only found once
				}
			 }
			 String output = Arrays.toString(c); //returns the arrays value to string
			 System.out.println(output); //outputs the array
		 };
		
		fileData.stream().map(String :: toLowerCase) //converts all the string values in the array into lowercase letters
						 .map(cleanup) //makes the modifications of the array based on cleanup method
						 .map(m -> m.split(" ")) //if there's a spacing in the filedata add it into an element of the array
						 .forEach(logic); //modifies the array based on the logic method 
		
		System.out.println(mapCount);
	}
}
