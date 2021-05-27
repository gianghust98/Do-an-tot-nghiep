<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Danh sách bài thi thử</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/paging.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/html5shiv.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-1.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/webcamjs/1.0.25/webcam.js"></script>
<style type="text/css">
.hidden {
	display: none;
}

.error-message {
	color: red;
}

.anchor {
	display: block;
	height: 115px; /*same height as header*/
	margin-top: -115px; /*same height as header*/
	visibility: hidden;
}

.imageExam {
	float: left;
	height: 150px;
	width: 250px;
	margin-bottom: 25px;
}
</style>

<script type="text/javascript">
	$(document)
			.ready(
					function() {

						var buttons = document
								.getElementsByClassName('openModalExam');

						for (var i = 0; i < buttons.length; i++) {
							buttons[i].addEventListener("click", clickHandler);

						}

						function clickHandler(event) {
							var buttonId = event.target.id;
							var idExam = document.getElementById(buttonId).value;

							$('#examModal').modal('show');
							$('#examModal #idExamModal').val(idExam);

						}

						$('#btnLamBaiThi')
								.click(
										function() {
											var baseUrl = $('#baseUrl').val();
											var examId = $('#idExamModal')
													.val();
											window.location.href = baseUrl+"/doExam?idExam="
													+ examId;

										});
						$('#btPic')
							.click(
								function(){
									// CAMERA SETTINGS.
									    Webcam.set({
									        width: 280,
									        height: 280,
									        image_format: 'jpeg',
									        jpeg_quality: 100
									    });
									    Webcam.attach('#camera');
							 
								    takeSnapShot = function () {
								        Webcam.snap(function (data_uri) {
								        
											var blob = dataURItoBlob(data_uri);
											console.log('blob',blob);
											var fd = new FormData(document.forms[0]);
											fd.append("canvasImage", blob);
											console.log('file',fd);
											
											$.ajax({
												  url: 'http://localhost:8081/webtoeic/takePicture/beforeTest',
												  type: 'POST',
												  processData: false, 
												  contentType: false, 
												  dataType : 'json',
												  data: fd
												});
																			        
									        });
								    }
								    //CONVERT dataURI
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
									
									    return new Blob([ia], {type:mimeString});
									}
								     
					
							});

					});
</script>
</head>
<body>

	<jsp:include page="template/header.jsp"></jsp:include>
	<input id="baseUrl" value="${pageContext.request.contextPath}" style="display:none;"/>
	<div class="container">
		<!--PAGE TITLE-->
		<div class="span9" style="text-align: center">
			<div class="page-header">
				<h4 style="font-weight: bold;">DANH SÁCH BÀI THI THỬ</h4>
			</div>
		</div>

		<!-- /. PAGE TITLE-->
		<div class="row">

			<div class="span9">
				<c:if test="${fn:length(listData) == 0 }">
					<h3>Không tìm thấy dữ liệu</h3>
				</c:if>

				<c:forEach items="${listData}" var="list" varStatus="loop">

					<div class="span9">
						<div class="span3">
							<img class="imageExam"
								src="${pageContext.request.contextPath}/resources/file/images/exam/${list.anhbaithithu}" />
						</div>
						<div class="span1"></div>
						<div class="span5">
							<h4 class="content-heading" id="namebaithithu">
								${list.tenbaithithu}</h4>
							<button class="btn btn-primary openModalExam"
								value="${list.baithithuid}" id="openModalExam.${loop.index}">
								Chi tiết</button>
						</div>


					</div>

				</c:forEach>

				<br>
			</div>

			<div class="span3">
				<div class="side-bar">

					<h3>DANH MỤC</h3>
					<ul class="nav nav-list">
						<li><a href="/webtoeic/listExam">THI THỬ</a></li>
						<li><a href="/webtoeic/listGrammar">HỌC NGỮ PHÁP</a></li>
						<li><a href="/webtoeic/listVocab">HỌC TỪ VỰNG</a></li>
					</ul>

				</div>
			</div>
		</div>
	</div>




	<!--Pagination-->
	<c:if test="${listData.size() != 0}">

		<div class="paging">
			<c:if test="${currentPage != 1}">
				<a href="/webtoeic/listExam?page=${currentPage-1}">Back</a>
			</c:if>
			<c:if test="${currentPage == 1}">
				<a class="current">1</a>
			</c:if>

			<c:if test="${currentPage != 1}">
				<a href="/webtoeic/listExam?page=1">1</a>
			</c:if>

			<c:forEach var="pag" items="${pageList}" varStatus="loop">
				<c:if test="${currentPage == pag}">
					<a class="current">${pag}</a>
				</c:if>
				<c:if test="${currentPage != pag}">
					<a href="/webtoeic/listExam?page=${pag}">${pag}</a>
				</c:if>
			</c:forEach>

			<c:if test="${currentPage != totalPage}">
				<a href="/webtoeic/listExam?page=${currentPage+1} " class="pageNext">Next</a>
			</c:if>
		</div>
	</c:if>



	<!--/.End Pagination-->
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

	<!-- Modal -->
	<div class="modal fade" id="examModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content -->

			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">HƯỚNG DẪN LÀM BÀI THI THỬ</h4>
				</div>
				<div class="modal-body">
					<input class="hidden" id="idExamModal">
					<p>Bài thi thử gồm 2 phần: (100 câu hỏi)</p>
					<img style="float: left"
						src="https://img.icons8.com/nolan/64/000000/reading.png"> <br>
					<p>Phần 1: Listening skills (30 phút - 50 câu hỏi)</p>
					<br> <img style="float: left"
						src="https://img.icons8.com/nolan/64/000000/reading.png"> <br>
					<p>Phần 2: Reading skills (45 phút - 50 câu hỏi)</p>

					<br>
					<p>Bài thi được thực hiện sau khi xác nhận làm bài thi</p>

				</div>
			</div>


			<div class="modal-footer">
				<c:if test="${message == 'true'}">
					<button type="button" class="btn btn-primary" id="btnLamBaiThi">Làm
						bài thi</button>
				</c:if>
				
				    
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

			</div>
			 
			<input type="button" value="Take a Snap " id="btPic" onclick="takeSnapShot()" />
			<div id="camera" style="height:auto;width:auto; text-align:center;"></div>
			 
		</div>
	</div>


	<!-- Modal -->

	<jsp:include page="include/footerHome.jsp"></jsp:include>


</body>

</html>