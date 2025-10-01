package com.concordance.Tabs;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.concordance.objects.DBWord;
import com.concordance.objects.Document;
import com.concordance.objects.Word;

/*
 *  data.append("Word", word.value);
	   data.append("Group", group.value);
	   data.append("NumberOfInstances", numberOfInstances.value);
	   data.append("NumberOfChars", numberOfChars.value);
 */
public class DBWordsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	ArrayList<DBWord>  wordsList = new ArrayList<DBWord>();
    	wordsList.add(new DBWord(0, "Feras","TXT",2,2));
    	wordsList.add(new DBWord(0, "Feras","",2,2));
    	wordsList.add(new DBWord(1, "Daniel","",2,2));
    	wordsList.add(new DBWord(2, "Nice","",2,2));
    	wordsList.add(new DBWord(3, "hi","",2,2));
    	wordsList.add(new DBWord(3, "hi","",2,2));
    	wordsList.add(new DBWord(4, "how","",2,2));
    	wordsList.add(new DBWord(5, "why","",2,2));
    	wordsList.add(new DBWord(6, "So","",2,2));
    	wordsList.add(new DBWord(7, "NO","",2,2));
    	
	   String inputWord  = request.getParameter("Word");
	   String inputGroup  = request.getParameter("Group");
       String inputNumberOfInstances    = request.getParameter("NumberOfInstances");
       String inputNumberOfChars   = request.getParameter("NumberOfChars");
         
       ArrayList<DBWord>  retWordsList = new ArrayList<DBWord>();
       for(DBWord word : wordsList)
       {       		 
       		if(     (inputWord == ""  || word.getWord().contains(inputWord))   &&       				
       				(inputGroup == ""   || word.getGroup().contains(inputGroup))   &&
       				(inputNumberOfInstances == ""    || String.valueOf(word.getNumberOfInstances()).equals(inputNumberOfInstances) )&&
       				(inputNumberOfChars == ""    || String.valueOf(word.getNumberOfLetters()).equals(inputNumberOfChars) ))
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
