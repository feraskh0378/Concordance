package com.concordance.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.concordance.dao.WordsDAO;
import com.concordance.dao.GroupsDAO;
import com.concordance.model.SongWord;
import com.concordance.model.Word;


public class GetGroupWordsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	GroupsDAO groupDAO = new GroupsDAO();
    	groupDAO.initConnection();
    	ArrayList<SongWord>  wordsList = null;
    	String SongTitle = new String();
	   String inputGroupId     = request.getParameter("GroupId");
	   String inputGroupName     = request.getParameter("GroupName");
	   String inputStartIndex     = request.getParameter("StartIndex");
	   int MaxSizeInPage = 20;
	   
	   wordsList = groupDAO.getGroupWords(inputGroupId,inputStartIndex,String.valueOf(MaxSizeInPage));
	   int songWordsSize = groupDAO.getGroupWordsSize(inputGroupId,inputStartIndex,String.valueOf(MaxSizeInPage));
                  
    	
        // Dynamic content for each tab
        request.setAttribute("GroupWordsData", wordsList);
        request.setAttribute("GroupNameData", inputGroupName);
        request.setAttribute("GroupWordsSize", songWordsSize);
        request.setAttribute("MaxSizeInPage", MaxSizeInPage);
        request.setAttribute("StartIndex", Integer.parseInt(inputStartIndex));
        
        
     

        // Forward request to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("GetGroupWords.jsp");
        dispatcher.forward(request, response);
    }
}
