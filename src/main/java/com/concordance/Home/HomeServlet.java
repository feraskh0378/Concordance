package com.concordance.Home;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.concordance.objects.Document;

public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	ArrayList<Document>  documentsList = new ArrayList<Document>();
    	documentsList.add(new Document(0, "Feras","Feras",3,3,3));
    	documentsList.add(new Document(1, "Feras","Feras",3,3,3));
    	documentsList.add(new Document(2, "Feras","Feras",3,3,3));
    	documentsList.add(new Document(3, "Feras","Feras",3,3,3));
    	documentsList.add(new Document(4, "Feras","Feras",3,3,3));
    	documentsList.add(new Document(5, "Feras","Feras",3,3,3));
    	documentsList.add(new Document(6, "Feras","Feras",3,3,3));
    	documentsList.add(new Document(7, "Feras","Feras",3,3,3));
    	
        // Dynamic content for each tab
      //  request.setAttribute("DocumentsTabData", documentsList);
     

        // Forward request to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }
}
