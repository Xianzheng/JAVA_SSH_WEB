<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="/struts-tags" prefix="s"%>
<!doctype html>
<html lang="en">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Media</title>

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
		  <div style="width:300px; height:auto; float:left; display:inline; margin:100px">
			 <video id="my-video" class="video-js" controls preload="auto" width="364" height="264"
  			poster="PosterImage" data-setup="{}">
    		<source src="<s:property value="file"/>" type='video/mp4'>
    
    		<p class="vjs-no-js">
	      		To view this video please enable JavaScript, and consider upgrading to a web browser that
	      		<a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
    		</p>
  			</video>
  		</div>  
  		
  		
  	</div>
  	

  			<script src="http://vjs.zencdn.net/6.2.0/video.js"></script> 
	
<%-- <DIV style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
	共[<B><s:property value="totalCount"/></B>]条记录,[<B><s:property value="totalPage"/></B>]页
	,每页显示
	<select name="pageSize" onchange="to_page()">
		<option value="3" <s:if test="pageSize == 3">selected</s:if>>3</option>
		<option value="5" <s:if test="pageSize == 5">selected</s:if>>5</option>
		<option value="10" <s:if test="pageSize == 10">selected</s:if>>10</option>
	</select>
	条
	<s:if test="currPage!=1">
	[<A href="javascript:to_page(<s:property value="1"/>)">首页</A>]
	[<A href="javascript:to_page(<s:property value="currPage-1"/>)">前一页</A>]
	</s:if>&nbsp;&nbsp;
	<B>
	<s:iterator var ="i" begin ="1" end ="totalPage">
		<s:if test="#i == currPage">
			<s:property value="#i"/>
		</s:if>
		<s:else>
			<a href="javascript:to_page(<s:property value="#i"/>)"><s:property value="#i"/></a>
		</s:else>
	</s:iterator>
	</B>&nbsp;&nbsp;
	<s:if test="currPage!=totalPage">
	[<A href="javascript:to_page(<s:property value="currPage+1"/>)">后一页</A>] 
	[<A href="javascript:to_page(<s:property value="totalPage"/>)">尾页</A>]
	</s:if>
	到
	<input type="text" size="3" id="page" name="currPage" />
	页
	
	<input type="button" value="Go" onclick="to_page()"/>
</DIV>
</FORM> --%>							
								
 <!-- /.container -->

  <!-- Footer -->
<%@ include file="/jsp/footer.jsp" %>

</body>

</html>