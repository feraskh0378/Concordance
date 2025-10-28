

function CheckSelectedGroup(view)
{
	let newSelectedGroup  = view.querySelector('[id="'+SelectedGroup.id+'"]');
	if(newSelectedGroup != null)
	{
		newSelectedGroup.classList.toggle("highlight");
		SelectedGroup = newSelectedGroup;
	}
}


function CheckSelectedGroupWord(view)
{
	let newSelectedGroupWord  = view.querySelector('[id="'+SelectedGroupWord.id+'"]');
	if(newSelectedGroupWord != null)
	{
		newSelectedGroupWord.classList.toggle("highlight");
		SelectedGroupWord = newSelectedGroupWord;
	}
}

function ToggleSelectedGroupWord(tr)
{
	if(tr != null)
	{		
		if(SelectedGroupWord != null)
		{
		 	SelectedGroupWord.classList.toggle("highlight");
		}
		SelectedGroupWord = tr;
		SelectedGroupWord.classList.toggle("highlight");
	}
}

function ToggleSelectedGroup(tr)
{
	if(SelectedGroup != null)
	{
	 	SelectedGroup.classList.toggle("highlight");
	}
    SelectedGroup = tr;
	SelectedGroup.classList.toggle("highlight");
}


function getGroupWords(groupId,groupName,fromIndex)
{
	
	let data = new URLSearchParams();
	
   data.append("GroupId", groupId);
   data.append("GroupName", groupName);
   data.append("StartIndex", fromIndex);
    
   			   
   fetch("GetGroupWords", {
     method: "POST",
     body: data
   })
   .then(res => res.text())
   .then(data => 
	{
	let view = document.getElementById("GroupWordsView");
	view.innerHTML = data;
	view.hidden = false;
	CheckSelectedGroupWord(view);
	}
);
          
};


function FilterGroups(fromIndex)
{	
	let data = new URLSearchParams();
	
	
		let  table = document.getElementById('GroupsTable');
			
		   
	   let groupName  = table.querySelector('[id="GroupName"]');
	   let numberOfWords =  table.querySelector('[id="NumberOfWords"]');
	   
	  			  
	 
	   data.append("GroupName", groupName.value);
	   data.append("NumberOfWords", numberOfWords.value);
	   data.append("StartIndex", fromIndex);
 
	   	   
   fetch("FilterGroups", {
     method: "POST",
     body: data
   })
   .then(res => res.text())
   .then(data => {
		let table = document.getElementById('GroupsTable');
		
		// remove rows one by one (from bottom up)
		for (let i = table.rows.length - 1; i >= 3; i--) { table.deleteRow(i);}
		table.insertAdjacentHTML("beforeend", data);
		CheckSelectedGroup(table);
									
		}
	);
          
};

function getGroups(fromIndex)
{	
	let data = new URLSearchParams();
	
   data.append("StartIndex", fromIndex);	   
   fetch("GetGroups", {
     method: "POST",
     body: data
   })
   .then(res => res.text())
   .then(data => {
		let view = document.getElementById("GroupsView");

		view.innerHTML = data;		
		
		CheckSelectedGroup(view);
		
		
		
		}
		
	);
          
};


function filterGroupWords(fromIndex)
{	
	let data = new URLSearchParams();
	
	let  table = document.getElementById('GroupWordsTable');

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
   			   
   fetch("FilterGroupWords", {
     method: "POST",
     body: data
   })
   .then(res => res.text())
   .then(data => {
	let table = document.getElementById('GroupWordsTable');
	
	// remove rows one by one (from bottom up)
	for (let i = table.rows.length - 1; i >= 3; i--) {  table.deleteRow(i);}			
	table.insertAdjacentHTML("beforeend", data);
	table.hidden = false;
	CheckSelectedGroupWord(table);
					
	}
);
          
};