package com.concordance.controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.concordance.dao.WordsDAO;
import com.concordance.dao.SongsDAO;
import com.concordance.model.Song;
import com.concordance.model.SongWord;
/*
data.append("WordId", wordID);
data.append("Word", word);
data.append("Song", song);
data.append("Verse", verse);
data.append("Line", line);
data.append("LinePlace", linePlace);
*/

public class WordsTextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
	    	WordsDAO wordsDoa = new WordsDAO();
	    	SongsDAO songsDoa = new SongsDAO();
	    	wordsDoa.initConnection();
	    	songsDoa.initConnection();
	    	
	       ArrayList<ArrayList<SongWord>> Verses = new ArrayList<ArrayList<SongWord>>();	
    	   String inputWordId  = request.getParameter("WordId");
    	   String inputVerse  = request.getParameter("Verse");
    	   String viewId  = request.getParameter("ViewId");
    	   SongWord songWord = wordsDoa.getSongWord(Integer.parseInt(inputWordId));
    	   
    	       	     	  
    	   int songID = wordsDoa.getSongId(Integer.parseInt(inputWordId));
    	   Song songObject = songsDoa.getSong(songID); 
    	       	   
    	   int wordVerse = songWord.getVerse();
    	   if(Integer.parseInt(inputVerse) != 0)
    	   {
    		   wordVerse = Integer.parseInt(inputVerse);
    	   }
    	   
    	   
    	   ArrayList<SongWord> WordVerse = wordsDoa.filterSongWords(String.valueOf(songID),"0","100",String.valueOf(wordVerse));    	   
    	   Verses.add(WordVerse);
    	   
    	   if((wordVerse + 1) <= songObject.getNumberOfVerses())
    	   {    		 
    		   String nextVerse = String.valueOf(wordVerse + 1); 
    		   ArrayList<SongWord> FirstVerse = wordsDoa.filterSongWords(String.valueOf(songID),"0","100",nextVerse);    		   
    		   Verses.add(FirstVerse);
    	   }
    	  
    	   
    	   
    	   if((wordVerse + 2) <= songObject.getNumberOfVerses())
    	   {
    		   String nextVerse = String.valueOf(wordVerse + 2);
    		   ArrayList<SongWord> FirstVerse = wordsDoa.filterSongWords(String.valueOf(songID),"0","100",nextVerse);    		   
    		   Verses.add(FirstVerse);
    	   }
    	  	      	   
            // Dynamic content for each tab
            request.setAttribute("WordTextData", Verses);
            request.setAttribute("WordData", songWord.getWord());
            
            int nextVerse = 0;
            int prevVerse = 0;
            if(wordVerse < songObject.getNumberOfVerses())
            	nextVerse = wordVerse + 1;
            
            if(wordVerse > 1)
            	prevVerse = wordVerse + -1;
            
            request.setAttribute("NextVerse", nextVerse);
            request.setAttribute("PrevVerse", prevVerse);
            request.setAttribute("ViewID", viewId);
            request.setAttribute("WordId", inputWordId);
            request.setAttribute("SongTitle", songObject.getTitle());
            
            
            // Forward request to JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("WordTextTab.jsp");
            dispatcher.forward(request, response);
    }
}
