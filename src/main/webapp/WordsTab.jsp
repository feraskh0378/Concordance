<%@ page import="com.concordance.objects.Word, java.util.ArrayList, java.util.List, java.util.Map, java.util.HashMap"  contentType="text/html;charset=UTF-8" language="java" %>

     <table class="tablecontent" >
      
      
        <% 
        	int i = 0;
            ArrayList<Word> words  =  (ArrayList<Word>) request.getAttribute("WordsTabData") ;
        	for(Word word : words)
        	{
        		
        %>
         
	        <tr onclick="ShowWordText('<%= word.getWord() %>' , '<%=word.getDocument()%>','<%=word.getLine()%>')">
	            <td class="StringTd" ><%= word.getWord() %></td>                
	            <td class="StringTd" ><%= word.getDocument() %></td>
	            <td class="IntTd"><%= word.getVerse() %></td>
	            <td class="IntTd"><%= word.getLine() %></td>             
	            <td class="IntTd"><%= word.getFrequency() %></td>
	            <td class="StringTd"><%= word.getGroup() %></td>
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
    </table> 
