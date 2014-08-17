<!DOCTYPE HTML>
<html lang="zh">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="shortcut icon" href="${rc.contextPath}/images/favicon.ico" />
		<title>系统错误 - 物料管理系统</title>
		<link href="${rc.contextPath}/styles/reset.css" rel="stylesheet" type="text/css">
		<link href="${rc.contextPath}/styles/style.css" rel="stylesheet" type="text/css">
	</head>

	<body>
		<#include "./top.ftl">

		<div class="page">
			<div class="warnTip">
				<div class="tipTitle">
					<#if form?exists>
						您好 ${form.contextUserName}，温馨提示：您没有权限访问此页面（功能）！
					<#else>
						您好${userName}，汇付天下温馨提示：系统错误，请稍后再试！
					</#if>
				</div>
				<div class="tipContent">
				<#if exceptionTrace?exists>
					错误堆栈如下：
					</br>
					${exceptionTrace}
				</#if>
				</div>
			</div>
		</div>

		<#include "./footer.ftl">
	</body>

</html>