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


public class GetWordSongsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	WordsDAO wordDAO = new WordsDAO();
    	wordDAO.initConnection();
    	ArrayList<SongWord>  wordsList = null;
    	String SongTitle = new String();
	   String inputWordText     = request.getParameter("WordText");	   
	   String inputStartIndex     = request.getParameter("StartIndex");
	   int MaxSizeInPage = 20;
	   
	   
	   
	   wordsList = wordDAO.FilterSongWords(inputStartIndex, String.valueOf(MaxSizeInPage),inputWordText,false, "",false,"",false,"","","");
	   int songWordsSize = wordDAO.FilterSongWordsSize(inputStartIndex, String.valueOf(MaxSizeInPage),inputWordText,false, "",false,"",false,"","","");
                  
    	
        // Dynamic content for each tab
        request.setAttribute("WordSongsData", wordsList);        
        request.setAttribute("WordSongsSize", songWordsSize);
        request.setAttribute("MaxSizeInPage", MaxSizeInPage);
        request.setAttribute("StartIndex", Integer.parseInt(inputStartIndex));
        request.setAttribute("WordTextData", inputWordText);
        
        
        
     

        // Forward request to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("GetWordSongs.jsp");
        dispatcher.forward(request, response);
    }
}
