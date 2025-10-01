package com.concordance.Tabs;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.concordance.objects.Document;

public class DocumentsServlet extends HttpServlet 
{
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	ArrayList<Document>  documentsList = new ArrayList<Document>();
    	documentsList.add(new Document(0, "Feras","Feras Khoury",3,6,10));
    	documentsList.add(new Document(1, "Daniel","Daniel GH",5,10,4));  
    	documentsList.add(new Document(2, "Israel","Daniel GH",5,10,4));
    	documentsList.add(new Document(3, "Israel New","Daniel GH",5,10,4));
    	documentsList.add(new Document(4, "Israel New","Daniel GH",5,10,4));
    	documentsList.add(new Document(5, "Israel New","Daniel GH",5,10,4));
    	documentsList.add(new Document(6, "Israel New","Daniel GH",5,10,4));
    	documentsList.add(new Document(7, "Israel New","Daniel GH",5,10,4));
    	documentsList.add(new Document(8, "Israel New","Daniel GH",5,10,4));
    	documentsList.add(new Document(9, "Israel New","Daniel GH",5,10,4));
    	documentsList.add(new Document(10, "Israel New","Daniel GH",5,10,4));
    	documentsList.add(new Document(11, "Israel New","Daniel GH",5,10,4));
    	documentsList.add(new Document(12, "Israel New","Daniel GH",5,10,4));
    	documentsList.add(new Document(13, "Israel New","Daniel GH",5,10,4));
    	documentsList.add(new Document(14, "Israel New","Daniel GH",5,10,4));

    	
        String inputTitle  = request.getParameter("Title");
        String inputAuthor = request.getParameter("Author");
        String inputNumberOfVerses    = request.getParameter("NumberOfVerses");
        String inputNumberOfLines    = request.getParameter("NumberOfLines");
        String inputNumberOfWords    = request.getParameter("NumberOfWords");
          	
        ArrayList<Document>  retDocumentsList = new ArrayList<Document>();
    	for(Document doc : documentsList)
    	{
    		if(     (inputTitle == ""  || doc.title.contains(inputTitle))   &&
    				(inputAuthor == "" || doc.author.contains(inputAuthor)) &&
    				(inputNumberOfVerses == ""    || String.valueOf(doc.numberOfVerses).equals(inputNumberOfVerses)  ) &&
    				(inputNumberOfLines == ""    || String.valueOf(doc.numberOfLines).equals(inputNumberOfLines) ) &&
    				(inputNumberOfWords == ""    || String.valueOf(doc.numberOfWords).equals(inputNumberOfWords) ))
    		{
    			retDocumentsList.add(doc);
    		}
    	}
    	
        // Dynamic content for each tab
        request.setAttribute("DocumentsTabData", retDocumentsList);
     

        // Forward request to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("DocumentsTable.jsp");
        dispatcher.forward(request, response);
    
    }
}
