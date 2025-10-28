<%@ page import="com.concordance.model.Group, java.util.ArrayList, java.util.List, java.util.Map, java.util.HashMap"  contentType="text/html;charset=UTF-8" language="java" %>

    <table id="GroupsTable">
	 					<tr> 
		      				<td colspan="1">
	
								<button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#newGroup">
	  							  New Group
								</button>
							</td>		
							<td colspan="1">
		  					<div >Groups in the DataBase</div>
		  					
							</td>	
						</tr>
	   					<tr>
				            <th>Group</th>            
				            <th>Number of words</th>
				        </tr>          
	 					<tr>
	 					    <td><input type="txt" id="GroupName"  value=""            oninput="FilterGroups(0)"></td>	    			
	    					<td><input type="txt" id="NumberOfWords"    value=""  oninput="FilterGroups(0)"></td>
	    				</tr>     
	  				
				        <% 
				            ArrayList<Group> groups  =  (ArrayList<Group>) request.getAttribute("GroupsTabData") ;
				        	for(Group group : groups)
				        	{
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
				      			<td colspan=1 >  		
				 		 			<button class="circle-btn" onclick="FilterGroups(<%= inputStartIndex - MaxInPage%>)">&lt;</button>
								</td>
							<% } else { %>
								<td colspan=1 >  		
				 		 			<button class="disabled-circle-btn" onclick="">&lt;</button>
								</td>							
							<% } %>
									
						    <% if(inputStartIndex + MaxInPage >= groupsSize) { %>
										<td colspan=1>  		
						 		 				<button class="disabled-circle-btn" onclick="">&gt;</button>
										</td>
									<% } else { %>
									<td colspan=1>  	
									     <button class="circle-btn" onclick="FilterGroups(<%= inputStartIndex + MaxInPage %>)">&gt;</button>
									     </td>
										<% } %>
					        </tr>
	   			</table>	  
