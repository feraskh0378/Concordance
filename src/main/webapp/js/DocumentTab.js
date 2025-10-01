function ShowWordText(word,doc,line)
{
	let data = new URLSearchParams();
		
	   data.append("Word", word);
	   data.append("Document", doc);	   
	   data.append("Line", line);

				   
	   fetch("WordsText", {
	     method: "POST",
	     body: data
	   })
	   .then(res => res.text())
	   .then(data => {
		let view = document.getElementById("TextWordsLines");
		let table = document.getElementById("TextWordsTable"); 
		if(currentTab == 'DocumentsTab')
		{
			table = document.getElementById("TextDocumentsTable");
			view = document.getElementById("TextDocumentsLines");
		}
		else
		{
			if(currentTab == "GroupsTab")
			{
				table = document.getElementById("TextGroupsTable");
			    view = document.getElementById("TextGroupsLines");				
			}
		}					
		view.innerHTML = data;
		view.hidden = false;
		table.hidden = false;
						
		}
	);
	          
	
}
function getDocumentsTable(tableId,fromIndex)
{	
	let data = new URLSearchParams();
	
	if(tableId)
	{
		let  table = document.getElementById(tableId);
			
		   
	   let title  = table.querySelector('[id="Title"]');
	   let author =  table.querySelector('[id="Author"]');
	   let numberOfVerses    =  table.querySelector('[id="NumberOfVerses"]');
	   let numberOfLines    =  table.querySelector('[id="NumberOfLines"]');
	   let numberOfWords    =  table.querySelector('[id="NumberOfWords"]'); 
	   
	  			  
	 
	   data.append("Title", title.value);
	   data.append("Author", author.value);
	   data.append("NumberOfVerses", numberOfVerses.value);
	   data.append("NumberOfLines", numberOfLines.value);
	   data.append("NumberOfWords", numberOfWords.value);
   }
   else
   {
		data.append("Title", "");
	    data.append("Author", "");
		data.append("NumberOfVerses", "");
		data.append("NumberOfLines", "");
		data.append("NumberOfWords", "");
   }
       
   data.append("FromIndex", fromIndex);	   
   fetch("DocumentsTable", {
     method: "POST",
     body: data
   })
   .then(res => res.text())
   .then(data => {
		let table = document.getElementById(tableId);
		
		// remove rows one by one (from bottom up)
		for (let i = table.rows.length - 1; i >= 3; i--) { table.deleteRow(i);}
		table.insertAdjacentHTML("beforeend", data);					
		}
	);
          
};