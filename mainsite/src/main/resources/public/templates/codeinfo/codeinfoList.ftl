<html>
<head>
  <title>常量表列表</title>
</head>
<body>
<div>
<#if msg??>${msg}</#if>
</div>


<#if res?? && res?has_content>
 <#list res as s>
   -----------------
   <br/>
   id:${s.id}
   <br/>
   所属业务:${s.bizGroup}
   <br/>
   类别code:${s.typeCode}
   <br/>
   类别名称:${s.typeName}
   <br/>
   常量值:${s.value}
   <br/>
   <a href="delete?id=#{s.id}">删除</a>
   <br/>
   -----------------
 </#list>
</#if>
</body>
</html> 