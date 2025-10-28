<%@ page import="com.concordance.model.Song, java.util.ArrayList, java.util.List, java.util.Map, java.util.HashMap"  contentType="text/html;charset=UTF-8" language="java" %>
				  				
				        <% 
				            ArrayList<Song> songs  =  (ArrayList<Song>) request.getAttribute("SongsTabData") ;
				        	for(Song song : songs){
				        %>
				         
				         <tr id='<%= song.getId() %>' onclick="getSongWords(<%= song.getId() %>,'<%= song.getTitle() %>',0); ToggleSelectedSong(this); HideWordText('TextSongsLines');">
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