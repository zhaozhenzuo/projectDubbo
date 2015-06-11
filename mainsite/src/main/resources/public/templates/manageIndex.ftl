<html>
<head>
  <title>后台管理首页</title>
</head>
<body>
	<div>
	<#if username??>欢迎:${username}|<a href="/logout">退出</a>
	<br/>
	<#else>
	<a href="/login">登录</a>|<a href="/user/register">注册</a>
	</#if><div>
	
	<div>
	<p/>
	
	--------商品类目管理-----------<br/>
	<a href="/category/savePage">添加商品类目</a>
	<a href="/category/list">商品类目列表</a>
	<p/>
	
	--------商品管理-----------<br/>
	<a href="/product/savePage">添加商品</a>
	<a href="/product/manage">商品管理</a>
	<p/>
	
	--------常量管理-----------<br/>
	<a href="/codeinfo/savePage">添加常量</a>
	<a href="/codeinfo/list">常量列表</a>
	<p/>
	
	--------用户管理-----------<br/>
	<a href="/user/list">用户列表</a>
	<p/>
	
	</div>
</body>
</html> 