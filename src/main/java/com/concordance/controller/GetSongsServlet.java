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

public class GetSongsServlet extends HttpServlet 
{
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	ArrayList<Song>  songsList = null;
    	SongsDAO songsDAO = new SongsDAO(); 	
    	songsDAO.initConnection();
    	int maxInPage = 40;
    	
    	
        String inputStartIndex  = request.getParameter("StartIndex");
        
        songsList = songsDAO.getAllSongs(inputStartIndex, String.valueOf(maxInPage)); 
        int songsSize = songsDAO.getSongsSize();
          	    	
        // Dynamic content for each tab
        request.setAttribute("SongsTabData", songsList);
        request.setAttribute("StartIndex", Integer.parseInt(inputStartIndex));
        request.setAttribute("SongsSize", songsSize);
        request.setAttribute("MaxInPage", maxInPage);
        
     

        // Forward request to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("GetSongsTable.jsp");
        dispatcher.forward(request, response);
    
    }
}
