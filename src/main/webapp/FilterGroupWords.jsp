<%@ page import="com.concordance.model.SongWord, java.util.ArrayList, java.util.List, java.util.Map, java.util.HashMap"  contentType="text/html;charset=UTF-8" language="java" %>
			          <% 
        	int i = 0;
            ArrayList<SongWord> words  =  (ArrayList<SongWord>) request.getAttribute("SongWordsData") ;
        	for(SongWord songWord : words)
        	{
        		
            %>         
	        <tr id='<%= songWord.getId() %>' onclick="ShowWordText('<%= songWord.getId() %>',0,'TextGroupLines'); ToggleSelectedGroupWord(this);">
	            <td class="StringTd" ><%= songWord.getWord() %></td>                
	            <td class="StringTd" ><%= songWord.getSong() %></td>
	            <td class="IntTd"><%= songWord.getVerse() %></td>
	            <td class="IntTd"><%= songWord.getLine() %></td>             
	            <td class="IntTd"><%= songWord.getLinePlace() %></td>
	            <td class="StringTd"><%= songWord.getGroup() %></td>
	        </tr>
         
	       <%
	       } 
        	int inputStartIndex =   	(int) request.getAttribute("StartIndex");
        	int WordsSize =   	(int) request.getAttribute("WordsSize");
        	int MaxInPage =   	(int) request.getAttribute("MaxInPage");
	       %>	
	        <tr>
	        <% if(inputStartIndex != 0) { %>
      			<td colspan=3 >  		
 		 			<button class="circle-btn" onclick="filterGroupWords(<%= inputStartIndex - MaxInPage%>)">&lt;</button>
				</td>
			<% } else { %>
				<td colspan=3 >  		
 		 			<button class="disabled-circle-btn" onclick="">&lt;</button>
				</td>							
			<% } %>
					
		    <% if(inputStartIndex + MaxInPage >= WordsSize) { %>
						<td colspan=4>  		
		 		 				<button class="disabled-circle-btn" onclick="">&gt;</button>
						</td>
					<% } else { %>
					<td colspan=4>  	
					     <button class="circle-btn" onclick="filterGroupWords(<%= inputStartIndex + MaxInPage %>)">&gt;</button>
					     </td>
						<% } %>
	        </tr>	   	
  
