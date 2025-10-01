package com.concordance.objects;

public class DBWord {
	
	public DBWord(int id,String word,String group, int numberOfInstances, int numberOfLetters)
	{
		this.id = id;
		this.word = word;
		this.numberOfInstances = numberOfInstances;
		this.numberOfLetters = numberOfLetters;
		this.group = group;
	}

	private int    id;
	private String word;
	private int    numberOfInstances;
	private int    numberOfLetters;
	private String group;
	
	
	public String getGroup()
	{
		return  group;
	}
	public String getWord()
	{
		return  word;
	}
	public int getNumberOfInstances()
	{
		return  numberOfInstances;
	}
	public int getNumberOfLetters()
	{
		return  numberOfLetters;
	}
}
