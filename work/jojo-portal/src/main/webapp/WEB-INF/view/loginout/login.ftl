<!DOCTYPE HTML>
<!-- #登录页面 -->
<html lang="zh">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="shortcut icon" href="${rc.contextPath}/images/favicon.ico" />
		<title>登录 物料管理系统</title>
		<link href="${rc.contextPath}/styles/reset.css" rel="stylesheet" type="text/css">
		<link href="${rc.contextPath}/styles/style.css" rel="stylesheet" type="text/css">
		<link href="${rc.contextPath}/styles/form.css" rel="stylesheet" type="text/css">
		<style type="text/css">
			#login-icon{
				width:640px;
				float: left;
				text-align: center;
				margin-top: 12px;
			}

			#login-form{
				float: left;
				margin-top: 16px;
				padding-left: 36px;
			}

			#login-form form{
				border: 1px solid #ccc;
				border-top: 0;
				padding: 3px 10px 3px 15px;
			}

			#login-tip{
				float: left;
				margin-top: 24px;
				clear: both;
			}

			#login-form .field label {
				display: block;
				font-size: 14px;
				padding-bottom: 3px;
			}

			.submit-btn{
				margin: 12px 0;
			    width: 158px;
			    font-size: 18px;
			    height: 36px;
			    display: inline-block;
			    vertical-align: middle;
			    *vertical-align: auto;
			    zoom: 1;
			    *display: inline;
			    text-align: center;
			    overflow: visible;
			    cursor: pointer;
			    color: #fff;
			    background-color: #e64545;
			    border: solid 1px #e64545;
			    border-radius: 2px;
			    text-shadow: 0 1px rgba(51, 51, 51, 0.3);
			}

		</style>

	</head>

	<body>
		<div id="header"><img title="物料管理系统" class="logo" src="${rc.contextPath}/images/logo.jpg" alt="物料管理系统" border="0"></div>

		<div class="page">
				<div id="login-icon"><img title="物料管理系统" class="logo" src="${rc.contextPath}/images/login.jpg" alt="物料管理系统" border="0"></div>
				<div id="login-form">
					<div class="form-header">欢迎使用物料管理系统</div>
					<form action="${rc.contextPath}/login" method="post">
						<#--
						<div class="field error">${form.errors["login_error"]!}&nbsp;</div>
						-->
						<div class="field">
							<label for="userId">登录名</label>
							<input id="userId" name="userId" type="text" value="${form.userId!}" />
						</div>

						<div class="field">
							<label for="pwd">密　码</label>
							<input id="pwd" name="pwd" type="password" value="" />
						</div>

						<div class="field">
							<input type="hidden" name="redirectURL" value="${form.redirectURL!}" />
							<button type="submit" class="submit-btn">登　录</button>
						</div>

						<div id="login-tip">
							<div class="field">
								<span class="h3">使用请知</span>
							</div>
							<div class="field">
								请使用 <span class="h3"><a target="blank" href="http://www.google.cn/intl/zh-CN/chrome/">Google Chrome</a></span>、<span class="h3"><a target="blank" href="http://firefox.com.cn/download/">Firefox</a></span>、<span class="h3"><a target="blank" href="http://windows.microsoft.com/zh-cn/internet-explorer/download-ie">IE 8.0</a></span> 以后版本
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

		<#include "../common/footer.ftl">
	</body>

</html>