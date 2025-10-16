package com.concordance.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.concordance.dao.WordsDAO;
import com.concordance.model.SongWord;
import com.concordance.model.Word;


public class FilterWordsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	WordsDAO wordDAO = new WordsDAO();
    	wordDAO.initConnection();
    	ArrayList<Word>  wordsList = null;

	   String inputWord     = request.getParameter("Word");
       String inputGroup     = request.getParameter("Group");       
       String inputNumberOfInstances     = request.getParameter("NumberOfInstances");           
       String inputNumberOfChars = request.getParameter("NumberOfChars");
       String inputStartIndex = request.getParameter("StartIndex");
       int maxInPage = 20;
       int filterWordsSize = 0;

    	

         
       wordsList = wordDAO.FilterWords(inputStartIndex,String.valueOf(maxInPage),inputWord,inputGroup,inputNumberOfInstances,inputNumberOfChars);
       filterWordsSize =  wordDAO.FilterWordsSize(inputStartIndex,String.valueOf(maxInPage),inputWord,inputGroup,inputNumberOfInstances,inputNumberOfChars);
    	
        // Dynamic content for each tab
        request.setAttribute("WordsTabData", wordsList);        
        request.setAttribute("StartIndex", Integer.parseInt(inputStartIndex));
        request.setAttribute("WordsSize", filterWordsSize);
        request.setAttribute("MaxInPage", maxInPage);
     

        // Forward request to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("FilterWords.jsp");
        dispatcher.forward(request, response);
    }
}
