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
  					<td><input type="txt" id="Word"               oninput="filterWords(0)"></td>                
  					<td><input type="txt" id="Group"              oninput="filterWords(0)"></td>
  					<td><input type="txt" id="NumberOfInstances"  oninput="filterWords(0)"></td>
  					<td><input type="txt" id="NumberOfChars"      oninput="filterWords(0)"></td>            
 				</tr>  
	  			     
      
      
        <% 
          
        	for(Word word : words)
          	{ 
	        %>                 
	         <tr id='<%= word.getWord() %>' onclick="getWordSongs('<%= word.getWord() %>',0); ToggleSelectedWord(this); HideWordText('TextWordsLines'); UpdateAddWordToGroupBox('<%= word.getWord() %>');">   
	             <td class="StringTd" ><%= word.getWord() %></td>                 
	             <td class="StringTd"><%= word.getGroup() %></td>
	             <td class="IntTd"><%= word.getNumberOfInstancesDB() %></td>
	             <td class="IntTd"><%= word.getNumberOfChars() %></td>          
	         </tr>
	         
		   <%
	       } 
	       %>	
	       <tr>
	       <td colspan=1 >  
	      <button type="button" class="btn btn-primary"  title="Click to add the word to group" data-bs-toggle="modal" data-bs-target="#addWordToGroup">
  Add to group 
</button>
	       	</td>
	        <% if(inputStartIndex != 0) { %>
      			<td colspan=1 >  		
 		 			<button class="circle-btn" onclick="filterWords(<%= inputStartIndex - maxSizeInPage%>)">&lt;</button>
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
					     <button class="circle-btn" onclick="filterWords(<%= inputStartIndex + maxSizeInPage %>)">&gt;</button>
					     </td>
						<% } %>
	        </tr>	   	
    </table> 
