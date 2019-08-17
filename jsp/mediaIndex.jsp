<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="/struts-tags" prefix="s"%>
<!doctype html>
<html lang="en">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Theme Page</title>

  <!-- Bootstrap core CSS -->
  <link href="${pageContext.request.contextPath }/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="${pageContext.request.contextPath }/css/business-frontpage.css" rel="stylesheet">
  
  <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
  <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
 
	

</head>

<body>

  <%@ include file="/jsp/header.jsp" %>
  <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    
                </h4>
 </div>

  <!-- Header -->
  <div class="container">
	  <div class="jumbotron">
	    <h1>This is a Media Page</h1> 
	    <p>You can upload all kind of Media, and the format of media is limited</p> 
	  </div>
	  
 </div>
 <s:actionmessage/>
 	<!-- post button -->
<s:if test="#session.existUser">
<!-- 按钮触发模态框 -->
<div class="container">
<button class="btn btn-primary btn-lg span3 offset1" data-toggle="modal" data-target="#myModal" >
    Post Media
</button>
</div>
</s:if>

<s:if test="!#session.existUser">
<!-- 按钮触发模态框 -->
<div class="container">
<button class="btn btn-primary btn-lg span3 offset1" onclick="warning()" >
   Post Media
</button>
</div>
</s:if>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    
    <div class="modal-dialog">
    
    
        <div class="modal-content">
        
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    For new Media
                </h4>
            </div>
            
            <form id="Form" name="Form" action="${pageContext.request.contextPath }/media_postMedia.action" 
            method=post enctype="multipart/form-data">
				  
				  <div class="form-group">
				    <label for="newTheme">Media Title</label>
				    <input type="text" class="form-control" id="" name="title" 
				    placeholder="Media Title">
				  </div>
				  
				  <div class="form-group">
				    <label for="newTheme">More Detail</label>
				    <textarea class="form-control" id="newTheme" rows="9" name="detail"
				     placeholder="Media Title"></textarea>
				  </div>
				  
				  <div class="form-group">
				    <label for="InputFile">Poster Image</label>
				    <input type="file" id="InputFile" name="upload">
				    <p class="help-block">it supports jpg format</p>
				  </div>
				  
	
				  <div class="form-group">
				    <label for="InputFile">Media upload</label>
				    <input type="file" id="InputFile" name="uploadMedia">
				    <p class="help-block">it supports mp4 format</p>
				  </div>

				  <button type="submit" id="BUTT" class="btn btn-default">Submit</button>
			</form>
        </div><!-- /.modal-content -->
        
    </div><!-- /.modal -->

</div>
<!-- end post -->
  <!-- Page Content -->
<%--   <a href="${pageContext.request.contextPath}/media_playMedia.action?sid=<s:property value="sid"/>&&pid=<s:property value="pid"/>">
							 <img
								src="<s:property value="fileimage"/>" width="250"
								height="200"><s:property value="filetype"/>
							<img />
							
						</a> --%>
  <div class="container">
  

 <div class="row">
 <s:iterator value="list">
      <div class="col-md-4 mb-5">
        <div class="card h-100">
          <a href="${pageContext.request.contextPath}/media_playMedia.action?sid=<s:property value="sid"/>&&pid=<s:property value="pid"/>">
							 <img
								src="<s:property value="fileimage"/>" width="250"
								height="200">
							<img />
							
						</a> 
          <div class="card-body">
            <h4 class="card-title"><s:property value="detail"/></h4>
            <p class="card-text"><!-- 这里可以做视频描述 --></p>
          </div>
          <div class="card-footer">
            <a href="#" class="btn btn-primary">Find Out More!</a>
          </div>
        </div>
      </div>
</s:iterator>
</div><!-- end row -->


</div>

  
  <div class="container">
  <FORM id="articleForm" name="articleForm"
   action="${pageContext.request.contextPath }/media_findAllMedia.action"
   method=post class="form-inline">
  
	   
	   
  	
   
 
  
 <%--  <DIV style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
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
</DIV> --%>  
</FORM>
</div> 							
								
 <!-- /.container -->

  <!-- Footer -->
<%@ include file="/jsp/footer.jsp" %>
	 <script> 
		$("#BUTT").click(function(){
			alert("a");
				setInterval(function(){
					$("#newTheme").append("${sessionScope.speed}");
				},3000);
		});

	</script> 
</body>

</html>