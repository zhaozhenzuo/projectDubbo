<html>
<head>
  <title>用户列表</title>
</head>
<body>
<#if res?? && res?has_content>

 <#list res as s>
   -----------------
   <br/>
   id:${s.id}
   <br/>
   用户名:${s.username}
   <br/>
   地址:<#if s.address??>${s.address}</#if>
   <br/>
   电话:<#if s.phone??>${s.phone}</#if>
   <br/>
   是否禁用:<#if s.enabled??>${s.enabled}</#if>
   <br/>
   -----------------
 </#list>
</#if>
</body>
</html> 