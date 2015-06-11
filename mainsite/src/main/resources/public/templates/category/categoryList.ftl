<html>
<head>
  <title>商品类别列表</title>
</head>
<body>
<#if res?? && res?has_content>

 <#list res as s>
   -----------------
   <br/>
   id:${s.id}
   <br/>
   商品类别名称:${s.name}
   <br/>
   父级类目id:${s.parentId}
   <br/>
   -----------------
 </#list>
</#if>
</body>
</html> 