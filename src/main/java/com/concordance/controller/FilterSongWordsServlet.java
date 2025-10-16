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


public class FilterSongWordsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	WordsDAO wordDAO = new WordsDAO();
    	wordDAO.initConnection();
    	ArrayList<SongWord>  wordsList = null;
    	
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
         
       wordsList = wordDAO.FilterSongWords(inputStartIndex, String.valueOf(maxInPage),inputWord,filterWord,inputSong,filterSong,inputGroup,filterGroup,inputVerse,inputLine,inputPlaceInLine);
       filterSongsSize = wordDAO.FilterSongWordsSize(inputStartIndex,  String.valueOf(maxInPage), inputWord, filterWord, inputSong, filterSong, inputGroup, filterGroup, inputVerse, inputLine, inputPlaceInLine);
       
       
        // Dynamic content for each tab
        request.setAttribute("SongWordsData", wordsList);
        request.setAttribute("StartIndex", Integer.parseInt(inputStartIndex));
        request.setAttribute("WordsSize", filterSongsSize);
        request.setAttribute("MaxInPage", maxInPage);
     
     

        // Forward request to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("FilterSongWords.jsp");
        dispatcher.forward(request, response);
    }
}
