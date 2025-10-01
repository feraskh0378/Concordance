
function getGroupsTable(runtime)
{
	
	let data = new URLSearchParams();
	
	    if(runtime)
		{
			   let group  =  document.getElementById("GroupGroup");
			   let numberOfWords =  document.getElementById("GroupNumberOfWords");
			   data.append("Group", group.value);
			   data.append("NumberOfWords", numberOfWords.value);			   
		   }
		   else
		   {
				data.append("Group", "");
		   		data.append("NumberOfWords", "");		   
		   }
			   
			   fetch("GroupsTable", {
			     method: "POST",
			     body: data
			   })
			   .then(res => res.text())
			   .then(data => {
				let table = document.getElementById("GroupsTable");
				
				// remove rows one by one (from bottom up)
				for (let i = table.rows.length - 1; i >= 3; i--) {
				  table.deleteRow(i);
				}			
				table.insertAdjacentHTML("beforeend", data);

								
				}
			);
          
};


function ShowGroupWords(group)
{
	let data = new URLSearchParams();
		
	
			data.append("Word", "");
	        data.append("Group", group);
		    data.append("NumberOfInstances", "");
			data.append("NumberOfChars", "");
	
				   
	   fetch("DBWordsTable", {
	     method: "POST",
	     body: data
	   })
	   .then(res => res.text())
	   .then(data => {
			let table = document.getElementById("GroupWordsTable");
			
			// remove rows one by one (from bottom up)
			for (let i = table.rows.length - 1; i >= 3; i--) {table.deleteRow(i);}			
		    table.insertAdjacentHTML("beforeend", data);					
		}
		);      
}