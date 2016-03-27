<html>
<head>
<title>Assignment3</title>

<script type="text/javascript">
	function uploadFile() {
		if (window.File && window.FileList) {
			var fd = new FormData();
			var uploadedf = document.getElementById('Upload').files;
			document.getElementById('number_files').value = uploadedf.length;

			this.getTime();

			document.getElementById('formUpload').submit();
			//document.getElementById('memcache').submit();
		} else {
			this.getTime();
			document.getElementById('formUpload').submit();
			//document.getElementById('memcache').submit();
		}
	}
	function getTime() {
		var timecurrent = new Date().getTime();
	
		document.getElementById('timefield').value = timecurrent;
	}

	function DeleteFile() {
		var timecurrent = new Date().getTime();
		document.getElementById('deletefield').value = timecurrent;

		document.getElementById('deleteform').submit();
	}

	function SearchFile() {
		var timecurrent = new Date().getTime();
		document.getElementById('searchfield').value = timecurrent;

		document.getElementById('searchform').submit();
	}
</script>


<style>
.homebutton 
{
margin-top:5px;
}

body {
	background: #EAEAEA;
}

.upload {
	border: 2px solid;
	width: 50%;
	margin-left: 25%;
	margin-top: 10px;
}

.upload form {
	padding-left: 10px;
}

.title {
	border-bottom: 2px solid;
	padding-top: 0px;
	margin-top: 0;
	padding-top: 5px;
	background: #223d62;
	color: white;
	font-weight: bold;
	padding-bottom: 5px;
}
</style>
</head>
<body>

	<h2 align="center">CS-553 - Assignment 3 - Google App Engine</h2>

	<h3 align="center">Operations on Google App Engine with Mem Cache</h3>

	<%@ page
		import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
	<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>

	<%
		BlobstoreService blobobj = BlobstoreServiceFactory
				.getBlobstoreService();
	%>
	<div class="Withmemcache">

		<div class="upload">
			<p class="title">Upload File on Google App Engine</p>
			
			
			<form id="formUpload" enctype="multipart/form-data" method="post"
				action="<%=blobobj.createUploadUrl("/upload")%>">
				<p>Browse the files to be be Uploaded</p>

				<input name="number_files" type="text" id="number_files" value="1" hidden="hidden" />

				<input type="text" name="getcurtime" id="timefield" value="1"
					hidden="hidden" />

				<p>
					<input type="file" name="Upload" id="Upload" multiple /> <input
						type="button" onclick="uploadFile();" value="Upload Files" />
				</p>

				

			</form>
		</div>


		<div class="upload">

			<p class="title">List all the files from Google App Engine</p>

			<form action="/showall" method="post">
				<p>Click here to list all the files :</p>

				<input id="submit" type="submit" value="Show All">
			</form>

		</div>


		<div class="upload">

			<p class="title">Search file from Google App Engine</p>

			<form action="/searchfile" method="post" id="searchform">
				<p>Enter the filename to be searched :</p>
				<input type="text" name="filename">  
				<input name="searchtime" type="text" id="searchfield" hidden="hidden" />
				<input id="dsubmit" type="button" value="Search File" onclick="SearchFile()">
			</form>

		</div>

		<div class="upload">

			<p class="title">Read content of file from Google App Engine</p>

			<form action="/displaycontents" method="post">
				<p>Enter the filename to display its contents :</p>
				<input type="text" name="Value"> <input id="submit"
					type="submit" value="Display File Contents">
			</form>

		</div>

		<div class="upload">

			<p class="title">Delete file from Google App Engine</p>

			<form action="/delete" method="post" id="deleteform">
				<p>Enter the filename to be removed :</p>

				<input name="deletetime" type="text" id="deletefield" value="1"
					hidden="hidden" /> <input type="text" name="Delete"> <input
					id="button" type="submit" value="Delete File"
					onclick="DeleteFile()">
			</form>
		</div>

		<div align="center" class="homebutton">

			<form action="home.jsp">
				<input type="submit" value="Back To Home">
			</form>
		</div>

	</div>




</body>
</html>