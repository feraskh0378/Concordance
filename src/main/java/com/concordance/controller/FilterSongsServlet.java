package com.concordance.controller;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.concordance.dao.SongsDAO;
import com.concordance.model.Song;

public class FilterSongsServlet extends HttpServlet 
{
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	ArrayList<Song>  songsList = null;
    	int filterSongsSize = 0;
    	SongsDAO songsDAO = new SongsDAO(); 	
    	songsDAO.initConnection();
    	
    	
        String inputTitle  = request.getParameter("Title");
        String inputPoet = request.getParameter("Poet");
        String inputComposer = request.getParameter("Composer");
        String inputDate = request.getParameter("Date");
        String inputNumberOfVerses    = request.getParameter("NumberOfVerses");
        String inputNumberOfLines    = request.getParameter("NumberOfLines");
        String inputNumberOfWords    = request.getParameter("NumberOfWords");
        String inputStartIndex  = request.getParameter("StartIndex");
        int maxInPage = 40;
        
        
        songsList = songsDAO.filterSongs(inputStartIndex, String.valueOf(maxInPage),inputTitle,inputPoet,inputComposer,inputDate,inputNumberOfVerses,inputNumberOfLines,inputNumberOfWords);
        filterSongsSize = songsDAO.filterSongsSize(inputStartIndex, String.valueOf(maxInPage),inputTitle,inputPoet,inputComposer,inputDate,inputNumberOfVerses,inputNumberOfLines,inputNumberOfWords);
          	    	
        // Dynamic content for each tab
        request.setAttribute("SongsTabData", songsList);
        request.setAttribute("StartIndex", Integer.parseInt(inputStartIndex));
        request.setAttribute("SongsSize", filterSongsSize);
        request.setAttribute("MaxInPage", maxInPage);
     

        // Forward request to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("FilterSongs.jsp");
        dispatcher.forward(request, response);
    
    }
}
