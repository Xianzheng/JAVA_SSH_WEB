<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="/struts-tags" prefix="s"%>
<!doctype html>
<html lang="en">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Error info</title>

  <!-- Bootstrap core CSS -->
  <link href="${pageContext.request.contextPath }/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="${pageContext.request.contextPath }/css/business-frontpage.css" rel="stylesheet">
  <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
  <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="http://vjs.zencdn.net/6.2.0/video-js.css" rel="stylesheet">
  
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
	<SCRIPT>
		function to_page(page){
			if(page){
				$("#page").val(page);
			}
			document.articleForm.submit();		
		}
		function warning(){
			alert("It needs Login before");
		}
	</SCRIPT>

</head>

<body>

  <%@ include file="/jsp/header.jsp" %>

	<div class="container">
		<div class="jumbotron">
			<h1>Bootstrap Tutorial</h1>
			<p>Bootstrap is the most popular HTML, CSS, and JS framework for
				developing responsive, mobile-first projects on the web.</p>
		</div>
		<div>
		<h1>The file you uploaded not match rules</h1>
		</div>
	</div>
	
	
	<%@ include file="/jsp/footer.jsp" %>

</body>

</html>