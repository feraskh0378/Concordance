<%@ page import="com.concordance.model.Word, java.util.ArrayList, java.util.List, java.util.Map, java.util.HashMap"  contentType="text/html;charset=UTF-8" language="java" %>

     
      
        <% 
            ArrayList<Word> words  =  (ArrayList<Word>) request.getAttribute("WordsTabData") ;
        	for(Word word : words)
          	{ 
	        %>                 
	         <tr id='<%= word.getWord() %>' onclick="getWordSongs('<%= word.getWord() %>',0); ToggleSelectedWord(this);">         
	             <td class="StringTd" ><%= word.getWord() %></td>                 
	             <td class="StringTd"><%= word.getGroup() %></td>
	             <td class="IntTd"><%= word.getNumberOfInstancesDB() %></td>
	             <td class="IntTd"><%= word.getNumberOfChars() %></td>          
	         </tr>
	         
		   <%
	       } 
         	int inputStartIndex =   	(int) request.getAttribute("StartIndex");
        	int WordsSize =   	(int) request.getAttribute("WordsSize");
        	int MaxInPage =   	(int) request.getAttribute("MaxInPage");
	       %>	
	        <tr>
	        <% if(inputStartIndex != 0) { %>
      			<td colspan=2 >  		
 		 			<button class="circle-btn" onclick="filterWords(<%= inputStartIndex - MaxInPage%>)">&lt;</button>
				</td>
			<% } else { %>
				<td colspan=2 >  		
 		 			<button class="disabled-circle-btn" onclick="">&lt;</button>
				</td>							
			<% } %>
					
		    <% if(inputStartIndex + MaxInPage >= WordsSize) { %>
						<td colspan=2>  		
		 		 				<button class="disabled-circle-btn" onclick="">&gt;</button>
						</td>
					<% } else { %>
					<td colspan=2>  	
					     <button class="circle-btn" onclick="filterWords(<%= inputStartIndex + MaxInPage %>)">&gt;</button>
					     </td>
						<% } %>
	        </tr>	   