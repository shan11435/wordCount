package com.testing.ideas;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
public class T1_Reduce {		
			
			public static int sum(int x, int y) { //another way
				return (x + y) ;
			}
			static BinaryOperator<Integer> sum2 = (a,b) -> a + b; //one way
			
			public static void main(String[] args) {
				List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
				int result = numbers.stream().reduce(0, T1_Reduce.sum2);
				System.out.println(result);
	}

}
