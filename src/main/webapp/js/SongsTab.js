

function CheckSelectedSong(view)
{
	let newSelectedSong  = view.querySelector('[id="'+SelectedSong.id+'"]');
	if(newSelectedSong != null)
	{
		newSelectedSong.classList.toggle("highlight");
		SelectedSong = newSelectedSong;
	}
}


function CheckSelectedSongWord(view)
{
	let newSelectedSongWord  = view.querySelector('[id="'+SelectedSongWord.id+'"]');
	if(newSelectedSongWord != null)
	{
		newSelectedSongWord.classList.toggle("highlight");
		SelectedSongWord = newSelectedSongWord;
	}
}

function ToggleSelectedSongWord(tr)
{
	if(tr != null)
	{		
		if(SelectedSongWord != null)
		{
		 	SelectedSongWord.classList.toggle("highlight");
		}
		SelectedSongWord = tr;
		SelectedSongWord.classList.toggle("highlight");
	}
}

function ToggleSelectedSong(tr)
{
	if(SelectedSong != null)
	{
	 	SelectedSong.classList.toggle("highlight");
	}
    SelectedSong = tr;
	SelectedSong.classList.toggle("highlight");
}


function ToggleSelectedWordSong(tr)
{
	if(tr != null)
	{		
		if(SelectedWordSong != null)
		{
		 	SelectedWordSong.classList.toggle("highlight");
		}
		SelectedWordSong = tr;
		SelectedWordSong.classList.toggle("highlight");
	}
}

function HideWordText(ViewId)
{
	let view = document.getElementById(ViewId);			
	view.hidden = true;		          
};


function ShowWordText(wordID,Verse,ViewId)
{
	let data = new URLSearchParams();
	
	
		
	   data.append("WordId", wordID);
	   data.append("Verse", Verse);
	   data.append("ViewId", ViewId);
	   				   
	   fetch("WordsText", {
	     method: "POST",
	     body: data
	   })
	   .then(res => res.text())
	   .then(data => {
		let view = document.getElementById(ViewId);	
		view.innerHTML = data;
		view.hidden = false;
	
						
		}
	);	          	
}

function getSongWords(songId,songTitle,fromIndex)
{
	
	let data = new URLSearchParams();
	
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
	let view = document.getElementById("SongWordsView");
	view.innerHTML = data;
	view.hidden = false;
	CheckSelectedSongWord(view);
	}
);
          
};


function FilterSongs(fromIndex)
{	
	let data = new URLSearchParams();
	
	
		let  table = document.getElementById('SongsTable');
			
		   
	   let title  = table.querySelector('[id="Title"]');
	   let poet =  table.querySelector('[id="Poet"]');
	   let composer =  table.querySelector('[id="Composer"]');
	   let numberOfVerses    =  table.querySelector('[id="NumberOfVerses"]');
	   let numberOfLines    =  table.querySelector('[id="NumberOfLines"]');
	   let numberOfWords    =  table.querySelector('[id="NumberOfWords"]'); 
	   let date    =  table.querySelector('[id="Date"]');
	   
	  			  
	 
	   data.append("Title", title.value);
	   data.append("Poet", poet.value);
	   data.append("Composer", composer.value);
	   data.append("NumberOfVerses", numberOfVerses.value);
	   data.append("NumberOfLines", numberOfLines.value);
	   data.append("NumberOfWords", numberOfWords.value);
	   data.append("Date", date.value);
	   data.append("StartIndex", fromIndex);
 
	   	   
   fetch("FilterSongs", {
     method: "POST",
     body: data
   })
   .then(res => res.text())
   .then(data => {
		let table = document.getElementById('SongsTable');
		
		// remove rows one by one (from bottom up)
		for (let i = table.rows.length - 1; i >= 3; i--) { table.deleteRow(i);}
		table.insertAdjacentHTML("beforeend", data);
		CheckSelectedSong(table);
									
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
		
		CheckSelectedSong(view);
		
		
		
		}
		
	);
          
};


function filterSongWords(fromIndex)
{	
	let data = new URLSearchParams();
	
	let  table = document.getElementById('SongsWordsTable');

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
	let table = document.getElementById('SongsWordsTable');
	
	// remove rows one by one (from bottom up)
	for (let i = table.rows.length - 1; i >= 3; i--) {  table.deleteRow(i);}			
	table.insertAdjacentHTML("beforeend", data);
	table.hidden = false;
	CheckSelectedSongWord(table);
					
	}
);
          
};