<html>
<head>
  <title>购物车详情页</title>
</head>
<body>
--------------我的购物车---------------------------------<p/>
<#if res?? && res?has_content>
   <#if res.cartItemDTOList?? && res.cartItemDTOList?has_content>
     <#list res.cartItemDTOList as cartItem>
-------------------------------------------------------------------------------------------<br/>
        商品名称:${cartItem.productVo.name}<br/>
        skuId:${cartItem.skuId}<br/>
        原价:${cartItem.originalPriceOfOne}<br/>
        销售价:${cartItem.salePriceOfOne}<br/>
        件数：${cartItem.count}<br/>
        小计:${cartItem.totalPriceForCurrentSku}<br>
        <a href="/cart/deleteCartItem?skuId=${cartItem.skuId}">删除</a>
        
        <br/>
-------------------------------------------------------------------------------------------
       <br/>
       总计:${res.totalPrice}
     </#list>
   </#if>
</#if>
</body>
</html> 