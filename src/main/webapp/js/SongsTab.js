
function ShowWordText(wordID,word,song,verse,line,linePlace)
{
	let data = new URLSearchParams();
		
	   data.append("WordId", wordID);
	   data.append("Word", word);
	   data.append("Song", song);
	   data.append("Verse", verse);
	   data.append("Line", line);
	   data.append("LinePlace", linePlace);
	   				   
	   fetch("WordsText", {
	     method: "POST",
	     body: data
	   })
	   .then(res => res.text())
	   .then(data => {
		let view = document.getElementById("TextWordsLines");
	 
		if(currentTab == 'SongsTab')
		{
	
			view = document.getElementById("TextSongsLines");
		}
		else
		{
			if(currentTab == "GroupsTab")
			{
	
			    view = document.getElementById("TextGroupsLines");				
			}
		}					
		view.innerHTML = data;
		view.hidden = false;
	
						
		}
	);
	          
	
}


function FilterSongsTable(tableId,fromIndex)
{	
	let data = new URLSearchParams();
	
	
		let  table = document.getElementById(tableId);
			
		   
	   let title  = table.querySelector('[id="Title"]');
	   let poet =  table.querySelector('[id="Poet"]');
	   let composer =  table.querySelector('[id="Composer"]');
	   let numberOfVerses    =  table.querySelector('[id="NumberOfVerses"]');
	   let numberOfLines    =  table.querySelector('[id="NumberOfLines"]');
	   let numberOfWords    =  table.querySelector('[id="NumberOfWords"]'); 
	   
	  			  
	 
	   data.append("Title", title.value);
	   data.append("Poet", poet.value);
	   data.append("Composer", composer.value);
	   data.append("NumberOfVerses", numberOfVerses.value);
	   data.append("NumberOfLines", numberOfLines.value);
	   data.append("NumberOfWords", numberOfWords.value);
	   data.append("StartIndex", fromIndex);
 
	   	   
   fetch("FilterSongs", {
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


function getSongs(fromIndex)
{	
	let data = new URLSearchParams();
	
   data.append("StartIndex", fromIndex);	   
   fetch("GetSongs", {
     method: "POST",
     body: data
   })
   .then(res => res.text())
   .then(data => {
		let view = document.getElementById("SongsView");

		view.innerHTML = data;					
		}
	);
          
};