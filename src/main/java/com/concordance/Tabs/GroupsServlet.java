package com.concordance.Tabs;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.concordance.objects.Document;
import com.concordance.objects.Group;


public class GroupsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	   ArrayList<Group>  GroupsList = new ArrayList<Group>();
    	   GroupsList.add(new Group(0, "TXT",5));
      	
    	   String parGroup  = request.getParameter("Group");
           String parNumberOfWords = request.getParameter("NumberOfWords");
           
           ArrayList<Group>  retGroupsList = new ArrayList<Group>();
       	for(Group group : GroupsList)
       	{
       		 
       		if(     (parGroup == ""  || group.getGroup().contains(parGroup))   &&       				
       				(parNumberOfWords == ""    || String.valueOf(group.getNumberOfWords()).equals(parNumberOfWords)))        				
       		{
       			retGroupsList.add(group);
       		}
       	}
       	
    	
        // Dynamic content for each tab
        request.setAttribute("GroupsTabData", retGroupsList);
     

        // Forward request to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("GroupsTab.jsp");
        dispatcher.forward(request, response);
    }
}
