<html>
<head>
<title>Assignment3</title>


<style>
body {
	background: #EAEAEA;
}

.upload {
	border: 2px solid;
	width: 50%;
	margin-left: 25%;
	margin-top: 4px;
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

.homebutton 
{
margin-top:5px;
}
</style>
</head>
<body>

	<h2 align="center">CS-553 - Assignment 3 - Google App Engine</h2>

	<h3 align="center">Extra Operation</h3>
	<div class="extraoperation">


		
		<div class="upload">

			<p class="title">Remove all files from Cache</p>

			<form action="/removeallcache" method="post">
				<p>Click here to list remove all the files from cache :</p>

				<input type="submit" value="Remove All from Cache">
			</form>

		</div>

		<div class="upload">

			<p class="title">Remove all files from Distributed Storage and
				Cache</p>

			<form action="/removeall" method="post">
				<p>Click here to list remove all the files from cache and
					Distributed Storage :</p>

				<input  type="submit"
					value="Remove All from Cache and Distributed Storage">
			</form>

		</div>

		<div class="upload">

			<p class="title">Stats about cache</p>

			<form action="/stats" method="post">
				<p>Click here to see the statistics for cache :</p>

				<input type="submit"
					value="See the statistics for cache">
			</form>

		</div>

		<div class="upload">

			<p class="title">Size of Distributed Storage System</p>

			<form action="/sizestorage" method="post">
				<p>Click here to see the size of Distributed Storage SYstem :</p>

				<input type="submit"
					value="See the  size of Distributed Storage SYstem">
			</form>

		</div>


		<div class="upload">

			<p class="title">Searches for a regular expression in file key</p>

			<form action="/searchregexp" method="post" id="searchform">
				<p>Enter the file name :</p>

				<input type="text" name="filename">

				<p>Enter the regular expression :</p>

				<input type="text" name="regexp"> <input id="dsubmit"
					type="submit" value="Search Regular Expression">
			</form>

		</div>



		<div class="upload">

			<p class="title">Searches for a regular expression in file name</p>

			<form action="/filelistregexp" method="post" id="searchform">

				<p>Enter the regular expression :</p>

				<input type="text" name="regexp"> <input type="submit"
					value="Search Regular Expression">
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