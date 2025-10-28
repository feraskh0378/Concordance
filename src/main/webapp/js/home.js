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
      	getSongs(0);
		getWords(0);      	
      	getGroups(0);
		
		const datalist = document.getElementById("groups");

		fetch("GetGroupsOptions") // URL to your servlet
		  .then(res => res.json())
		  .then(groups => {
		    groups.forEach(g => {
		      const option = document.createElement("option");
		      option.value = g.name;
		      datalist.appendChild(option);
		    });
		  })
		  .catch(err => console.error("Error loading groups:", err)); 	
		  
		  document.getElementById("newGroup").addEventListener("submit", async function(event) {
		  	  		 	  event.preventDefault(); // 🛑 stop the page reload

		  	  		 	  const form = event.target;

		  	  		 	  // Prepare form data for sending
		  	  		 	  const formData = new FormData(form);

		  	  		 	  try {
		  	  		 	    // Send data to the server
		  	  		 	    const response = await fetch(form.action, {
		  	  		 	      method: form.method,
		  	  		 	      body: formData
		  	  		 	    });

		  	  		 	    // Handle the response
		  	  		 	    const result = await response.text();
		  	  		 	    alert(result );
		  	  				// Clear the form
		  	  				form.reset();
		  	  				// Optionally close the modal
		  	  				   const modal = bootstrap.Modal.getInstance(document.getElementById('newGroup'));
		  	  				   modal.hide();
							   location.reload(true);
							   document.getElementsByClassName('tablinks')[2].click(); 


		  	  		 	  } catch (error) {
		  	  		 	    console.error("Submission failed", error);
		  	  		 	    document.getElementById("result").innerText = "❌ Submission failed!";
		  	  		 	  }
		  	  		 	});
		  document.getElementById("addWordToGroupForm").addEventListener("submit", async function(event) {
		  		 	  event.preventDefault(); // 🛑 stop the page reload

		  		 	  const form = event.target;

		  		 	  // Prepare form data for sending
		  		 	  const formData = new FormData(form);

		  		 	  try {
		  		 	    // Send data to the server
		  		 	    const response = await fetch(form.action, {
		  		 	      method: form.method,
		  		 	      body: formData
		  		 	    });

		  		 	    // Handle the response
		  		 	    const result = await response.text();
		  		 	    alert(result );
		  				// Clear the form
		  				form.reset();
		  				// Optionally close the modal
		  				   const modal = bootstrap.Modal.getInstance(document.getElementById('addWordToGroup'));
		  				   modal.hide();
		  				  


		  		 	  } catch (error) {
		  		 	    console.error("Submission failed", error);
		  		 	    document.getElementById("result").innerText = "❌ Submission failed!";
		  		 	  }
		  		 	});
		  
		  
		document.getElementById("uploadForm").addEventListener("submit", async function(event) {
		 	  event.preventDefault(); // 🛑 stop the page reload

		 	  const form = event.target;

		 	  // Prepare form data for sending
		 	  const formData = new FormData(form);

		 	  try {
		 	    // Send data to the server
		 	    const response = await fetch(form.action, {
		 	      method: form.method,
		 	      body: formData
		 	    });

		 	    // Handle the response
		 	    const result = await response.text();
		 	    alert("Loaded" + result );
				// Clear the form
				form.reset();
				// Optionally close the modal
				   const modal = bootstrap.Modal.getInstance(document.getElementById('uploadModal'));
				   modal.hide();
				   location.reload(true);


		 	  } catch (error) {
		 	    console.error("Submission failed", error);
		 	    document.getElementById("result").innerText = "❌ Submission failed!";
		 	  }
		 	});
      	document.getElementsByClassName('tablinks')[0].click(); 
		
     };