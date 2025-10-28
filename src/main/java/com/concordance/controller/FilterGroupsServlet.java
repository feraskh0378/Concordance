package com.concordance.controller;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.concordance.dao.GroupsDAO;
import com.concordance.dao.SongsDAO;
import com.concordance.model.Group;
import com.concordance.model.Song;

public class FilterGroupsServlet extends HttpServlet 
{
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	ArrayList<Group>  groupsList = null;
    	int filterGroupsSize = 0;
    	GroupsDAO groupsDAO = new GroupsDAO(); 	
    	groupsDAO.initConnection();
    	
    	
        String inputGroup  = request.getParameter("GroupName");
        String inputNumberOfWords = request.getParameter("NumberOfWords");
        String inputStartIndex  = request.getParameter("StartIndex");
        int maxInPage = 40;
        
        
        groupsList = groupsDAO.filterGroups(inputStartIndex, String.valueOf(maxInPage),inputGroup,inputNumberOfWords);
        filterGroupsSize = groupsDAO.filterGroupsSize(inputStartIndex, String.valueOf(maxInPage),inputGroup,inputNumberOfWords);
          	    	
        // Dynamic content for each tab
        request.setAttribute("GroupsTabData", groupsList);
        request.setAttribute("StartIndex", Integer.parseInt(inputStartIndex));
        request.setAttribute("GroupsSize", filterGroupsSize);
        request.setAttribute("MaxInPage", maxInPage);
     

        // Forward request to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("FilterGroups.jsp");
        dispatcher.forward(request, response);
    
    }
}
