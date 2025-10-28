<%@ page import="com.concordance.model.Song, java.util.ArrayList, java.util.List, java.util.Map, java.util.HashMap"  contentType="text/html;charset=UTF-8" language="java" %>

				<table id="SongsTable">
	 					<tr> 
		      				<td colspan="2">
	
								<button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#uploadModal">
	  							  Upload New Song
								</button>
							</td>		
							<td colspan="4">
		  					<div >Songs in the DataBase</div>
							</td>	
						</tr>
	   					<tr>
	   						<th>Id</th>
				            <th>Title</th>            
				            <th>Poet</th>
				            <th>Composer</th>
				            <th>Date</th>
				            <th>Number of verses</th>
				            <th>Number of lines</th>
				            <th>Number of words</th>
				        </tr>          
	 					<tr>
	 					    <td><input type="txt" id="Id"  value=""            oninput="FilterSongs(0)"></td>
	    					<td><input type="txt" id="Title"  value=""            oninput="FilterSongs(0)"></td>          
	    					<td><input type="txt" id="Poet" value=""              oninput="FilterSongs(0)"></td>
	    					<td><input type="txt" id="Composer" value=""          oninput="FilterSongs(0)"></td>
	    					<td><input type="txt" id="Date" value=""          oninput="FilterSongs(0)"></td>
	    					<td><input type="txt" id="NumberOfVerses"    value="" oninput="FilterSongs(0)"></td>
	    					<td><input type="txt" id="NumberOfLines"    value=""  oninput="FilterSongs(0)"></td>
	    					<td><input type="txt" id="NumberOfWords"    value=""  oninput="FilterSongs(0)"></td>
	    				</tr>     
	  				
				        <% 
				            ArrayList<Song> songs  =  (ArrayList<Song>) request.getAttribute("SongsTabData") ;
				        	for(Song song : songs){
				        %>
				         
				         <tr id='<%= song.getId() %>' onclick="getSongWords(<%= song.getId() %>,'<%= song.getTitle() %>',0);ToggleSelectedSong(this);">
				             <td class="IntTd" ><%= song.getId() %></td>
				             <td class="StringTd" ><%= song.getTitle() %></td>                
				             <td class="StringTd"><%= song.getPoet() %></td>
				             <td class="StringTd"><%= song.getComposer() %></td>
				             <td class="StringTd"><%= song.getDate() %></td>
				             <td class="IntTd"><%= song.getNumberOfVerses() %></td>
				             <td class="IntTd"><%= song.getNumberOfLines() %></td>
				             <td class="IntTd"><%= song.getNumberOfWords() %></td>
				         </tr>
				         
					       <%
					       } 
				        	int inputStartIndex =   	(int) request.getAttribute("StartIndex");
				        	int SongsSize =   	(int) request.getAttribute("SongsSize");
				        	int MaxInPage =   	(int) request.getAttribute("MaxInPage");
					       %>	
					       
					        <tr>
					        <% if(inputStartIndex != 0) { %>
				      			<td colspan=3 >  		
				 		 			<button class="circle-btn" onclick="FilterSongs(<%= inputStartIndex - MaxInPage%>)">&lt;</button>
								</td>
							<% } else { %>
								<td colspan=3 >  		
				 		 			<button class="disabled-circle-btn" onclick="">&lt;</button>
								</td>							
							<% } %>
									
						    <% if(inputStartIndex + MaxInPage >= SongsSize) { %>
										<td colspan=4>  		
						 		 				<button class="disabled-circle-btn" onclick="">&gt;</button>
										</td>
									<% } else { %>
									<td colspan=4>  	
									     <button class="circle-btn" onclick="FilterSongs(<%= inputStartIndex + MaxInPage %>)">&gt;</button>
									     </td>
										<% } %>
					        </tr>
	   			</table>	   			