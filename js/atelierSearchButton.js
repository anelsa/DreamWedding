function searchButton(){
	
	
				var input=document.getElementById("atelierSearchBar").value;
				document.getElementById("1").innerHTML="";
				document.getElementById("notFound").style.display="none";
							if(input=="")
											{
												document.getElementById("notFound").style.display="inline";
											}
				xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
						
						var jsonAteliers= JSON.parse(this.responseText);
								
																	html= '<div id="'+
																	jsonAteliers[0].divID+
																	'" class="atelierDivs"' +
																	'style=\x27background-image: url("' +
																	jsonAteliers[0].imageName +
																	'");\x27>' +
																	
																	
																	'<a id="'+ 
																	jsonAteliers[0].townID +
																	'" class="atelierTown"></a>'+
																	'<br><br>' +
																	
														
																	'<a id="' + 
																	jsonAteliers[0].addressID + 
																	'" class="atelierAddress"></a>' +
																	'<a class="atelierAnchors" id="' +
																	jsonAteliers[0].id + '"'+
																	' href="#" onclick=createPage("' + 
																	jsonAteliers[0].id +
																	'");></a></div>';
															document.getElementById("1").innerHTML=html;
															document.getElementById(jsonAteliers[0].id).innerHTML =jsonAteliers[0].name;
															document.getElementById(jsonAteliers[0].townID).innerHTML =jsonAteliers[0].town;
															document.getElementById(jsonAteliers[0].addressID).innerHTML =jsonAteliers[0].address;
											
								
					}
				};
				xhttp.open("GET", "searchAtelierByName.php?atelName=" + input, true);
				xhttp.send();
}
