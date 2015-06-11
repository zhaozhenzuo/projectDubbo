<html>
<head>
  <title>商品管理列表</title>
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
   <a href="/sku/savePage?productId=${s.id}&productName=${s.name}">添加sku到此商品</a><br/>
   <#if s.status=='NEW' || s.status=='ROLLBACK' || s.status=='OFFLINE'><a href="/product/online?productId=${s.id}">上线此商品</a><br/></#if>
   <#if s.status=='ONLINE'><a href="/product/offline?productId=${s.id}">下线此商品</a><br/></#if>
   <a href="/product/detail?productId=${s.id}">商品详情</a><br/>
   <p/>
   ----------------------------------------------------------------------------
 </#list>
</#if>
</body>
</html> 