function updateWordsDocumentsTable()
{
	
	let data = new URLSearchParams();
	
   let word  =  document.getElementById("WordWord");
   let doc =  document.getElementById("WordDocument");
   let verse    =  document.getElementById("WordVerse");
   let line    =  document.getElementById("WordLine");   
   data.append("Word", word.value);
   data.append("Document", doc.value);
   data.append("Verse", verse.value);
   data.append("Line", line.value);   
			   
   fetch("WordsTable", {
     method: "POST",
     body: data
   })
   .then(res => res.text())
   .then(data => {
	let table = document.getElementById("WordDocumentsTable");
	
	// remove rows one by one (from bottom up)
	for (let i = table.rows.length - 1; i >= 3; i--) {
	  table.deleteRow(i);
	}			
	table.insertAdjacentHTML("beforeend", data);

	table.hidden = false;
					
	}
);
          
};

function ShowWordDocuments(word)
{
	let data = new URLSearchParams();				   
   data.append("Word", word);
   data.append("Document", "");
   data.append("Verse", "");
   data.append("Line", "");   
   
   fetch("WordsTable", {
   		     method: "POST",
   		     body: data
   		   })
   		   .then(res => res.text())
   		   .then(data => {
   			let table = document.getElementById("WordDocumentsTable");
   			
   			// remove rows one by one (from bottom up)
   			for (let i = table.rows.length - 1; i >= 3; i--) {
   			  table.deleteRow(i);
   			}			
   			table.insertAdjacentHTML("beforeend", data);
			let tableName = document.getElementById("WordDocumentsTitle");
			tableName.innerHTML = word;
			tableName = document.getElementById("WordWord");
			tableName.value = word;
	
			table.hidden = false;
   							
   			}
   		);
};

function getWordSongsTable(tableId,word,fromIndex)
{
		let data = new URLSearchParams();
		
		let  table = document.getElementById(tableId);


		
	   data.append("WordText", word);	   
	   data.append("StartIndex", fromIndex);
	   
	   
	 
	   			   
	   fetch("GetWordSongs", {
	     method: "POST",
	     body: data
	   })
	   .then(res => res.text())
	   .then(data => 
		{
		let table = document.getElementById("WordSongsView");
		
		// remove rows one by one (from bottom up)
					
		table.innerHTML = data;

		table.hidden = false;
						
		}
	);
	
};


function filterWordSongsTable(tableId,fromIndex)
{
	
	let data = new URLSearchParams();
	
	let  table = document.getElementById(tableId);

	let wordObject  = table.querySelector('[id="Word"]');
	let wordInput  = wordObject.value;
	let songObject   = table.querySelector('[id="Song"]');
	let songInput   = songObject.value;
	let verseInput = table.querySelector('[id="Verse"]').value;
	let lineInput  = table.querySelector('[id="Line"]').value; 
	let linePlaceInput  = table.querySelector('[id="PlaceInLine"]').value;
	let GroupInput  = table.querySelector('[id="Group"]').value;
	let tableTitleObject = table.querySelector('[id="TableTitle"]');
	let fSong =  true;
	let fGroup = true;
	let fWord =  true;
		
   
   data.append("Word", wordInput);
   data.append("FilterWord", fWord);
   data.append("Song", songInput);
   data.append("FilterSong", fSong);
   data.append("Verse", verseInput);
   data.append("Line", lineInput);
   data.append("LinePlace", linePlaceInput);
   data.append("Group", GroupInput);
   data.append("FilterGroup", fGroup);
   data.append("StartIndex", fromIndex);
   			   
   fetch("FilterWordSongs", {
     method: "POST",
     body: data
   })
   .then(res => res.text())
   .then(data => {
	let table = document.getElementById(tableId);
	
	// remove rows one by one (from bottom up)
	for (let i = table.rows.length - 1; i >= 3; i--) {  table.deleteRow(i);}			
	table.insertAdjacentHTML("beforeend", data);

	table.hidden = false;
					
	}
);
          
};



function getSongWordsTable(tableId,songId,songTitle,fromIndex)
{
	
	let data = new URLSearchParams();
	
	let  table = document.getElementById(tableId);


	
   data.append("SongId", songId);
   data.append("SongTitle", songTitle);
   data.append("StartIndex", fromIndex);
   
   
 
   			   
   fetch("GetSongWords", {
     method: "POST",
     body: data
   })
   .then(res => res.text())
   .then(data => 
	{
	let table = document.getElementById("SongWordsView");
	
	// remove rows one by one (from bottom up)
				
	table.innerHTML = data;

	table.hidden = false;
					
	}
);
          
};


function filterSongWordsTable(tableId,fromIndex)
{
	
	let data = new URLSearchParams();
	
	let  table = document.getElementById(tableId);

	let wordObject  = table.querySelector('[id="Word"]');
	let wordInput  = wordObject.value;
	let songObject   = table.querySelector('[id="Song"]');
	let songInput   = songObject.value;
	let verseInput = table.querySelector('[id="Verse"]').value;
	let lineInput  = table.querySelector('[id="Line"]').value; 
	let linePlaceInput  = table.querySelector('[id="PlaceInLine"]').value;
	let GroupInput  = table.querySelector('[id="Group"]').value;
	let tableTitleObject = table.querySelector('[id="TableTitle"]');
	let fSong =  true;
	let fGroup = true;
	let fWord =  true;
		
   
   data.append("Word", wordInput);
   data.append("FilterWord", fWord);
   data.append("Song", songInput);
   data.append("FilterSong", fSong);
   data.append("Verse", verseInput);
   data.append("Line", lineInput);
   data.append("LinePlace", linePlaceInput);
   data.append("Group", GroupInput);
   data.append("FilterGroup", fGroup);
   data.append("StartIndex", fromIndex);
   			   
   fetch("FilterSongWords", {
     method: "POST",
     body: data
   })
   .then(res => res.text())
   .then(data => {
	let table = document.getElementById(tableId);
	
	// remove rows one by one (from bottom up)
	for (let i = table.rows.length - 1; i >= 3; i--) {  table.deleteRow(i);}			
	table.insertAdjacentHTML("beforeend", data);

	table.hidden = false;
					
	}
);
          
};


	function filterWordsTable(tableId,fromIndex)
	{
		let data = new URLSearchParams();
		
	
		let  table = document.getElementById(tableId);
		
	   let word  =  table.querySelector('[id="Word"]');
	   let group  =  table.querySelector('[id="Group"]');
	   let numberOfInstances =  table.querySelector('[id="NumberOfInstances"]');
	   let numberOfChars    =  table.querySelector('[id="NumberOfChars"]'); 
	   data.append("Word", word.value);
	   data.append("Group", group.value);
	   data.append("NumberOfInstances", numberOfInstances.value);
	   data.append("NumberOfChars", numberOfChars.value);
	   data.append("StartIndex", fromIndex);
    		   
	   fetch("FilterWords", {
	     method: "POST",
	     body: data
	   })
	   .then(res => res.text())
	   .then(data => {
			let table = document.getElementById(tableId);
			
			// remove rows one by one (from bottom up)
			for (let i = table.rows.length - 1; i >= 3; i--) {table.deleteRow(i);}			
		    table.insertAdjacentHTML("beforeend", data);					
		}
		);      
	};
	
function getWordsTable(tableId,fromIndex)
{
	let data = new URLSearchParams();
	data.append("StartIndex", fromIndex);
	
			   
   fetch("GetWords", {
     method: "POST",
     body: data
   })
   .then(res => res.text())
   .then(data => 
	{
		let view = document.getElementById("WordsView");
		view.innerHTML = data;					
	}
	);      
};