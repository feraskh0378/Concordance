<%@ page import="com.concordance.model.SongWord, java.util.ArrayList, java.util.List, java.util.Map, java.util.HashMap"  contentType="text/html;charset=UTF-8" language="java" %>

	            <table id="TextGroupsTable" width=600px height=500px > 
	  			<tr> 
	      				<td colspan="2">  		
	  		 				Text
						</td>	
					</tr>
	    			<tr style="width:100%; height:100%;"> 
	      				<td colspan="2" style="text-align: left; vertical-align: top;" >  		
	  		 				<div id="TextGroupsLines"  >
	  		 				
	  		 				 <%
	   ArrayList<ArrayList<SongWord>> textVerses = (ArrayList<ArrayList<SongWord>>) request.getAttribute("WordTextData");
	   String word = (String) request.getAttribute("WordData");
	  	        
        for(ArrayList<SongWord> verse : textVerses)
   	   {
       	   int firstLine = verse.getFirst().getLine();
	   	   for(SongWord textWord : verse)
	   	   {		
	   		 if(textWord.getLine() != firstLine)
		  	   {
		  		out.print("<br>");   
		  		firstLine = textWord.getLine();
		  	   }
	   		 
	   		 	if(textWord.getWord().equals(word))
	   		 	{
	   		 		out.print("<strong>" + textWord.getWord() + " </strong>");	   		 	
	   		 	}
	   		 	else
	   		 	{
	   	   			out.print(textWord.getWord() + " ");
	   		 	}
		  	  
	   	   }
	   	  out.print("<br><br>");
   	   }
   	   %>
	  		 				
	  		 				</div>
						</td>	
					</tr>
					<tr> 
	      				<td>  		
	  		 			<button class="circle-btn">&lt;</button>
						</td>	
						<td>  		
	  		 				<button class="circle-btn">&gt;</button>
						</td>
					</tr>
				</table> 
   
     
      