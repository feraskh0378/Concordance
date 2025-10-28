<%@ page import="com.concordance.model.Group, java.util.ArrayList, java.util.List, java.util.Map, java.util.HashMap"  contentType="text/html;charset=UTF-8" language="java" %>
				  				
				        <% 
				            ArrayList<Group> groups  =  (ArrayList<Group>) request.getAttribute("GroupsTabData") ;
				        	for(Group group : groups){
				        %>
				         
				        <tr id='<%= group.getId() %>' onclick="getGroupWords(<%= group.getId() %>,'<%= group.getGroup() %>',0);ToggleSelectedGroup(this);">				             
				             <td class="StringTd" ><%= group.getGroup() %></td>                
				             <td class="StringTd"><%= group.getNumberOfWords()%></td>				             
				         </tr>
				         
					       <%
					       } 
				        	int inputStartIndex =   	(int) request.getAttribute("StartIndex");
				        	int groupsSize =   	(int) request.getAttribute("GroupsSize");
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
									
						    <% if(inputStartIndex + MaxInPage >= groupsSize) { %>
										<td colspan=4>  		
						 		 				<button class="disabled-circle-btn" onclick="">&gt;</button>
										</td>
									<% } else { %>
									<td colspan=4>  	
									     <button class="circle-btn" onclick="FilterSongs(<%= inputStartIndex + MaxInPage %>)">&gt;</button>
									     </td>
										<% } %>
					        </tr>	