<%@ page import="com.concordance.objects.Group, java.util.ArrayList, java.util.List, java.util.Map, java.util.HashMap"  contentType="text/html;charset=UTF-8" language="java" %>

     <table class="tablecontent" >
      
      
        <% 
            ArrayList<Group> groups  =  (ArrayList<Group>) request.getAttribute("GroupsTabData") ;
        	for(Group group : groups)
        	{
        %>
         
         
         <tr onclick="getWordsTable('GroupWordDocumentsTable', null,null,null,null,null,'<%= group.getGroup() %>')">
             <td ><%= group.getGroup()%></td>                
             <td ><%= group.getNumberOfWords()%></td>             
         </tr>
         
	       <%
	       } 
	       %>	
    </table> 
