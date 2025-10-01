<%@ page import="com.concordance.Home.*, java.util.ArrayList, java.util.List, java.util.Map, java.util.HashMap"  contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tabs Example</title>
    <style>

.upload-btn {
  display: inline-flex;
  align-items: center;
  gap: 5px;
  padding: 8px 8px;
  background: #28a745;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 10px;
}
.upload-btn:hover {
  background: #218838;
}





.circle-btn {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 20px;
  height: 20px;
  border-radius: 50%;
  background: #007bff;   /* blue background */
  color: white;
  font-size: 20px;
  font-weight: bold;
  cursor: pointer;
  transition: background 0.3s;
  border: none;
}
.circle-btn:hover {
  background: #0056b3;   /* darker on hover */
}




    
        .tab { overflow: hidden; border-bottom: 1px solid #ccc; }
        .tab button { background-color: inherit; float: left; border: none; padding: 10px 16px; cursor: pointer; }
        .tab button.active { background-color: #ccc; }
        .tabcontent { display: none; padding: 6px; border: 1px solid #ccc; border-top: none; }
        .StringTd {}
        .IntTd { width : 20px}
        input { width : 60px}
         table {         	
            border-collapse: collapse;         	
            background: white;
            width: 400px;
        }

        th, td {
            padding: 5px 5px;
            text-align: center;
              font-size: 13px;
        }
        
        .tdtable
        {
        padding: 5px 5px;
	vertical-align:top;
              font-size: 13px;
        }

        th {
            background: #4CAF50;
            color: white;
            font-size: 13px;
        }

        tr:nth-child(even) {
            background: #f9f9f9;
        }

        tr:hover {
            background: #e6f7ff;
            cursor: pointer;
        }

        td {
            border-bottom: 1px solid #ddd;
        }
    </style>
     <script>
     let currentTab = 'DocumentsTab';
     </script>
    <script src="js/GroupsTab.js"></script>
    <script src="js/WordsTab.js"></script>
    <script src="js/DocumentTab.js"></script>
    <script src="js/home.js"></script>    
        
</head>
<body>

<div class="tab">
    <button class="tablinks" onclick="openTab(event, 'DocumentsTab')">Documents</button>
    <button class="tablinks" onclick="openTab(event, 'WordsTab')">Words</button>
    <button class="tablinks" onclick="openTab(event, 'GroupsTab')">Words Groups</button>
    <button class="tablinks" onclick="openTab(event, 'PhrasesTab')">Phrases</button>
</div>

 <div id="DocumentsTab" class="tabcontent" style="background: #e6f7ff;">
 	<table style="background: #e6f7ff;">
 		<tr>
 			<td>
 				<table id="DocumentsTable">
 					<tr> 
	      				<td >  
	      		

 <form action="upload" method="submit" enctype="multipart/form-data">
    <label for="file-upload" class="upload-btn">
      <i class="fa-solid fa-upload"></i> Upload Document
    </label>
    <input id="file-upload" type="file" name="file" hidden />
  </form>
	      				
	      				      				
	      			
						</td>		
						<td colspan="4">
	  					<div >Documents in the DataBase</div>
						</td>	
					</tr>
   					<tr>
			            <th>Title</th>            
			            <th>Author</th>
			            <th>Number of verses</th>
			            <th>Number of lines</th>
			            <th>Number of words</th>
			        </tr>          
 					<tr>
    					<td><input type="txt" id="Title"  value=""  oninput="getDocumentsTable('DocumentsTable')"></td>          
    					<td><input type="txt" id="Author" value="" oninput="getDocumentsTable('DocumentsTable')"></td>
    					<td><input type="txt" id="NumberOfVerses"    value="" oninput="getDocumentsTable('DocumentsTable')"></td>
    					<td><input type="txt" id="NumberOfLines"    value="" oninput="getDocumentsTable('DocumentsTable')"></td>
    					<td><input type="txt" id="NumberOfWords"    value="" oninput="getDocumentsTable('DocumentsTable')"></td>
    				</tr>     
  				</table>  
  			</td>
  			<td class="tdtable" >
  				<table id="DocumentWordsTable" hidden> 
    				<tr> 
      					<td colspan="6">  		
  							Words in <div id="TableTitle" >Feras</div>
						</td>	
					</tr>
	  				<tr>
			            <th>Word</th>            
			            <th>Document</th>
			            <th>Verse</th>
			            <th>Line</th>			            
			            <th>Frequency</th>
			            <th>Group</th>
        			</tr> 
                	<tr>
			             <td><input type="txt" id="Word"       oninput="getWordsTable('DocumentWordsTable')"></td>                
			             <td><input type="txt" id="Document"  value="" disabled oninput="getWordsTable('DocumentWordsTable')"></td>			             
			             <td><input type="txt" id="Verse" oninput="getWordsTable('DocumentWordsTable')"></td>
			             <td><input type="txt" id="Line" oninput="getWordsTable('DocumentWordsTable')"></td>			           
			             <td><input type="txt" id="Frequency" oninput="getWordsTable('DocumentWordsTable')"></td>
			             <td><input type="txt" id="Group"  value="" oninput="getWordsTable('DocumentWordsTable')"></td>              
			         </tr>       
 	 			</table> 
  			</td>
 			<td class="tdtable" >
	  			<table id="TextDocumentsTable" hidden> 
	  			<tr> 
	      				<td colspan="2">  		
	  		 				Text
						</td>	
					</tr>
	    			<tr style="width:100%; height:100%;"> 
	      				<td colspan="2" >  		
	  		 				<div style="width:100%; height:100px;" id="TextDocumentsLines"  >ss</div>
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
			</td>
  		</tr> 
  </table>
</div>

<div id="WordsTab" class="tabcontent" style="background: #e6f7ff;" >
		<table style="background: #e6f7ff;">
		<tr>
			 <td>
	 			<table id="WordsTable"> 
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
	   					<td><input type="txt" id="DBWord"               oninput="getDBWordsTable('WordsTable')"></td>                
	   					<td><input type="txt" id="Group"              oninput="getDBWordsTable('WordsTable')"></td>
	   					<td><input type="txt" id="NumberOfInstances"  oninput="getDBWordsTable('WordsTable')"></td>
	   					<td><input type="txt" id="NumberOfChars"      oninput="getDBWordsTable('WordsTable')"></td>            
	  				</tr>  
	  			</table>  
	  		</td>
	  		<td class="tdtable" >
	  			<table id="WordDocumentsTable" hidden> 
	    			<tr> 
	      				<td colspan="6">  		
	  					Documents have <div id="TableTitle" >Feras</div>
						</td>	
					</tr>
		  			<tr>
	            		<th>Word</th>	            		              
	            		<th>Document</th>
	            		<th>Verse</th>
	            		<th>Line</th>	 
	            		  <th>Frequency</th>
			            <th>Group</th>           		
	        		</tr> 
	                <tr>
	             		<td><input type="txt" id="Word" disabled oninput="getWordsTable('WordDocumentsTable')"></td>                
	             		<td><input type="txt" id="Document"  value=""  oninput="getWordsTable('WordDocumentsTable')"></td>
	             		<td><input type="txt" id="Verse" oninput="getWordsTable('WordDocumentsTable')"></td>
	             		<td><input type="txt" id="Line" oninput="getWordsTable('WordDocumentsTable')"></td>
	             		<td><input type="txt" id="Frequency" oninput="getWordsTable('WordDocumentsTable')"></td>
			             <td><input type="txt" id="Group"  value="" oninput="getWordsTable('WordDocumentsTable')"></td>	             		            
	         		</tr>       
	 	 		</table> 
	  		</td>
	  		<td class="tdtable" >
	  			<table  id="TextWordsTable" hidden> 
	  				<tr> 
	      				<td colspan="2">  		
	  		 				Text
						</td>	
					</tr>
					<tr style="width:100%; height:100%;"> 
	      				<td colspan="2" >  		
	  		 				<div style="width:100%; height:100px;" id="TextWordsLines"  ></div>
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
			</td>
		</tr> 
	</table>
</div>

<div id="GroupsTab" class="tabcontent" style="background: #e6f7ff;">  
	<table style="background: #e6f7ff;">
		<tr >
			 <td class="tdtable" >
				<table id="GroupsTable" style="width:150px;"> 
					<tr > 
	      				<td colspan="2">  		
	  						<div >Groups in the DataBase</div>
						</td>	
					</tr>
  					<tr>
            			<th>Group</th>            
            			<th>Word</th>            
        			</tr>
        			<tr>
             			<td><input type="txt" id="GroupGroup" oninput="getGroupsTable(1)"></td>                
             			<td><input type="txt" id="GroupNumberOfWords"  oninput="getGroupsTable(1)"></td>                   
         			</tr>  
  				</table> 
  			</td>  			
  			<td class="tdtable" >
  			<table id="GroupWordDocumentsTable" hidden> 
	    			<tr> 
	      				<td colspan="5">  		
	  					Words in  <div id="TableTitle" >Feras</div>
						</td>	
					</tr>
		  			<tr>
	            		<th>Word</th>	            		              
	            		<th>Document</th>
	            		<th>Verse</th>
	            		<th>Line</th>	 
	            		  <th>Frequency</th>
			            <th>Group</th>           		
	        		</tr> 
	                <tr>
	             		<td><input type="txt" id="Word"  oninput="getWordsTable('GroupWordDocumentsTable')"></td>                
	             		<td><input type="txt" id="Document"  value=""  oninput="getWordsTable('GroupWordDocumentsTable')"></td>
	             		<td><input type="txt" id="Verse" oninput="getWordsTable('GroupWordDocumentsTable')"></td>
	             		<td><input type="txt" id="Line" oninput="getWordsTable('GroupWordDocumentsTable')"></td>
	             		<td><input type="txt" id="Frequency" oninput="getWordsTable('GroupWordDocumentsTable')"></td>
			             <td><input type="txt" id="Group" disabled value="" oninput="getWordsTable('GroupWordDocumentsTable')"></td>	             		            
	         		</tr>       
	 	 		</table>   				
  			</td>
 			<td class="tdtable" >
	  			<table id="TextGroupsTable" hidden> 
	  			<tr> 
	      				<td colspan="2">  		
	  		 				Text
						</td>	
					</tr>
	    			<tr style="width:100%; height:100%;"> 
	      				<td colspan="2" >  		
	  		 				<div style="width:100%; height:100px;" id="TextGroupsLines"  >ss</div>
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
			</td>
  		</tr> 
  </table>
</div>   



<div id="PhrasesTab" class="tabcontent">  
<table id="PhrasesTable"> 
  <tr>
            <th>Phrase</th>            
            
        </tr>
        <tr>
             <td><input type="txt" id="Phrase" oninput="getWordsTable(1)"></td>                                               
         </tr>  
  </table>    
</div>

</body>
</html>
