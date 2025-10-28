package com.concordance.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import com.concordance.model.Song;
import com.concordance.model.Word;
import com.concordance.dao.*; 

@WebServlet("/upload")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 15
)
public class UploadServlet extends HttpServlet
{
	
	private static final String UPLOAD_DIR = "uploads";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	SongsDAO songs = new SongsDAO();    	
    	WordsDAO wordsDAO = new WordsDAO();
    	wordsDAO.initConnection();
    	songs.initConnection();
    	   
    	//Get file part and metadata
        Part filePart = request.getPart("file");
        String title = request.getParameter("title");
        String poet = request.getParameter("poet");
        String composer = request.getParameter("composer");
        String date = request.getParameter("date");
        int numberOfWords = 0;
        int verseNumber = 1;
        int lineNumber = 1; 
        int placeInLine = 1;
        
        
        int newSongID = songs.addSong(new Song(0,title,poet,composer,date, 0,0,0));
        
        if(newSongID == 0)
        {
        	return;
        }
       
        // Save file
        String fileName		  = filePart.getSubmittedFileName();
        InputStream fileData  =   filePart.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileData, "UTF-8"));
        
        String line;
        while ((line = reader.readLine()) != null) 
        {
        	if(line.isEmpty())
        	{
        		verseNumber++;
        		continue;
        	}                	
        	String[] words = line.split(" ");
        	
        	placeInLine = 1;
        	
        	for (String word : words) 
        	{        		
        		wordsDAO.addWord(new Word(0,word,"",title,verseNumber,lineNumber,placeInLine,0,0),newSongID);
        		placeInLine++;
        		numberOfWords++;
        	}
        	
        	lineNumber++;
        }
        
        songs.updateSong(newSongID,new Song(newSongID,title,poet,composer,date, verseNumber,lineNumber,numberOfWords));

  
        // Redirect or show message
        request.setAttribute("message", "Song uploaded successfully!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("UploadSong.jsp");
        dispatcher.forward(request, response);
    }
}
