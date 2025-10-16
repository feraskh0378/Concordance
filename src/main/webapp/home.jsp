<%@ page import="com.concordance.controller.*, java.util.ArrayList, java.util.List, java.util.Map, java.util.HashMap"  contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tabs Example</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
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




.disabled-circle-btn {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 20px;
  height: 20px;
  border-radius: 50%;
  background: #d3d3d3;   /* blue background */
  color: white;
  font-size: 20px;
  font-weight: bold;
  cursor: pointer;
  transition: background 0.3s;
  border: none;
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
     let currentTab = 'SongsTab';
     function mySubmitFunction() {
   	  alert("Form submitted without reloading!");
   	}  
     </script>
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="js/GroupsTab.js"></script>
    <script src="js/WordsTab.js"></script>
    <script src="js/SongsTab.js"></script>
    <script src="js/home.js"></script>    
        
</head>
<body>
<div class="modal fade" id="uploadModal" tabindex="-1" aria-labelledby="uploadLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="Upload" method="post" enctype="multipart/form-data" id="uploadForm">
        <div class="modal-header">
          <h5 class="modal-title" id="uploadLabel">Upload Song</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="mb-3">
            <label class="form-label">Title</label>
            <input type="text" class="form-control" name="title" required>
          </div>
          <div class="mb-3">
            <label class="form-label">Poet</label>
            <input type="text" class="form-control" name="poet" required>
          </div>
          <div class="mb-3">
            <label class="form-label">Composer</label>
            <input type="text" class="form-control" name="composer" required>
          </div>
          <div class="mb-3">
            <label class="form-label">Date</label>
            <input type="date" class="form-control" name="date" required>
          </div>
          <div class="mb-3">
            <label class="form-label">Poetry File (.txt)</label>
            <input type="file" class="form-control" name="file" accept=".txt" required>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
          <button type="submit" class="btn btn-primary">Upload</button>
        </div>
      </form>
    </div>
  </div>
</div>


<div class="tab">
    <button class="tablinks" onclick="openTab(event, 'SongsTab')">Documents</button>
    <button class="tablinks" onclick="openTab(event, 'WordsTab')">Words</button>
    <button class="tablinks" onclick="openTab(event, 'GroupsTab')">Words Groups</button>
    <button class="tablinks" onclick="openTab(event, 'PhrasesTab')">Phrases</button>
</div>

 <div id="SongsTab" class="tabcontent" style="background: #e6f7ff;">
 	<table style="background: #e6f7ff;">
 		<tr>
 			<td class=tdtable>
 				<div id="SongsView">
	 			  
  				</div>
  			</td>
  			<td class="tdtable" >
  				<div id="SongWordsView">
  				</div>  				
  			</td>
 			<td class="tdtable" >
 			<div style="width:100%; height:100px;" id="TextSongsLines"  >
	  			<table id="TextSongsTable" hidden> 
	  			<tr> 
	      				<td colspan="2">  		
	  		 				Text
						</td>	
					</tr>
	    			<tr style="width:100%; height:100%;"> 
	      				<td colspan="2" >  		
	  		 				
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
				ss</div>
			</td>
  		</tr> 
  </table>
</div>

<div id="WordsTab" class="tabcontent" style="background: #e6f7ff;" >
		<table style="background: #e6f7ff;">
		<tr>
			 <td>
			 	<div id="WordsView">
	 			  
  				</div>	 			
	  		</td>
	  		<td class="tdtable" >
	  		
	  		<div id="WordSongsView">
	 			  
  				</div>	
	  			<table id="WordSongsTable" hidden> 
	    			<tr> 
	      				<td colspan="6">  		
	  					Songs have <div id="TableTitle" >Feras</div>
						</td>	
					</tr>
		  			<tr>
	            		<th>Word</th>	            		              
	            		<th>Song</th>
	            		<th>Verse</th>
	            		<th>Line</th>	 
	            		  <th>PlaceInLine</th>
			            <th>Group</th>           		
	        		</tr> 
	                <tr>
	             		<td><input type="txt" id="Word" disabled oninput="getSongWordsTable('WordSongsTable')"></td>                
	             		<td><input type="txt" id="Song"  value=""  oninput="getSongWordsTable('WordSongsTable')"></td>
	             		<td><input type="txt" id="Verse" oninput="getSongWordsTable('WordSongsTable')"></td>
	             		<td><input type="txt" id="Line" oninput="getSongWordsTable('WordSongsTable')"></td>
	             		<td><input type="txt" id="PlaceInLine" oninput="getSongWordsTable('WordSongsTable')"></td>
			             <td><input type="txt" id="Group"  value="" oninput="getSongWordsTable('WordSongsTable')"></td>	             		            
	         		</tr>       
	 	 		</table> 
	  		</td>
	  		<td class="tdtable" >
	  		<div style="width:100%; height:100px;" id="TextWordsLines" hidden >
			  			<table  id="TextWordsTable" hidden> 
			  				<tr> 
			      				<td colspan="2">  		
			  		 				Text
								</td>	
							</tr>
							<tr style="width:100%; height:100%;"> 
			      				<td colspan="2" >  		
			  		 			
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
					</div>
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
  			<table id="GroupWordSongsTable" hidden> 
	    			<tr> 
	      				<td colspan="5">  		
	  					Words in  <div id="TableTitle" >Feras</div>
						</td>	
					</tr>
		  			<tr>
	            		<th>Word</th>	            		              
	            		<th>Song</th>
	            		<th>Verse</th>
	            		<th>Line</th>	 
	            		  <th>Frequency</th>
			            <th>Group</th>           		
	        		</tr> 
	                <tr>
	             		<td><input type="txt" id="Word"  oninput="getSongWordsTable('GroupWordSongsTable')"></td>                
	             		<td><input type="txt" id="Song"  value=""  oninput="getSongWordsTable('GroupWordSongsTable')"></td>
	             		<td><input type="txt" id="Verse" oninput="getSongWordsTable('GroupWordSongsTable')"></td>
	             		<td><input type="txt" id="Line" oninput="getSongWordsTable('GroupWordSongsTable')"></td>
	             		<td><input type="txt" id="Frequency" oninput="getSongWordsTable('GroupWordSongsTable')"></td>
			             <td><input type="txt" id="Group" disabled value="" oninput="getSongWordsTable('GroupWordSongsTable')"></td>	             		            
	         		</tr>       
	 	 		</table>   				
  			</td>
 			<td class="tdtable" >
 				<div style="width:100%; height:100px;" id="TextGroupsLines"  >
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
				</div>
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
