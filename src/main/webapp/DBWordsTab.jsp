<%@ page import="com.concordance.objects.DBWord, java.util.ArrayList, java.util.List, java.util.Map, java.util.HashMap"  contentType="text/html;charset=UTF-8" language="java" %>

     <table class="tablecontent" >
      
      
        <% 
            ArrayList<DBWord> words  =  (ArrayList<DBWord>) request.getAttribute("WordsTabData") ;
        	for(DBWord word : words){
        %>                 
         <tr onclick="getWordsTable('WordDocumentsTable', '<%= word.getWord() %>',null,null,null)">         
             <td class="StringTd" ><%= word.getWord() %></td>                 
             <td class="StringTd"><%= word.getGroup() %></td>
             <td class="IntTd"><%= word.getNumberOfInstances() %></td>
             <td class="IntTd"><%= word.getNumberOfLetters() %></td>          
         </tr>
         
	       <%
	       } 
	       %>	
    </table> 
