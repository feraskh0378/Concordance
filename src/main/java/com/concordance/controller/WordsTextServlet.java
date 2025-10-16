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
	    	
    	   String wordId  = request.getParameter("WordId");
    	   String word    = request.getParameter("Word");
    	   String song    = request.getParameter("Song");
    	   String songId    = request.getParameter("SongId");
    	   String verse   = request.getParameter("Verse");
    	   String line    = request.getParameter("Line");
    	   String linePlace  = request.getParameter("LinePlace");
    	   String[] text= new String[10];
    	   
    	   ArrayList<ArrayList<SongWord>> Verses = new ArrayList<ArrayList<SongWord>>();
    	   
    	   Song songObject = songsDoa.getSong(wordsDoa.getSongId(Integer.parseInt(wordId))); 
    	   
    	   
    	   int intVerse = Integer.parseInt(verse);
    	   if(intVerse > 1)
    	   {    		 
    		   String prevVerse = String.valueOf(intVerse - 1);
    		   ArrayList<SongWord> FirstVerse = wordsDoa.FilterSongWords("0","100","",false, song, false,"",false,prevVerse,"","");
    		   Verses.add(FirstVerse);
    	   }
    	   
    	   ArrayList<SongWord> WordVerse = wordsDoa.FilterSongWords("0","100","",false, song, false,"",false,verse,"","");
    	   Verses.add(WordVerse);
    	   
    	   
    	   if(intVerse < songObject.getNumberOfVerses())
    	   {
    		   String nextVerse = String.valueOf(intVerse + 1);
    		   ArrayList<SongWord> FirstVerse = wordsDoa.FilterSongWords("0","100","",false, song, false,"",false,nextVerse,"","");
    		   Verses.add(FirstVerse);
    	   }
    	  	      	   
            // Dynamic content for each tab
            request.setAttribute("WordTextData", Verses);
            request.setAttribute("WordData", word);
     

            // Forward request to JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("WordTextTab.jsp");
            dispatcher.forward(request, response);
    }
}
