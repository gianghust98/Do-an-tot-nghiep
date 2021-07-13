function markColor(id){
	//tách lấy id của câu hỏi
	var fields = id.split('.');	
	var answerId = fields[1];
	 document.getElementById("answer"+answerId).style.backgroundColor = "rgb(167,162,162)";

	}

function correctAnswer(){
	var correctArr =[];	
	for (var i = 1; i < 51; i++) {
		 var nameRadio = "correctanswer"+i;	 	
		 var x = document.getElementById("submitForm").elements.namedItem(nameRadio).value;
		
		 correctArr.push(x);}
			
	return correctArr;
}

function answerUser(){
	
	//var form = document.getElementById("submitForm");
	// array index start = 0
	var answerArr = [];
	
	for (var i = 1; i < 51; i++) {
		 var nameRadio = "question"+i;
		 var result = document.getElementById("submitForm").elements.namedItem(nameRadio);
		
		 if(result == null) answerArr.push("");
		 else{
			 	
		 var x = document.getElementById("submitForm").elements.namedItem(nameRadio).value;
		 answerArr.push(x)};
			
	}

	return answerArr;
}
// let countMouseLeave = 0;
// testReading.addEventListener('mouseleave', e => {
// 	alert("mouse leave!");
// 	countMouseLeave +=1;
// 	console.log("countMouseleave: ", countMouseLeave);
// });



let countMouseLeave = 0;

$(document).ready(function(){

	
    $('#btnSubmit').click(function () {
        var examId = $("#id_bai_exam").val();
        console.log("id exam reading: ", examId);
        document.getElementById("url-id-exam-reading").href = "http://localhost:8081/webtoeic/doExam/reading?idExam=" + examId;
	
		var answerArr = answerUser();
		var correctArr = correctAnswer();
		var countCorrect = 0;
		
		
			
			for(var i = 0; i<50; i++){
			 if(answerArr[i] == correctArr[i] && answerArr[i] != ' ' ) countCorrect++;
				
			}
			
			
		var jsonAnswerUser = JSON.stringify(answerArr);
		console.log('count correct: ',countCorrect);	
		
		var url="http://localhost:8081/webtoeic/reading/"+examId+"/"+countCorrect;
		
		if(window.XMLHttpRequest){
			xhttp = new XMLHttpRequest();
		}
		else{
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
	  	 xhttp.open("POST",url,true);
		
	       xhttp.onreadystatechange = function(){
			if(xhttp.readyState == 4){
				
			//var data = xhttp.responseText;
			//document.getElementById("testReading").innerHTML = data;
			console.log('abcdefggggg');
				
			}
		}
		
		
		xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

		xhttp.send(jsonAnswerUser);
		location.reload();

		// $.ajax({
		// 	url: "http://localhost:8081/webtoeic/reading/" +examId+ "/" +countCorrect,
		//    type: 'POST',
		//    processData: false,
		//    contentType: false,
		//    dataType: 'json',
		// });
		
		
	});
	

	
	$('#btnResult').click(function(){
		
		//clear clock,stop countdown
	    clearInterval(timecheck);
		//tranfer information
	    
	    //remove btn XemdapAn, show btn lamlai
	    $('#btnResult').hide();
	    //$('#btndoAgain').show();
	    
		document.getElementById("btnSubmit").style.margin = "0px 0px 0px 80px";

	    
	    var answerArr = answerUser();

		var correctArr = correctAnswer();
		var countCorrect = 0;
		
		for(var i = 0; i<50; i++){
		 if(answerArr[i] == correctArr[i] && answerArr[i] != ' ' ) countCorrect++;
			
		}
		
			
		var jsonAnswerUser = JSON.stringify(answerArr);
		
		//console.log("answerARR="+answerArr);
		//console.log("correctArr="+correctArr);
		//console.log("correctCount="+countCorrect);
		console.log('mouse leave: ',countMouseLeave);

		
		
		var examId = $("#id_bai_exam").val();
		
		var url="http://localhost:8081/webtoeic/showResultListening/"+examId+"/"+countCorrect;
		if(window.XMLHttpRequest){
			xhttp = new XMLHttpRequest();
		}
		else{
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		xhttp.open("POST",url,true);		
			xhttp.onreadystatechange = function(){
			if(xhttp.readyState == 4){				
				var data = xhttp.responseText;
				document.getElementById("main").innerHTML = data;
			}
		}
		
		
		xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

		xhttp.send(jsonAnswerUser);
		
	});

	
	
	

	var timecheck;
	function startTimer(duration, display) {
		var timer = duration, minutes, seconds;
		
		timecheck= setInterval(function () {
				minutes = parseInt(timer / 60, 10)
				seconds = parseInt(timer % 60, 10);
		
				minutes = minutes < 10 ? "0" + minutes : minutes;
				seconds = seconds < 10 ? "0" + seconds : seconds;
		
				display.textContent = minutes + ":" + seconds;
		
				if (--timer < 0) {
				clearInterval(timecheck);
				//do someth after countdown
				var examId = $("#id_bai_exam").val();
				alert("Đã hết thời gian làm bài test Listening. Hệ thống sẽ tự động chuyển qua bài test Reading");
				$("#btnSubmit").click()
				//window.location.href = "http://localhost:8081/webtoeic/reading?idExam="+examId;

				}
			},1000);
			
		
	
	}
	function dataURItoBlob(dataURI) {
		// convert base64/URLEncoded data component to raw binary data held in a string
		var byteString;
		if (dataURI.split(',')[0].indexOf('base64') >= 0)
			byteString = atob(dataURI.split(',')[1]);
		else
			byteString = unescape(dataURI.split(',')[1]);

		// separate out the mime component
		var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];

		// write the bytes of the string to a typed array
		var ia = new Uint8Array(byteString.length);
		for (var i = 0; i < byteString.length; i++) {
			ia[i] = byteString.charCodeAt(i);
		}

		return new Blob([ia], { type: mimeString });
	}

	function resetWebcam() {
		Webcam.reset();
	}


	function regconizedUserTestListening() {
		Webcam.set({
			width: 220,
			height: 220,
			image_format: 'jpeg',
			jpeg_quality: 100
		});
		Webcam.attach('#camera2');

		take_snapshot = function () {
			Webcam.snap(function (data_uri) {
				var blob = dataURItoBlob(data_uri);
				console.log('blob', blob);
				var fd = new FormData(document.forms[0]);
				fd.append("canvasImage", blob);
				var examId = $("#id_bai_exam").val();
				

				$.ajax({
					url: 'http://localhost:8081/webtoeic/takePicture/duringTest?idExam=' + examId,
					type: 'POST',
					processData: false,
					contentType: false,
					dataType: 'json',
					data: fd
				});
			});

		};

		var step = 3000;
		for(i = 1;i<10;i++){
			setTimeout(take_snapshot, step);
			step = step + 3000;
		}
	
	}
	


	
	window.onload = function () {
			//change time here
			//var thirtyMinutes = 0.2 * 30;
			var thirtyMinutes = 60 * 30;
			display = document.querySelector('#time');
			startTimer(thirtyMinutes, display);
			regconizedUserTestListening();
			$( "#testReading" ).mouseleave(function() {
				alert("Dont leave the scope of exam!");
				countMouseLeave +=1;
			});
			
			
		};




});








