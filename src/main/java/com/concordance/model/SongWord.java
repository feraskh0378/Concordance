package com.concordance.model ;

public class SongWord {
	
	public SongWord(int id,String word, String song,String group,  int verse, int line,int linePlace)
	{
		this.id = id;
		this.word = word;
		this.song = song;
		this.verse = verse;
		this.line = line;
		this.frequency = 0;
		this.group = group;
		this.linePlace = linePlace;
	}

	private int    id;
	private String word;
	private String song;
	private int    verse;
	private int    line;		
	private int    frequency;
	private int    linePlace;
	private String group;
	
	public int getId()
	{
		return id;
	}
	
	public String getWord()
	{
		return  word;
	}
	public String getSong()
	{
		return  song;
	}
	public int getVerse()
	{
		return  verse;
	}
	public int getLine()
	{
		return  line;
	}
	public int getLinePlace()
	{
		return  linePlace;
	}
	public String getGroup()
	{
		return  group;
	}

	
}
