package com.concordance.objects;

public class Word {
	
	public Word(int id,String word, String document,  int verse, int line, int numberOfLetters)
	{
		this.id = id;
		this.word = word;
		this.document = document;
		this.verse = verse;
		this.line = line;
		this.frequency = line;
		this.group = "TXT";
	}

	private int    id;
	private String word;
	private String document;
	private int    verse;
	private int    line;		
	private int    frequency;
	private String group;
	
	public String getWord()
	{
		return  word;
	}
	public String getDocument()
	{
		return  document;
	}
	public int getVerse()
	{
		return  verse;
	}
	public int getLine()
	{
		return  line;
	}
	public int getFrequency()
	{
		return  frequency;
	}
	public String getGroup()
	{
		return  group;
	}

	
}
