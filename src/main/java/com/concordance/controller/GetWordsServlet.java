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


public class GetWordsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	WordsDAO wordDAO = new WordsDAO();
    	wordDAO.initConnection();
    	ArrayList<Word>  wordsList = null;
    	int maxInPage = 20;
    	int wordsSize = 0;
    	
       String inputStartIndex = request.getParameter("StartIndex");

    	

         
       wordsList = wordDAO.getAllWords(inputStartIndex,String.valueOf(maxInPage));
       wordsSize  = wordDAO.getAllWordsSize(inputStartIndex,String.valueOf(maxInPage));
    	
        // Dynamic content for each tab
        request.setAttribute("WordsTabData", wordsList);
        request.setAttribute("StartIndex", Integer.parseInt(inputStartIndex));
        request.setAttribute("SongsSize", wordsSize);
        request.setAttribute("MaxInPage", maxInPage);
     

        // Forward request to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("GetWords.jsp");
        dispatcher.forward(request, response);
    }
}
