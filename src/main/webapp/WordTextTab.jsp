<%@ page import="com.concordance.model.SongWord, java.util.ArrayList, java.util.List, java.util.Map, java.util.HashMap"  contentType="text/html;charset=UTF-8" language="java" %>

	            <table id="TextGroupsTable" width=600px height=500px > 
	  			<tr> 
	      				<td colspan="2">  		
	  		 				${SongTitle}
						</td>	
					</tr>
	    			<tr style="width:100%; height:100%;"> 
	      				<td colspan="2" style="text-align: left; vertical-align: top;" >  		
	  		 				<div id="TextGroupsLines"  >
	  		 				
	  		 				 <%
	  
	   int nextVerse =  (int) request.getAttribute("NextVerse") ;
	   int prevVerse =  (int) request.getAttribute("PrevVerse") ;	   	  
	   ArrayList<ArrayList<SongWord>> textVerses = (ArrayList<ArrayList<SongWord>>) request.getAttribute("WordTextData");
	   String word = (String) request.getAttribute("WordData");
	  	        
        for(ArrayList<SongWord> verse : textVerses)
   	   {
       	   int firstLine = verse.getFirst().getLine();
	   	   for(SongWord textWord : verse)
	   	   {		
	   		 if(textWord.getLine() != firstLine)
		  	   {
		  		out.print("<br>");   
		  		firstLine = textWord.getLine();
		  	   }
	   		 
	   		 	if(textWord.getWord().equals(word))
	   		 	{
	   		 		out.print("<strong>" + textWord.getWord() + " </strong>");	   		 	
	   		 	}
	   		 	else
	   		 	{
	   	   			out.print(textWord.getWord() + " ");
	   		 	}
		  	  
	   	   }
	   	  out.print("<br><br>");
   	   }
        /*   request.setAttribute("NextVerse", nextVerse);
            request.setAttribute("PrevVerse", prevVerse);
            request.setAttribute("ViewId", viewId);
            */
   	   %>
	  		 				
	  		 				</div>
						</td>	
					</tr>
					   <tr>
	        <% if(prevVerse != 0) { %>
      			<td>  		
 		 			<button class="circle-btn" onclick="ShowWordText('${WordId}','<%= prevVerse%>','${ViewID}')">&lt;</button>
				</td>
			<% } else { %>
				<td >  		
 		 			<button class="disabled-circle-btn" onclick="">&lt;</button>
				</td>							
			<% } %>
					
		    <% if(nextVerse == 0) { %>
				<td >  		
			 				<button class="disabled-circle-btn" onclick="">&gt;</button>
				</td>
			<% } else { %>
			<td >  	
			     <button class="circle-btn" onclick="ShowWordText('${WordId}','<%= nextVerse%>','${ViewID}')">&gt;</button>
			     </td>
				<% } %>
	        </tr>	   	
				</table> 
   
     
      