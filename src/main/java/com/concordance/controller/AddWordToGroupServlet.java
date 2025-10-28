package com.concordance.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import com.concordance.model.Group;
import com.concordance.model.Song;
import com.concordance.model.Word;
import com.concordance.dao.*; 

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 15
)
public class AddWordToGroupServlet extends HttpServlet
{
	
	private static final String UPLOAD_DIR = "uploads";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	GroupsDAO groupsDAO = new GroupsDAO();    	
    	WordsDAO wordsDAO = new WordsDAO();
    	groupsDAO.initConnection();
    	wordsDAO.initConnection();
    	   
    	//Get file part and metadata
    	String word = request.getParameter("word");
        String groupName = request.getParameter("group");
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Group group = groupsDAO.getGroup(groupName);
        wordsDAO.updateWordGroup(word,group.getId());
        
        PrintWriter out = response.getWriter();
        out.print("OK");
    }
}
