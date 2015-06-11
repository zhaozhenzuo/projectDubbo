<html>
<head>
  <title>商品详情页</title>
</head>
<body>
<#if res?? && res?has_content>
   <#if res.status!='ONLINE'>
     商品:${res.name}已过期
   <#else>
      商品名称:${res.name}<br/>
	  <#if res.skuVoList?? && res.skuVoList?has_content>
      <#list res.skuVoList as skuVo>
        <br/>
     	skuid:${skuVo.id}<br/>
     	颜色:${skuVo.colorAttributeVo.codeInfoValue}<br/>
     	尺寸:${skuVo.sizeAttributeVo.codeInfoValue}<br/>
     	原价:${skuVo.marketPrice}<br/>
     	现价:${skuVo.salePrice}<br/>
     	库存:
     	<#if skuVo.stockCountInMem??>
     		${skuVo.stockCountInMem}<br/>
     		<a href="/cart/addCartItem?skuId=${skuVo.id}&count=1">加入购物车</a><br/>
     	<#else>
     		商品已被抢光
     	</#if>
     	<img src="${skuVo.picUrls}"/><br/>
     	<a href="/cart/detail">我的购物车</a>
     	<p/>
-------------------------------------------------------------------------------------------
     </#list>
	</#if>     	
   </#if>

  
</#if>
</body>
</html> 