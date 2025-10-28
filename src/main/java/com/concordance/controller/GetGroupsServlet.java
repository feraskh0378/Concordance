package com.concordance.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.concordance.dao.GroupsDAO;
import com.concordance.model.Group;


public class GetGroupsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	GroupsDAO groupsDAO = new GroupsDAO();
    	groupsDAO.initConnection();
    	ArrayList<Group>  GroupsList = null;
    	int maxInPage = 20;
    	int groupsSize = 0;
    	
       String inputStartIndex = request.getParameter("StartIndex");

    	

         
       GroupsList = groupsDAO.getAllGroups(inputStartIndex,String.valueOf(maxInPage));
       groupsSize  = groupsDAO.getAllGroupsSize(inputStartIndex,String.valueOf(maxInPage));
    	
        // Dynamic content for each tab
        request.setAttribute("GroupsTabData", GroupsList);
        request.setAttribute("StartIndex", Integer.parseInt(inputStartIndex));
        request.setAttribute("GroupsSize", groupsSize);
        request.setAttribute("MaxInPage", maxInPage);
     

        // Forward request to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("GetGroups.jsp");
        dispatcher.forward(request, response);
    }
}
