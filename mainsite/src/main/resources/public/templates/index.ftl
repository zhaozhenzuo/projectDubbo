<html>
<head>
  <title>首页</title>
</head>
<body>
	<div>
	<#if username??>欢迎:${username}|<a href="/logout">退出</a>
	<br/>
	<a href="/manageIndex">后台管理</a>
	<#else>
	<a href="/login">登录</a>|<a href="/user/register">注册</a>
	</#if>
	<div>
	
	<div>
	<p/>
	--------商品浏览-----------<br/>
	<a href="/product/list">商品浏览</a>
	<p/>
	
	--------购物车-----------<br/>
	<a href="/cart/detail">我的购物车</a>
	<p/>
	
	<br/>
	</div>
</body>
</html> 