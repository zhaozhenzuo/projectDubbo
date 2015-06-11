<html>
<head>
  <title>sku列表</title>
</head>
<body>
<#if res?? && res?has_content>

 <#list res as s>
   -----------------
   <br/>
   id:${s.id}
   <br/>
   条形码:${s.barcode}
   <br/>
   所属业务:${s.bizGroup}
   <br/>
   类别code:${s.typeCode}
   <br/>
   类别名称:${s.typeName}
   <br/>
   常量值:${s.value}
   <br/>
   -----------------
 </#list>
</#if>
</body>
</html> 