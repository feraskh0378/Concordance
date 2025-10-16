package com.concordance.model;

public class Word {
	private int    id;
	private String word;
	private String songTitle;	
	private String groupName;
	private int verseNumber;
	private int lineNumber;
	private int placeInLine;
	private int numberOfChars;
	private int numberOfInstancesInSong;
	private int numberOfInstancesInDB;
	
	
	public Word(String word,String group,String songTitle,int verseNumber, int lineNumber, int placeInLine, int numberOfInstancesInSong, int numberOfInstancesInDB )
	{
		this.id = 0;
		this.word = word;
		this.songTitle = songTitle;
		this.groupName = group;
		this.verseNumber = verseNumber;
		this.lineNumber = lineNumber;
		this.placeInLine = placeInLine;
		this.numberOfChars = word.length();
		this.numberOfInstancesInSong = numberOfInstancesInSong;
		this.numberOfInstancesInDB = numberOfInstancesInDB;
	}
	
	public Word()
	{
		this.id = 0;
		this.word = "";
		this.songTitle = "";
		this.groupName = "";
		this.verseNumber = 0;
		this.lineNumber = 0;
		this.placeInLine = 0;
		this.numberOfChars = 0;
		this.numberOfInstancesInSong = 0;
		this.numberOfInstancesInDB = 0;
	}
	
	public void setID(int _id)
	{
		id = _id;
	}
	
	public void setGroup(String _group)
	{
		groupName = _group;
	}
	public void setWord(String _word)
	{
		word = _word;
		numberOfChars = word.length();
	}
		
	public String getGroup()
	{
		return  groupName;
	}
	public String getWord()
	{
		return  word;
	}
	public int getNumberOfInstancesDB()
	{
		return  numberOfInstancesInDB;
	}
	public int getNumberOfInstancesSong()
	{
		return  numberOfInstancesInSong;
	}
	public int getNumberOfChars()
	{
		return  numberOfChars;
	}
	
	public int getVerseNumber()
	{
		return this.verseNumber;
	}
	public int getLineNumber()
	{
		return this.lineNumber;
	}
	public int getPlaceInLine()
	{
		return this.placeInLine;
	}
}
