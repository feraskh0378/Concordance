package com.concordance.Tabs;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.concordance.objects.Document;
import com.concordance.objects.Word;

public class WordsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	ArrayList<Word>  wordsList = new ArrayList<Word>();
    	wordsList.add(new Word(0, "Feras","Feras",3,3,3));
    	wordsList.add(new Word(0, "Feras","Daniel",3,3,3));
    	wordsList.add(new Word(1, "Daniel","Feras",3,3,3));
    	wordsList.add(new Word(2, "Nice","Feras",3,3,3));
    	wordsList.add(new Word(3, "hi","Daniel",3,3,3));
    	wordsList.add(new Word(3, "hi","Feras",3,3,3));
    	wordsList.add(new Word(4, "how","Daniel",3,3,3));
    	wordsList.add(new Word(5, "why","Daniel",3,3,3));
    	wordsList.add(new Word(6, "So","Daniel",3,3,3));
    	wordsList.add(new Word(7, "NO","Daniel",3,3,3));
    	
	   String inputWord  = request.getParameter("Word");
       String inputDocument = request.getParameter("Document");
       String inputVerse    = request.getParameter("Verse");
       String inputLine    = request.getParameter("Line");           
       String inputFequency    = request.getParameter("Fequency");
       String inputGroup    = request.getParameter("Group");
         
           ArrayList<Word>  retWordsList = new ArrayList<Word>();
       	for(Word word : wordsList)
       	{
       		 
       		if(     (inputWord == ""  || word.getWord().contains(inputWord))   &&
       				(inputDocument == "" || word.getDocument().contains(inputDocument)) &&
       				(inputVerse == ""    || String.valueOf(word.getVerse()).equals(inputVerse)  ) &&
       				(inputLine == ""    || String.valueOf(word.getLine()).equals(inputLine) ) )
       		{
       			retWordsList.add(word);
       		}
       	}
       	
    	
        // Dynamic content for each tab
        request.setAttribute("WordsTabData", retWordsList);
     

        // Forward request to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("WordsTab.jsp");
        dispatcher.forward(request, response);
    }
}
