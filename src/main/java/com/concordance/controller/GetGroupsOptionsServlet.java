package com.concordance.controller; 

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import com.concordance.dao.GroupsDAO;
import com.concordance.model.Group;


public class GetGroupsOptionsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
    	GroupsDAO dao = new GroupsDAO();
    	dao.initConnection();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ArrayList<Group> groups = dao.getAllGroups();
        try
        {
        	PrintWriter out = response.getWriter();
        	boolean printComma = false;
        	 out.print("[");
             for(Group g : groups ) 
             {
            	 if(printComma)
            	 {            		 
            		 out.print(",");
            	 }
            	 printComma = true;
             	out.print("{ \"id\":\""+g.getId()+"\",\"name\":\""+g.getGroup()+"\"  }");             	
             }
             out.print("]");
             out.flush();
        }
        catch(Exception e)
        {
        	
        }
       
    }
}