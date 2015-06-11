<html>
<head>
  <title>添加sku</title>
</head>
<body>
<form action="save" method="post">
		<#if msg??>${msg}</#if>
		<br/>
		<br/>
		所属商品id:<#if productId??>${productId}</#if>
		所属商品名称:<#if productName??>${productName}</#if>
		<br/>
		------------------
		<div class="form-group">
			条形码：<input type="text" name="barcode" class="form-control"
				/>
		</div>
		<div class="form-group">
			颜色：<input type="text" name="colorAttributeVo.codeInfoId" class="form-control"
				/>
		</div>
		
		<div class="form-group">
			尺寸：<input type="text" name="sizeAttributeVo.codeInfoId" class="form-control"
				 />
		</div>
		<div class="form-group">
			销售价：<input type="text" name="salePrice" class="form-control"
				/>
		</div>
		<div class="form-group">
			原价：<input type="text" name="marketPrice" class="form-control"
				/>
		</div>
		<div class="form-group">
			库存：<input type="text" name="stockCount" class="form-control"
				/>
		</div>
		<div class="form-group">
			sku缩略图url：<input type="text" name="picUrls" class="form-control"
				/>
		</div>
		
		<#if productId??>
		<input type="hidden" name="productId" value="${productId}" class="form-control"
				/>
		</#if>

		<div class="form-group">
			<input type="submit" value="提交" />
		</div>
	</form>
</body>
</html> 