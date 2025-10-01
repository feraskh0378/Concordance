<%@ page import="com.concordance.objects.Document, java.util.ArrayList, java.util.List, java.util.Map, java.util.HashMap"  contentType="text/html;charset=UTF-8" language="java" %>

        <% 
            ArrayList<Document> documents  =  (ArrayList<Document>) request.getAttribute("DocumentsTabData") ;
        	for(Document doc : documents){
        %>
         
         <tr onclick="getWordsTable('DocumentWordsTable', null,'<%= doc.title %>',null,null)">
             <td class="StringTd" ><%= doc.title %></td>                
             <td class="StringTd"><%= doc.author %></td>
             <td class="IntTd"><%= doc.numberOfVerses %></td>
             <td class="IntTd"><%= doc.numberOfLines %></td>
             <td class="IntTd"><%= doc.numberOfWords %></td>
         </tr>
         
	       <%
	       } 
	       %>	
	       
	        <tr>
	        <td colspan=3>  		
	  		 			<button class="circle-btn">&lt;</button>
						</td>	
						<td colspan=3>  		
	  		 				<button class="circle-btn">&gt;</button>
						</td>
	        </tr>
	   