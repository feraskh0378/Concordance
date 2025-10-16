package com.concordance.model;

public class Group {
	
	public Group(int id,String group, int numberOfWords)
	{
		this.id = id;
		this.group = group;		
		this.numberOfWords = numberOfWords;
		
	}

	private int    id;
	private String group;
	private int    numberOfWords;
	
	public String getGroup()
	{
		return  group;
	}
	public int getNumberOfWords()
	{
		return  numberOfWords;
	}

}
