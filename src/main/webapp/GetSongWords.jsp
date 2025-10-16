<%@ page import="com.concordance.model.SongWord, java.util.ArrayList, java.util.List, java.util.Map, java.util.HashMap"  contentType="text/html;charset=UTF-8" language="java" %>

<%
String songTitle  =  (String) request.getAttribute("SongTitleData") ;
ArrayList<SongWord> words  =  (ArrayList<SongWord>)  request.getAttribute("SongWordsData") ;
int                 songsWordsSize  =  (int) request.getAttribute("SongWordsSize") ;
int                 maxSizeInPage  =  (int) request.getAttribute("MaxSizeInPage") ;
int inputStartIndex =   	(int) request.getAttribute("StartIndex");

%>
			<table id="SongsWordsTable"  class="tablecontent" > 
    				<tr> 
      					<td colspan="6">  		
  							Words in <div id="TableTitle" >Feras</div>
						</td>	
					</tr>
	  				<tr>
			            <th>Word</th>            
			            <th>Song</th>
			            <th>Verse</th>
			            <th>Line</th>			            
			            <th>Frequency</th>
			            <th>Group</th>
        			</tr> 
                	<tr>
			             <td><input type="txt" id="Word"       oninput="filterSongWordsTable('SongsWordsTable',0)"></td>                
			             <td><input type="txt" id="Song"  value="<%= songTitle %>" disabled oninput="filterSongWordsTable('SongsWordsTable',0)"></td>			             
			             <td><input type="txt" id="Verse" oninput="filterSongWordsTable('SongsWordsTable',0)"></td>
			             <td><input type="txt" id="Line" oninput="filterSongWordsTable('SongsWordsTable',0)"></td>			           
			             <td><input type="txt" id="PlaceInLine" oninput="filterSongWordsTable('SongsWordsTable',0)"></td>
			             <td><input type="txt" id="Group"  value="" oninput="filterSongWordsTable('SongsWordsTable',0)"></td>              
			         </tr>    
			          <% 
        	
            
        	for(SongWord songWord : words)
        	{
        		
            %>         
	        <tr onclick="ShowWordText('<%= songWord.getId() %>','<%= songWord.getWord() %>','<%= songWord.getSong() %>','<%= songWord.getVerse() %>','<%= songWord.getLine() %>','<%= songWord.getLinePlace() %>')">
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
 		 			<button class="circle-btn" onclick="filterSongWordsTable('SongsWordsTable',<%= inputStartIndex - maxSizeInPage%>)">&lt;</button>
				</td>
			<% } else { %>
				<td colspan=3 >  		
 		 			<button class="disabled-circle-btn" onclick="">&lt;</button>
				</td>							
			<% } %>
					
		    <% if(inputStartIndex + maxSizeInPage >= songsWordsSize) { %>
						<td colspan=4>  		
		 		 				<button class="disabled-circle-btn" onclick="">&gt;</button>
						</td>
					<% } else { %>
					<td colspan=4>  	
					     <button class="circle-btn" onclick="filterSongWordsTable('SongsWordsTable',<%= inputStartIndex + maxSizeInPage %>)">&gt;</button>
					     </td>
						<% } %>
	        </tr>	   	
 		</table> 
  
