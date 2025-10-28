
function UpdateAddWordToGroupBox(wordValue)
{
	let word = document.getElementById("AddWordToGroupInput");
	word.value = wordValue;
	
}

function CheckSelectedWord(view)
{
	let newSelectedWord  = view.querySelector('[id="'+SelectedWord.id+'"]');
	if(newSelectedWord != null)
	{
		newSelectedWord.classList.toggle("highlight");
		SelectedWord = newSelectedWord;
	}
}


function CheckSelectedWordSong(view)
{
	let newSelectedWordSong  = view.querySelector('[id="'+SelectedWordSong.id+'"]');
	if(newSelectedWordSong != null)
	{
		newSelectedWordSong.classList.toggle("highlight");
		SelectedWordSong = newSelectedWordSong;
	}
}



function ToggleSelectedWord(tr)
{
	if(SelectedWord != null)
	{
	 	SelectedWord.classList.toggle("highlight");
	}
    SelectedWord = tr;
	SelectedWord.classList.toggle("highlight");
}

function getWordSongs(word,fromIndex)
{
		let data = new URLSearchParams();
				
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
		CheckSelectedWordSong(table);
						
		}
	);
	
};


function filterWordSongs(fromIndex)
{
	
	let data = new URLSearchParams();
	
	let  table = document.getElementById('WordSongsTable');

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
	let table = document.getElementById('WordSongsTable');
	
	// remove rows one by one (from bottom up)
	for (let i = table.rows.length - 1; i >= 3; i--) {  table.deleteRow(i);}			
	table.insertAdjacentHTML("beforeend", data);
	CheckSelectedWordSong(table);
	table.hidden = false;
					
	}
);
          
};



	function filterWords(fromIndex)
	{
		let data = new URLSearchParams();
		
	
		let  table = document.getElementById('WordsTable');
		
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
			let table = document.getElementById('WordsTable');
			
			// remove rows one by one (from bottom up)
			for (let i = table.rows.length - 1; i >= 3; i--) {table.deleteRow(i);}			
		    table.insertAdjacentHTML("beforeend", data);	
			
			CheckSelectedWord(table)				
		}
		);      
	};
	
function getWords(fromIndex)
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