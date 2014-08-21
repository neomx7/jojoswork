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

	<#-- 橙色, ok -->
	<#--
	<link rel="stylesheet" type="text/css" href="${rc.contextPath}/scripts/jquery/jqueryUI/themes/ui-lightness/jquery.ui.all.css" />
	-->
	<#-- 蓝色, ok -->
	<#--
	<link rel="stylesheet" type="text/css" href="${rc.contextPath}/scripts/jquery/jqueryUI/themes/start/jquery-ui.css" />
	-->
	<#-- gray色, ok -->
	<#--
	<link rel="stylesheet" type="text/css" href="${rc.contextPath}/scripts/jquery/jqueryUI/themes/smoothness/jquery-ui.css" />
	-->
	<#-- 白底红字, ok? -->
	<#--
	<link rel="stylesheet" type="text/css" href="${rc.contextPath}/scripts/jquery/jqueryUI/themes/flick/jquery-ui.css" />
	-->
	<#-- 蓝底橙字, ok? -->
	<#--
	<link rel="stylesheet" type="text/css" href="${rc.contextPath}/scripts/jquery/jqueryUI/themes/excite-bike/jquery-ui.css" />
	-->

	<#-- 雨过天晴色, ok -->
	<#--
	-->
	<link rel="stylesheet" type="text/css" href="${rc.contextPath}/scripts/jquery/jqueryUI/themes/cupertino/jquery-ui.css" />


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
	.ui-layout-pane {
		background: #FFF;
		border: 1px solid #BBB;
		padding: 2px;
		overflow: auto;
	}

	.errorTip {
		width: 100%;
		margin: 0 auto;
		text-align: center;
		padding: 24px 0;
		color: #333;
		background: #FFF1F0;
		border: 1px solid #f2bfbb;
	}

	.warnTip {
		width: 100%;
		margin: 0 auto;
		text-align: center;
		color: #333;
		background: #ffefc8;
		border: 1px solid #edc898;
		min-height: 78px;
		line-height: 78px;
	}

	.tipTitle {
		font-size: 14px;
		font-weight: bold;
		padding-bottom: 5px;
	}

	.tipContent {
		text-align: left;
		color: red;
		padding-left: 12px;
		line-height: 23px;
	}

	.tip {
		width: 1280px;
		margin: 0 auto;
		text-align: center;
		height: 78px;
		line-height: 78px;
		background: #c8ffc8;
		border: 1px solid #98ff98;
	}

	.tip a {
		text-decoration: none;
	}

	.ui-autocomplete-loading {
		background: white url("${rc.contextPath}/scripts/jquery/jqueryUI/imgs/ui-anim_basic_16x16.gif") right center no-repeat;
	}
	</style>

	<#-- jquery -->
	<#--
	<script type="text/javascript" src="${rc.contextPath}/scripts/jquery/jqueryJstree/libs/jquery.js"></script>
	 -->
	<script type="text/javascript" src="${rc.contextPath}/scripts/jquery/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/scripts/jquery/jquery.json-2.4.min.js"></script>


	<#-- jquery UI -->
	<#--
	<script type="text/javascript" src="${rc.contextPath}/scripts/jquery/jqueryUI/ui/jquery-ui.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/scripts/jquery/jqueryUI/ui/jquery.ui.position.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/scripts/jquery/jqueryUI/ui/jquery.ui.core.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/scripts/jquery/jqueryUI/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/scripts/jquery/jqueryUI/ui/jquery.ui.button.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/scripts/jquery/jqueryUI/ui/jquery.ui.tabs.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/scripts/jquery/jqueryUI/ui/jquery.ui.dialog.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/scripts/jquery/jqueryUI/ui/jquery.ui.mouse.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/scripts/jquery/jqueryUI/ui/jquery.ui.sortable.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/scripts/jquery/jqueryUI/ui/jquery.ui.autocomplete.js"></script>
	-->
	<script src="${rc.contextPath}/scripts/jquery/jqueryUI/jquery-ui-1.10.4.min.js" type="text/javascript"></script>



	<#--
	<script src="${rc.contextPath}/scripts/jquery/jqueryUI/custom/jquery-ui-custom.min.js" type="text/javascript"></script>
	-->

	<#-- jquery Layout -->
	<script src="${rc.contextPath}/scripts/jquery/jqueryLayout/jquery.layout-latest.js" type="text/javascript"></script>
	<script src="${rc.contextPath}/scripts/jquery/debug/debug.js" type="text/javascript"></script>

	<#-- jquery JqGird -->
	<script src="${rc.contextPath}/scripts/jquery/jqueryJqGird/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
	<script type="text/javascript">
		$.jgrid.no_legacy_api = true;
		$.jgrid.useJSON = true;
	</script>
	<script src="${rc.contextPath}/scripts/jquery/jqueryJqGird/js/minified/jquery.jqGrid.min.js" type="text/javascript"></script>
	<script src="${rc.contextPath}/scripts/jquery/jqueryJqGird/plugins/ui.multiselect.js" type="text/javascript"></script>
	<script src="${rc.contextPath}/scripts/jquery/jqueryJqGird/plugins/jquery.tablednd.js" type="text/javascript"></script>
	<script src="${rc.contextPath}/scripts/jquery/jqueryJqGird/plugins/jquery.contextmenu.js" type="text/javascript"></script>

	<#-- index -->
	<script src="${rc.contextPath}/scripts/jquery/index.js" type="text/javascript"></script>
	</head>
	<body scroll="auto" class="v-generated-body v-sa v-ch v-webkit v-mac">
	    ${body}
		<noscript>You have to enable javascript in your browser to use an application built with Vaadin.</noscript>
	</body>

</html>