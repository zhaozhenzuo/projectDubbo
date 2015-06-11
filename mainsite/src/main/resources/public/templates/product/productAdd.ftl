<html>
<head>
  <title>商品添加</title>
</head>
<body>
<form action="save" method="post">
		<#if errmsg??>${errmsg}</#if>

		<div class="form-group">
			商品名称：<input type="text" name="name" class="form-control"
				/>
		</div>
		
		<div class="form-group">
			类别：<input type="text" name="lowestCategoryId" class="form-control"
				 />
		</div>
		<div class="form-group">
			图片url：<input type="text" name="picUrls" class="form-control"
				/>
		</div>
		<div class="form-group">
			商品最低售卖价格：<input type="text" name="price" class="form-control"
				/>
		</div>
		<div class="form-group">
			市场价：<input type="text" name="marketPrice" class="form-control"
				/>
		</div>
		<div class="form-group">
			品牌id：<input type="text" name="brandId" class="form-control"
				/>
		</div>
		
		<div class="form-group">
			<input type="submit" value="提交" />
		</div>
	</form>
</body>
</html> 