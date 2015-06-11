<html>
<head>
  <title>商品类别添加</title>
</head>
<body>
<form action="save" method="post">
		<#if msg??>${msg}</#if>
		<div class="form-group">
			商品类别名称：<input type="text" name="name" class="form-control"
				/>
		</div>
		<div class="form-group">
			父级类目：<input type="text" name="parentId" class="form-control"
				/>
		</div>

		<div class="form-group">
			<input type="submit" value="提交" />
		</div>
	</form>
</body>
</html> 