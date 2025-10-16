package com.concordance.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.concordance.model.Word;
import com.concordance.model.Song;
import com.concordance.model.Word;

/*
 *  data.append("Word", word.value);
	   data.append("Group", group.value);
	   data.append("NumberOfInstances", numberOfInstances.value);
	   data.append("NumberOfChars", numberOfChars.value);
 */
public class DBWordsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	ArrayList<Word>  wordsList = new ArrayList<Word>();
    	
    	
	   String inputWord  = request.getParameter("Word");
	   String inputGroup  = request.getParameter("Group");
       String inputNumberOfInstances    = request.getParameter("NumberOfInstances");
       String inputNumberOfChars   = request.getParameter("NumberOfChars");
         
       ArrayList<Word>  retWordsList = new ArrayList<Word>();
       for(Word word : wordsList)
       {       		 
       		if(     (inputWord == ""  || word.getWord().contains(inputWord))   &&       				
       				(inputGroup == ""   || word.getGroup().contains(inputGroup))   &&
       				(inputNumberOfInstances == ""    || String.valueOf(word.getNumberOfInstances()).equals(inputNumberOfInstances) )&&
       				(inputNumberOfChars == ""    || String.valueOf(word.getNumberOfChars()).equals(inputNumberOfChars) ))
       		{
       			retWordsList.add(word);
       		}
       	}
       	
    	
        // Dynamic content for each tab
        request.setAttribute("WordsTabData", retWordsList);
     

        // Forward request to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("DBWordsTab.jsp");
        dispatcher.forward(request, response);
    }
}
