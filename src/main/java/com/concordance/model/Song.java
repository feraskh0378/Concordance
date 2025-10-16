package com.concordance.model;

public class Song 
{
	
	public Song(int Id,String title, String poet,String composer, int numberOfVerses, int numberOfLines, int numberOfWords)
	{
		this.id = Id;
		this.title = title;
		this.poet = poet;
		this.numberOfVerses = numberOfVerses;
		this.numberOfLines = numberOfLines;
		this.numberOfWords = numberOfWords;
		this.composer = composer;
		
	}

	private int id;
	private  String title;
	private  String poet;
	private  String composer;
	private int    numberOfVerses;
	private int    numberOfLines;
	private int    numberOfWords;
	
	public int getId()
	{
		return id;
	}
	public String getTitle()
	{
		return title;
	}
	public String getPoet()
	{
		return poet;
	}
	public String getComposer()
	{
		return composer;
	}
	public int getNumberOfVerses()
	{
		return numberOfVerses;
	}
	public int getNumberOfLines()
	{
		return numberOfLines;
	}
	public int getNumberOfWords()
	{
		return numberOfWords;
	}
}
