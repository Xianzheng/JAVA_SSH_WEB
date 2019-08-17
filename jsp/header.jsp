<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Start Bootstrap Template</title>

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/business-frontpage.css" rel="stylesheet">

  <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
  <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script>
  	function warning(){
		alert("It needs Login before");
	}
  </script>
</head>

<body>
  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="#">Welcome <s:property value="#session.existUser.name"/> <s:actionerror/></a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active">
            <a class="nav-link" href="#">
              <span class="sr-only">(current)</span>
            </a>
          </li> 
          
          <li class="nav-item">
         
          <s:if test="!#session.existUser">
            <a class="nav-link" href="#" data-toggle="modal" data-target="#myModal_login">
            Login
            </a>
          
          </s:if>
         
          <s:if test="#session.existUser">
          <a class="nav-link" href="${pageContext.request.contextPath }/user_logout.action" >
            Logout
            </a>
          </s:if>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath }">
            Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#" data-toggle="modal" data-target="#myModal_regist">
            Regist</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" 
            href="${ pageContext.request.contextPath }/jsp/add.jsp/article_findAll.action">
            Forum</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" 
            href="${ pageContext.request.contextPath }/jsp/mediaIndex.jsp/media_findAllMedia.action">
            Media</a>
          </li>
          <li class="nav-item">
          	<s:if test="#session.existUser">
            <a class="nav-link" 
            href="${ pageContext.request.contextPath }/jsp/gameIndex.jsp">Game</a>
            </s:if>
            <s:if test="!#session.existUser">
            <a class="nav-link" 
            href="javascript:warning();">Game</a>
            </s:if>
          </li>
           
        </ul>
      </div>
    </div>
  </nav>
<!-- 模态框（Modal）注册 -->
<div class="modal fade" id="myModal_regist" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    Registration
                </h4>
            </div>
            <form id="Form" name="Form" action="${pageContext.request.contextPath }/user_regist.action" method=post>
			  <div class="form-group">
			    <label for="username">UserName</label>
			    <input type="text" name="username" class="form-control" id="" placeholder="username">
			  </div>
			  <div class="form-group">
			    <label for="password">Password</label>
			    <input type="password" name="password"class="form-control" id="" placeholder="Password">
			  </div>
			  <div class="form-group">
			    <label for="password">User's Name</label>
			    <input type="text" name="name"class="form-control" id="" placeholder="User's Name">
			  </div>
			  <div class="form-group">
			    <label for="email">Email address</label>
			    <input type="email" name="email" class="form-control" id="regist_email" placeholder="Email">
 			  </div>
 			  <div class="form-group">
			    <label for="validation">Validation Code</label>
			    <input type="password" name="regist_validation" class="form-control" id="regist_validation" placeholder="Password">
			  </div>
			 <div class="form-group">
			 `<button type="button" class="btn btn-default" id="regist_button">Send Code</button>
			  <button type="submit" class="btn btn-default">Regist</button>
			  </div>
			</form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 模态框（Modal）登陆 -->
<div class="modal fade" id="myModal_login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    Login
                </h4>
            </div>
            <form action="${pageContext.request.contextPath }/user_login.action" method=post>
			  <div class="form-group">
			    <label for="username">user name</label>
			    <input type="text" name="username" class="form-control" id="" placeholder="UserName">
			  </div>
			  <div class="form-group">
			    <label for="password">password</label>
			    <input type="password" name="password" class="form-control" id="" placeholder="Password">
			  </div>
			  <div class="form-group">
			    <label for="email">email</label>
			    <input type="text" name="email" class="form-control" id="login_email" placeholder="如果您登陆过请直接登陆（100天）">
			  </div>
			  <div class="form-group">
			    <label for="password">Validation Code</label>
			    <input type="password" name="login_validation" class="form-control" id="" placeholder="如果您登陆过请直接登陆（100天）">
			  </div>
			  <button type="button" class="btn btn-default" id="loadButton">Send Validation Code</button>
			  <button type="submit" class="btn btn-default">Login</button>
			</form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
  <!-- Header -->
  <script type="text/javascript">
  
  	$("#loadButton").click(
  	function()
    {
  		
  		alert("sending login validation code to your email");
  		
  		$.ajax({
  			
  	        type : 'POST',               
  	        url : '/Web01/user_login_validation.action',
  	        data: {"login_email":$("#login_email").val().toString()},
  	      	
  	        error : function() {
  	            alert('Request Failed ');
  	        },
  	        success : function(data) {
  	            
  	        }
  	   });
    });
  	
  	$("#regist_button").click(
  	
  	function(){
  		alert("sending regist code to your email");
		$.ajax({
  			
  	        type : 'POST',               
  	        url : '/Web01/user_regist_validation.action',
  	        data: {"regist_email":$("#regist_email").val().toString()},
  	      	
  	        error : function() {
  	            alert('Request Failed');
  	        },
  	        success : function(data) {
  	            
  	        }
  	   });
  		
  		
  	});
  </script>

</body>
</html>