<html>
<head>
  <title>常量表添加</title>
</head>
<body>
<form action="save" method="post">
		<#if msg??>${msg}</#if>
		<div class="form-group">
			常量所属业务：<input type="text" name="bizGroup" class="form-control"
				/>
		</div>
		
		<div class="form-group">
			类别code：<input type="text" name="typeCode" class="form-control"
				 />
		</div>
		<div class="form-group">
			类别名称：<input type="text" name="typeName" class="form-control"
				/>
		</div>
		<div class="form-group">
			常量值：<input type="text" name="value" class="form-control"
				/>
		</div>

		<div class="form-group">
			<input type="submit" value="提交" />
		</div>
	</form>
</body>
</html> 