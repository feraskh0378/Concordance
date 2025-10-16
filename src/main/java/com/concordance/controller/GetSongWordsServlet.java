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


public class GetSongWordsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	WordsDAO wordDAO = new WordsDAO();
    	wordDAO.initConnection();
    	ArrayList<SongWord>  wordsList = null;
    	String SongTitle = new String();
	   String inputSongId     = request.getParameter("SongId");
	   String inputSongTitle     = request.getParameter("SongTitle");
	   String inputStartIndex     = request.getParameter("StartIndex");
	   int MaxSizeInPage = 20;
	   
	   wordsList = wordDAO.getSongWords(inputSongId,inputStartIndex,String.valueOf(MaxSizeInPage));
	   int songWordsSize = wordDAO.getSongWordsSize(inputSongId,inputStartIndex,String.valueOf(MaxSizeInPage));
                  
    	
        // Dynamic content for each tab
        request.setAttribute("SongWordsData", wordsList);
        request.setAttribute("SongTitleData", inputSongTitle);
        request.setAttribute("SongWordsSize", songWordsSize);
        request.setAttribute("MaxSizeInPage", MaxSizeInPage);
        request.setAttribute("StartIndex", Integer.parseInt(inputStartIndex));
        
        
     

        // Forward request to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("GetSongWords.jsp");
        dispatcher.forward(request, response);
    }
}
