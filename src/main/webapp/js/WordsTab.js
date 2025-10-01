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


function getWordsTable(tableId,word,doc,verse,line,frequency,group)
{
	
	let data = new URLSearchParams();
	
	let  table = document.getElementById(tableId);

	let wordInput  = table.querySelector('[id="Word"]').value;
	let docInput   = table.querySelector('[id="Document"]').value;
	let verseInput = table.querySelector('[id="Verse"]').value;
	let lineInput  = table.querySelector('[id="Line"]').value; 
	let FrequencyInput  = table.querySelector('[id="Frequency"]').value;
	let GroupInput  = table.querySelector('[id="Group"]').value;
	let tableTitleObject = table.querySelector('[id="TableTitle"]');
	
	
	if( word != null)
	{
		table.querySelector('[id="Word"]').value = wordInput  = word;
		tableTitleObject.innerHTML = word;
	}
	
	if( doc != null)
	{
		table.querySelector('[id="Document"]').value = docInput  = doc;
		tableTitleObject.innerHTML = doc;
	}
	
	if( verse != null)
	{
		table.querySelector('[id="Verse"]').value = verseInput  = verse;
	}
	
	if( line != null)
	{
		table.querySelector('[id="Line"]').value = lineInput  = line;
	}
	
	if( frequency != null)
	{
		table.querySelector('[id="Frequency"]').value = FrequencyInput  = frequency;
	}
	
	if( group != null)
	{
		table.querySelector('[id="Group"]').value = GroupInput  = group;
		tableTitleObject.innerHTML = group;
	}
   
   data.append("Word", wordInput);
   data.append("Document", docInput);
   data.append("Verse", verseInput);
   data.append("Line", lineInput);
   data.append("Frequency", FrequencyInput);
   data.append("Group", GroupInput);
   			   
   fetch("WordsTable", {
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


function getDBWordsTable(tableId)
{
	let data = new URLSearchParams();
	
	if(tableId)
	{
		let  table = document.getElementById(tableId);
		
	   let word  =  table.querySelector('[id="DBWord"]');
	   let group  =  table.querySelector('[id="Group"]');
	   let numberOfInstances =  table.querySelector('[id="NumberOfInstances"]');
	   let numberOfChars    =  table.querySelector('[id="NumberOfChars"]'); 
	   data.append("Word", word.value);
	   data.append("Group", group.value);
	   data.append("NumberOfInstances", numberOfInstances.value);
	   data.append("NumberOfChars", numberOfChars.value);
    }
    else
    {
		data.append("Word", "");
        data.append("Group", "");
	    data.append("NumberOfInstances", "");
		data.append("NumberOfChars", "");
	}
			   
   fetch("DBWordsTable", {
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