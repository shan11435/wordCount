package edu.citytech.s23356435.WordCount;

public class Line {
private int lineNumber;
private String[] words;

public Line(int lineNumber, String wordLine) {
	this.lineNumber = lineNumber;
	this.words = wordLine.split(" ");
}

public int getLineNumber() {
	return lineNumber;
}

public String[] getWords() {
	return words;
}
}
