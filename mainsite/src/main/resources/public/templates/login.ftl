<html>
<head>
  <title>登录</title>
</head>
<body>
<form action="doLogin" method="post">
		<#if errmsg??>${errmsg}</#if>

		<div class="form-group">
			用户名：<input type="text" name="username" class="form-control"
				placeholder="User ID" />
		</div>
		<div class="form-group">
			密码： <input type="password" name="password" class="form-control"
				placeholder="Password" />
		</div>
		<div class="form-group">
			<input type="checkbox" name="rememberＭe" /> Remember me
		</div>
		<div class="form-group">
			<input type="submit" value="提交" />
		</div>
		<div><a href="/user/register">注册</a></div>
	</form>
</body>
</html> 