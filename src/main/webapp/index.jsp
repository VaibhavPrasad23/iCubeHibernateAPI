<html>
<head>
<link rel="stylesheet" href="styles.scss">
</head>
<body>





<div class="body1" style = "background-color: #333333">
	<div class="playground">
		<div class="washing-machine">
			<div class="board">
				<input type="text" id="name" class="naam" placeholder="Your Name" > <input
					type="submit" id="submit" class="subm">
				<script>

						var btn = document.getElementById('submit');
						btn.addEventListener('click', func);

						function func() {
							console.log(document.getElementById("name").value)
							var name = document.getElementById("name").value
							const full = location.protocol + '//' + location.host;
							window.location.href =  full + "/api/web/messages/id/"	+ name;
						}
					</script>
			</div>
			<div class="window">
				<div class="content">
					<div class="sock orange"></div>
					<div class="sock green"></div>
					<div class="sock red"></div>
					<div class="cat">
						<div class="head">
							<div class="ear"></div>
							<div class="ear right"></div>
							<div class="eye"></div>
							<div class="eye"></div>
						</div>
						<div class="body"></div>
						<div class="feet"></div>
						<div class="tail"></div>
					</div>
				</div>
				<div class="bubbles">
					<div class="bubble"></div>
					<div class="bubble"></div>
					<div class="bubble"></div>
					<div class="bubble"></div>
					<div class="bubble"></div>
					<div class="bubble"></div>
					<div class="bubble"></div>
					<div class="bubble"></div>
					<div class="bubble"></div>
					<div class="bubble"></div>
					<div class="bubble"></div>
					<div class="bubble"></div>
					<div class="bubble"></div>
					<div class="bubble"></div>
					<div class="bubble"></div>
				</div>
			</div>
			<div class="badge">
				<div class="line" style="visibility: hidden"></div>
				<a href="web/myresource"
					style="font-size: 15px; margin-left: 5px; color: black">Hello World</a>

				<div class="line" style="visibility: hidden"></div>
				<div class="line" style="visibility: hidden"></div>
			</div>
		</div>
		<div class="clothes">
			<div class="shirt white"></div>
			<div class="shirt orange"></div>
			<div class="shirt"></div>
		</div>
		<div class="shadow"></div>
	</div>


</div>











</body>



</html>
