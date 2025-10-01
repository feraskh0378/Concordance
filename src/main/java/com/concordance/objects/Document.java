package com.concordance.objects;

public class Document {
	
	public Document(int id,String title, String author, int numberOfVerses, int numberOfLines, int numberOfWords)
	{
		this.id = id;
		this.title = title;
		this.author = author;
		this.numberOfVerses = numberOfVerses;
		this.numberOfLines = numberOfLines;
		this.numberOfWords = numberOfWords;
		
	}

	private int id;
	public  String title;
	public String author;
	public int    numberOfVerses;
	public int    numberOfLines;
	public int    numberOfWords;
}
