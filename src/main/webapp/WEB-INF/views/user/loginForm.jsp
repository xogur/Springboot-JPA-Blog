<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<div class="container">
	<form action="/auth/loginProc" method="post">
	  <div class="form-group">
	    <label for="username">Username</label>
	    <input type="text" name="username" class="form-control" placeholder="Enter Username" id="username">
	  </div>
		  
	  <div class="form-group">
	    <label for="password">Password</label>
	    <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
	  </div>
	  
	  <div class="form-group form-check">
	    <label class="form-check-label">
	      <input class="form-check-input" name="remember" type="checkbox"> Remember me
	    </label>
	  </div>
	  
	  <button id="btn-login" class="btn btn-primary">로그인</button>
	  <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=473493d9587df5cdba24e3d27437f2f2&redirect_uri=http://localhost:8000/auth/kakao/callback
"><img height=38 src="/image/kakao_login_button.png"/></a>
	</form>
	
</div>

<script src="/js/user.js"></script>
<%@include file="../layout/footer.jsp"%>



