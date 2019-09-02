<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <title>Hello World!</title>
</head>
<body>
Hello ${name}!
<br/>
<table>
    <tr>
        <td>姓名</td>
        <td>年龄</td>
        <td>生日</td>
        <td>钱包</td>
        <td>朋友列表</td>
        <td>最好的朋友</td>
    </tr>
    <#if stus??>
        <#list stus as stu>
        <tr>
            <td>${stu_index + 1}</td>
            <td <#if stu.name =='小明'>style="background:red;"</#if>>${stu.name}</td>
            <td>${stu.age}</td>
            <td >${stu.money}</td>
        </tr>
        </#list>
    </#if>

</table>
</br>
<hr />
${stuMap['stu1'].name}</br>
<#--${stuMap['stu1'].age}</br>-->
${stuMap['stu2'].name}</br>
${stuMap['stu2'].age}</br>
<hr />
</body>
</head>
</html>