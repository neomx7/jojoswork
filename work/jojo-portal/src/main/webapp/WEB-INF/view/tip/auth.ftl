<!DOCTYPE HTML>
<html lang="zh">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="shortcut icon" href="${rc.contextPath}/images/favicon.ico" />
		<title>访问拒绝 - 物料管理系统</title>
		<link href="${rc.contextPath}/styles/reset.css" rel="stylesheet" type="text/css">
		<link href="${rc.contextPath}/styles/style.css" rel="stylesheet" type="text/css">
	</head>

	<body>
		<#include "../common/top.ftl">

		<div class="page">

			<div class="content">
				<div class="errorTip">
					<div class="tipTitle">
						访问拒绝
					</div>
					您好<span class="h4"> ${form.contextUserName}</span>，温馨提示：您没有权限访问此页面（功能/菜单）！
				</div>
			</div>
		</div>

		<#include "../common/footer.ftl">
	</body>

</html>