<html>
<head>
  <title>注册</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
</head>
<body>
<form action="/user/insert" method="post">
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
			<input type="submit" value="注册" />
		</div>
	</form>
</body>
</html> 