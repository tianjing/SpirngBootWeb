<html>
<head>
    <title>FreeMarker Example Web Application 1</title>
</head>
<body>
${message}
</body>
<#list table as dt>
<tr>
    <td>${dt.name}</td>
    <td> ${dt.sex}</td>
</#list>


<#list list1 as st>
    <#list st.getDt().getColumns().keys() as column>
    <span>${column}</span>
    </#list>
    <#list st.getDt() as row>
        <#list st.dt.getColumns().keys() as column>
<span>${row.getValue(column)}</span>
        </#list>
    </#list>
</#list>


<#list marketMap.keySet() as key>
  <option value="${marketMap.get(key).id}">${key}</option>
</#list>

</html>


