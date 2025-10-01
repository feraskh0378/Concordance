package com.concordance.Tabs;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.concordance.objects.Document;
import com.concordance.objects.Word;

public class WordsTextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	   String parWord  = request.getParameter("Word");
           String parDocument = request.getParameter("Document");           
           String parLine    = request.getParameter("Line");           
         
        // Dynamic content for each tab
        request.setAttribute("WordTextData", "Feras feasdadas dada sdsa dsad adsad ad \n  dasdsadadadsadad");
     

        // Forward request to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("WordTextTab.jsp");
        dispatcher.forward(request, response);
    }
}
