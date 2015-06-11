<html>
<head>
  <title>商品列表</title>
</head>
<body>
<#if msg??>
${msg}
</#if>

<#if res?? && res?has_content>
 <#list res as s>
   <br/>
   id:${s.id}
   <br/>
   商品名称:${s.name}
   <br/>
   <img src="${s.picUrls}"/>
   <br/>
   原价:${s.marketPrice}元
   <br/>
   现价:${s.price}元
   <br/>
   状态:${s.status}
   <br/>
   <a href="/product/detail?productId=${s.id}">商品详情</a><br/>
   <p/>
   ----------------------------------------------------------------------------
 </#list>
</#if>
</body>
</html> 