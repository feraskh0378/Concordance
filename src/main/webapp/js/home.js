/**
 * 
 */

function openTab(evt, tabName)
      {
          var contents = document.getElementsByClassName("tabcontent");
          for (var i = 0; i < contents.length; i++) { contents[i].style.display = "none"; }

          var buttons = document.getElementsByClassName("tablinks");
          for (var i = 0; i < buttons.length; i++) { buttons[i].className = buttons[i].className.replace(" active", ""); }

          document.getElementById(tabName).style.display = "block";
          evt.currentTarget.className += " active";
		  
		  currentTab = tabName;
      };
      
      function getTable(tabName)
      {
      	   let formData = new FormData();
					   
		 
		   
      	   fetch(tabName, {
			     method: "POST",
			     body: formData
			   }) // call your servlet    
      	.then(res => res.text())
          .then(data => {
            let table = document.getElementById(tabName);
            table.innerHTML = data;
          });          
      };
      // Show first tab by default
      window.onload = function() 
      { 
      	getDocumentsTable('DocumentsTable');
		getDBWordsTable('WordsTable');      	
      	getGroupsTable(0);
      	
      	document.getElementsByClassName('tablinks')[0].click(); 
     };