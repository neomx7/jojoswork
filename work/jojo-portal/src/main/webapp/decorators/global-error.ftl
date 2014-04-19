<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="cn">
	<head>
	  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	  <title>JOJO's Default Site</title>
	<#-- link css -->
	<!--[if lt IE 7]><link rel="stylesheet" type="text/css" href="${rc.contextPath}/styles/activiti/lt7.css" /><![endif]-->
	<link rel="stylesheet" type="text/css" href="${rc.contextPath}/styles/activiti/styles.css" /> 
	<#--
	<link rel="stylesheet" type="text/css" href="${rc.contextPath}/styles/reindeer/styles.css" />
	-->
	<link rel="stylesheet" type="text/css" href="${rc.contextPath}/scripts/jquery/jqueryUI/themes/ui-lightness/jquery.ui.all.css" />
	<link rel="stylesheet" type="text/css" href="${rc.contextPath}/scripts/jquery/jqueryLayout/layout-default-latest.css" />
	<link rel="stylesheet" type="text/css" href="${rc.contextPath}/scripts/jquery/jqueryJqGird/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="${rc.contextPath}/scripts/jquery/jqueryJqGird/plugins/ui.multiselect.css" />

	<#-- style -->
	<style>
	/** layout css */
	html, body {
	    margin: 0;
	    padding: 0;
	    font-size: 75%;
	}
	/** custom css */
	.ui-layout-pane #indexHeader
	{
		padding:0px;
	}
	</style>

	<#-- jquery -->
	<script type="text/javascript" src="${rc.contextPath}/scripts/jquery/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/scripts/jquery/jquery.json-2.4.min.js"></script>
	</head>
	<body scroll="auto" class="v-generated-body v-sa v-ch v-webkit v-mac">
	    ${body}
		<noscript>You have to enable javascript in your browser to use an application built with Vaadin.</noscript>
	</body>

</html>