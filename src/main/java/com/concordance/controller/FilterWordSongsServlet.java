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


public class FilterWordSongsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
       WordsDAO wordDAO = new WordsDAO();
       wordDAO.initConnection();
       ArrayList<SongWord>  wordsList = null;
       String SongTitle = new String();
	   	  	
	   String inputWord     = request.getParameter("Word");
	   Boolean filterWord     = request.getParameter("FilterWord").equals("false") ? false : true;	   
       String inputSong     = request.getParameter("Song");
       Boolean filterSong     = request.getParameter("FilterSong").equals("false") ? false : true;
       String inputGroup    = request.getParameter("Group");
       Boolean filterGroup     = request.getParameter("FilterGroup").equals("false") ? false : true;
       String inputVerse    = request.getParameter("Verse");
       String inputLine     = request.getParameter("Line");                        
       String inputPlaceInLine = request.getParameter("LinePlace");
       String inputStartIndex = request.getParameter("StartIndex");
       int maxInPage = 20;
       int filterSongsSize = 0;
	   int MaxSizeInPage = 20;
	   
	   wordsList = wordDAO.FilterSongWords(inputStartIndex, String.valueOf(MaxSizeInPage),inputWord,false, inputSong,true,inputGroup,true,inputVerse,inputLine,inputPlaceInLine);
	   int songWordsSize = wordDAO.FilterSongWordsSize(inputStartIndex, String.valueOf(MaxSizeInPage),inputWord,false, inputSong,true,inputGroup,true,inputVerse,inputLine,inputPlaceInLine);
                  
    	
        // Dynamic content for each tab
        request.setAttribute("WordSongsData", wordsList);        
        request.setAttribute("WordSongsSize", songWordsSize);
        request.setAttribute("MaxSizeInPage", MaxSizeInPage);
        request.setAttribute("StartIndex", Integer.parseInt(inputStartIndex));
        request.setAttribute("WordTextData", inputWord);
        
        
        
     

        // Forward request to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("FilterWordSongs.jsp");
        dispatcher.forward(request, response);
    }
}
