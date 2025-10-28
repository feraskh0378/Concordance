<%@ page import="com.concordance.model.SongWord, java.util.ArrayList, java.util.List, java.util.Map, java.util.HashMap"  contentType="text/html;charset=UTF-8" language="java" %>

<%
String groupName  =  (String) request.getAttribute("GroupNameData") ;
ArrayList<SongWord> words  =  (ArrayList<SongWord>)  request.getAttribute("GroupWordsData") ;
int                 groupsWordsSize  =  (int) request.getAttribute("GroupWordsSize") ;
int                 maxSizeInPage  =  (int) request.getAttribute("MaxSizeInPage") ;
int inputStartIndex =   	(int) request.getAttribute("StartIndex");

%>
			<table id="GroupWordsTable"  class="tablecontent" > 
    				<tr> 
      					<td colspan="6">  		
  							Words in <div id="TableTitle" ><%= groupName %></div>
						</td>	
					</tr>
	  				<tr>
			            <th>Word</th>            
			            <th>Song</th>
			            <th>Verse</th>
			            <th>Line</th>			            
			            <th>Position in line</th>
			            <th>Group</th>
        			</tr> 
                	<tr>
			             <td><input type="txt" id="Word"       oninput="filterGroupWords(0)"></td>                
			             <td><input type="txt" id="Song"   oninput="filterGroupWords(0)"></td>			             
			             <td><input type="txt" id="Verse" oninput="filterGroupWords(0)"></td>
			             <td><input type="txt" id="Line" oninput="filterGroupWords(0)"></td>			           
			             <td><input type="txt" id="PlaceInLine" oninput="filterGroupWords(0)"></td>
			             <td><input type="txt" id="Group"  value="<%= groupName %>" disabled  oninput="filterGroupWords(0)"></td>              
			         </tr>    
			          <% 
        	
            
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
	       %>	
	       <tr>
	        <% if(inputStartIndex != 0) { %>
      			<td colspan=3 >  		
 		 			<button class="circle-btn" onclick="filterGroupWords(<%= inputStartIndex - maxSizeInPage%>)">&lt;</button>
				</td>
			<% } else { %>
				<td colspan=3 >  		
 		 			<button class="disabled-circle-btn" onclick="">&lt;</button>
				</td>							
			<% } %>
					
		    <% if(inputStartIndex + maxSizeInPage >= groupsWordsSize) { %>
						<td colspan=4>  		
		 		 				<button class="disabled-circle-btn" onclick="">&gt;</button>
						</td>
					<% } else { %>
					<td colspan=4>  	
					     <button class="circle-btn" onclick="filterGroupWords(<%= inputStartIndex + maxSizeInPage %>)">&gt;</button>
					     </td>
						<% } %>
	        </tr>	   	
 		</table> 
  
