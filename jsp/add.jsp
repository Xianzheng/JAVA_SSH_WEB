<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="/struts-tags" prefix="s"%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
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
	    <h1>This is a Forum</h1> 
	    <p></p> 
	  </div>
	  
 </div>
 <s:actionmessage/>
 	<!-- post button -->
<s:if test="#session.existUser">
<!-- 按钮触发模态框 -->
<div class="container">
<button class="btn btn-primary btn-lg span3 offset1" data-toggle="modal" data-target="#myModal" >
    Post New Theme
</button>
</div>
</s:if>

<s:if test="!#session.existUser">
<!-- 按钮触发模态框 -->
<div class="container">
<button class="btn btn-primary btn-lg span3 offset1" onclick="warning()" >
   Post New Theme
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
                    For new Theme
                </h4>
            </div>
            
            <form id="Form" name="Form" action="${pageContext.request.contextPath }/article_post.action" 
            method=post enctype="multipart/form-data">
				  <div class="form-group">
				    <label for="newTheme">New theme</label>
				    <input type="text" class="form-control" id="newTheme" name="title" placeholder="Email">
				  </div>
				  
				  <div class="form-group">
				    <label for="exampleFormControlTextarea1">Theme Content</label>
				    <textarea class="form-control" name="cont" id="exampleFormControlTextarea1" rows="3"></textarea>
				  </div>


				  <div class="checkbox">
				    <label>
				      <input type="checkbox"> Check me out
				    </label>
				  </div>
				  <button type="submit" class="btn btn-default">Submit</button>
			</form>
        </div><!-- /.modal-content -->
        
    </div><!-- /.modal -->

</div>
<!-- end post -->
  <!-- Page Content -->
  <FORM id="articleForm" name="articleForm"
		action="${pageContext.request.contextPath }/article_findAll.action"
method=post>
  <div class="container">
  
	   <table class="table table-bordered ">
	   	<tr>
	      <td>Title</td>
	    
	      <td>Author</td>
	    
	      <td>Reviews</td>
	    
	      <td>Replies</td>
	    
	      <td>Date</td>
	      <s:if test="#session.existUser.state==2">
	      <td>Delete</td>
	      </s:if>
	     <!--  value="#session.existUser.name" -->
	      
	      	
	     
	    </tr>
	    <s:iterator  value="list">
	  	<tr>
	      <td> <a href="${pageContext.request.contextPath}/article_findByPid.action?pid=<s:property value="pid"/>"><s:property value="title"/></a></td>
	    
	      <td><s:property value="author"/></td>
	    
	      <td><s:property value="reviews"/></td>
	    
	      <td><s:property value="replies"/></td>
	    
	      <td><s:property value="pdate"/></td>
	      
	      <s:if test="#session.existUser.state==2">
	      <td>
	      	<a href="${pageContext.request.contextPath}/article_deleteTheme.action?pid=<s:property value="pid"/>">
	      	<button type="button" class="close" data-dismiss="modal" >
                    &times;
             </button>
             </a>
	      </td>
	      </s:if>
	    </tr>
	 	</s:iterator>
	   </table>
	   
  	
  </div>  
  
<DIV style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
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
</FORM>							
								
 <!-- /.container -->

  <!-- Footer -->
<%@ include file="/jsp/footer.jsp" %>

</body>

</html>