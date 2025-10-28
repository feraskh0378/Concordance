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
        .highlight {
  
  background:yellow !important;
}
.tooltip {
  position: relative;
  display: inline-block;
  cursor: pointer;
}

.tooltip .tooltiptext {
  visibility: hidden;
  width: 140px;
  background-color: #333;
  color: #fff;
  text-align: center;
  padding: 6px;
  border-radius: 4px;
  position: absolute;
  z-index: 1;
  bottom: 125%; /* above the button */
  left: 50%;
  transform: translateX(-50%);
  opacity: 0;
  transition: opacity 0.3s;
}

.tooltip:hover .tooltiptext {
  visibility: visible;
  opacity: 1;
}
    </style>
     <script>
     let currentTab = 'SongsTab';
     let SelectedSong = null;
     let SelectedSongWord = null;
     let SelectedWord = null;
     let SelectedWordSong = null;
     let SelectedGroup = null;
     let SelectedGroupWord = null;
     
     
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
<div class="modal fade" id="newGroup" tabindex="-1" aria-labelledby="uploadLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="NewGroup" method="post" enctype="multipart/form-data" id="newGroupForm">
        <div class="modal-header">
          <h5 class="modal-title" id="addWordToGroupLabel">Choose group name</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="mb-3">
            <label class="form-label">Group Name</label> 
            <input type="text" class="form-control" name="groupName" id="GroupName" required>
          </div>                 
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
          <button type="submit" class="btn btn-primary">Add</button>
        </div>
      </form>
    </div>
  </div>
</div>
<div class="modal fade" id="addWordToGroup" tabindex="-1" aria-labelledby="uploadLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="AddWordToGroup" method="post" enctype="multipart/form-data" id="addWordToGroupForm">
        <div class="modal-header">
          <h5 class="modal-title" id="addWordToGroupLabel">Choose group</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="mb-3">
            <label class="form-label">Word</label> 
            <input type="text" class="form-control" name="word" id="AddWordToGroupInput" required>
          </div>         
          <div class="mb-3">
            <label class="form-label">Group</label>
            <input list="groups" class="form-control" name="group" required>
            <datalist id="groups">
  				
			</datalist>
          </div>          
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
          <button type="submit" class="btn btn-primary">Add</button>
        </div>
      </form>
    </div>
  </div>
</div>
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

 <div id="SongsTab" class="tabcontent" style="background: #e6f7ff; height:1000px;">
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
	  		</div>
			</td>
  		</tr> 
  </table>
</div>

<div id="WordsTab" class="tabcontent" style="background: #e6f7ff; height:1000px;">
		<table style="background: #e6f7ff;">
		<tr>
			 <td>
			 	<div id="WordsView">
	 			  
  				</div>	 			
	  		</td>
	  		<td class="tdtable" >
	  		
	  		<div id="WordSongsView">
	 			  
  				</div>	
	  			
	  		</td>
	  		<td class="tdtable" >
	  		 <div style="width:100%; height:100px;" id="TextWordsLines" hidden >			  			
			 </div>
			</td>
		</tr> 
	</table>
</div>

<div id="GroupsTab" class="tabcontent" style="background: #e6f7ff; height:1000px;">  
	<table style="background: #e6f7ff;">
		<tr>
			 <td>
			 	<div id="GroupsView">
	 			  
  				</div>	 			
	  		</td>
	  		<td class="tdtable" >
	  		
	  		<div id="GroupWordsView">
	 			  
  				</div>	
	  			
	  		</td>
	  		<td class="tdtable" >
	  		 <div style="width:100%; height:100px;" id="TextGroupLines" hidden >			  			
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
