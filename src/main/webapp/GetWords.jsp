<%@ page import="com.concordance.model.Word, java.util.ArrayList, java.util.List, java.util.Map, java.util.HashMap"  contentType="text/html;charset=UTF-8" language="java" %>

<%

ArrayList<Word> words  =  (ArrayList<Word>) request.getAttribute("WordsTabData") ;
int                 wordsSize  =  (int) request.getAttribute("SongsSize") ;
int                 maxSizeInPage  =  (int) request.getAttribute("MaxInPage") ;
int inputStartIndex =   	(int) request.getAttribute("StartIndex");

%>
        <table id="WordsTable" class="tablecontent" >
			
				<tr> 
     				<td colspan="4">  		
 					<div >Words in the DataBase</div>
				</td>	
			</tr>
 				<tr>
  					<th>Word</th>        
  					<th>Group</th>                	   					
  					<th>Frequency</th>
  					<th>Number of chars</th>
 				</tr>
 				<tr>
  					<td><input type="txt" id="Word"               oninput="filterWordsTable('WordsTable',0)"></td>                
  					<td><input type="txt" id="Group"              oninput="filterWordsTable('WordsTable',0)"></td>
  					<td><input type="txt" id="NumberOfInstances"  oninput="filterWordsTable('WordsTable',0)"></td>
  					<td><input type="txt" id="NumberOfChars"      oninput="filterWordsTable('WordsTable',0)"></td>            
 				</tr>  
	  			     
      
      
        <% 
          
        	for(Word word : words)
          	{ 
	        %>                 
	         <tr onclick="getWordSongsTable('WordSongsTable', '<%= word.getWord() %>',0)">   
	             <td class="StringTd" ><%= word.getWord() %></td>                 
	             <td class="StringTd"><%= word.getGroup() %></td>
	             <td class="IntTd"><%= word.getNumberOfInstancesDB() %></td>
	             <td class="IntTd"><%= word.getNumberOfChars() %></td>          
	         </tr>
	         
		   <%
	       } 
	       %>	
	       <tr>
	        <% if(inputStartIndex != 0) { %>
      			<td colspan=2 >  		
 		 			<button class="circle-btn" onclick="filterWordsTable('WordsTable',<%= inputStartIndex - maxSizeInPage%>)">&lt;</button>
				</td>
			<% } else { %>
				<td colspan=2 >  		
 		 			<button class="disabled-circle-btn" onclick="">&lt;</button>
				</td>							
			<% } %>
					
		    <% if(inputStartIndex + maxSizeInPage >= wordsSize) { %>
						<td colspan=2>  		
		 		 				<button class="disabled-circle-btn" onclick="">&gt;</button>
						</td>
					<% } else { %>
					<td colspan=2>  	
					     <button class="circle-btn" onclick="filterWordsTable('WordsTable',<%= inputStartIndex + maxSizeInPage %>)">&gt;</button>
					     </td>
						<% } %>
	        </tr>	   	
    </table> 
