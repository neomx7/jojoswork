<!-- #TopPane -->
<!-- manually attach allowOverflow method to pane -->
<div id="TopPane" class="ui-layout-north" style="padding:0px;" onmouseover="myLayout.allowOverflow('north')" onmouseout="myLayout.resetOverflow(this)">
	<script type="text/javascript">
		appRelPath = '${rc.contextPath}';
	</script>
	<div id="indexHeader" class="v-app v-theme-activiti v-app-ExplorerApp">
		<div class="v-view Default style" tabindex="1">
			<div class="v-loading-indicator" style="position: absolute; display: none;"></div>
			<div class="v-verticallayout v-verticallayout-main main" style="overflow: hidden; width: 100%;">
				<div style="overflow: hidden; margin: 0px; width: 100%; ">
					<#-- 欢迎界面的题头 -->
					<div style="height: 54px; width: 100%; overflow: hidden; padding-left: 0px; padding-top: 0px;">
						<div style="float: left; margin-left: 0px; width: 100%;">
							<div class="v-csslayout v-csslayout-header header" style="width: 100%;">
								<div class="v-csslayout-margin">
									<div class="v-csslayout-container">
										<div class="v-horizontallayout" style="overflow: hidden; height: 54px; width: 100%;">
											<div style="overflow: hidden; margin: 0px 18px 0px 0px; width: 100%; height: 54px;">
												<div style="height: 54px; width: auto; overflow: hidden; float: left; padding-left: 0px; padding-top: 0px;">
													<div style="float: left; margin-left: 0px;">
														<#-- TODO 换掉logo class="v-label v-label-h1 h1 v-label-logo logo" -->
														<div class="v-label v-label-h1 h1 v-label-logo " style="width: 100%;"></div>
													</div>
												</div>
												<#-- 这里放置用户信息 -->
												<div style="height: 54px; overflow: hidden; float: left; padding-left: 0px; padding-top: 0px;">
													<div style="float: left; margin-left: 649px;">
														<div tabindex="0" class="v-menubar v-menubar-person person">
															<span class="v-menubar-menuitem v-menubar-menuitem-person-menu">
																<span class="v-menubar-menuitem-caption">${form.userDO.usrId}</span>
															</span>
														</div>
													</div>
												</div>
												<div style="width: 0px; height: 0px; clear: both; overflow: hidden;"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<#-- 快速导航菜单 -->
				</div>
			</div>
		</div>
	</div>
</div>

<!-- #LeftPane -->
<!-- allowOverflow auto-attached by option: west__showOverflowOnHover = true -->
<div id="LeftPane" class="ui-layout-west">
	<div style="height: 100%;  width: 100%;">
		<div id="accordion">
			<#list form.menus as menulist>
				  <h3 id="menu1#${menulist.theId}" code="code#${menulist.dictCode}">${menulist.theName}</h3>
				  <div id="div#${menulist.theId}"></div>
			</#list>
		</div>
	</div>
</div>



<!-- #CenterPane -->
<div id="CenterPane" class="ui-layout-center">
	<#-- 标签 tab 形式,使用 jquery-UI -->
	<div id="tabs" style="height:99%;">

		<#-- 默认元素不能删除 -->
		<ul id="tabsUL" >
			<li><a href="#tabs-1">欢迎使用.</a></li>
		</ul>
		<div id="tabs-1" >
			<p>默认内容,显示欢迎信息,用户个人信息</p>
		</div>
	</div>

</div>

<!--hidden pane-->
<div id="consoleDlg" style="width:90%;"></div>
<div id="workflowDlg" style="width:90%;"></div>
<!-- hidden error message -->
<div id="globalErrDiv" style="display:none;">
	<div class="errorTip">
		<div class="tipTitle">
			系统异常
		</div>
		温馨提示：您访问的功能出现异常，请与开发人员联系！
		<div class="tipContent">
			<span class="h5">异常信息如下：</span>
			</br>
			<span id="tip"></span>
			<span id="tipDesc"></span>
		</div>
	</div>
</div>
<!--hidden success message -->
<div id="globalSucDiv" style="display:none;">
	<div class="tip">
		<div class="tipTitle">
			系统处理成功
		</div>
		<div class="tipContentSucc">
			<span class="h5">详细信息如下：</span>
			</br>
			<span id="tip_suc"></span>
			<span id="tipDesc_suc"></span>
		</div>
	</div>
</div>
<!--hidden info message -->
<div id="globalFormDiv" style="display:none;width:100%;">
</div>



<!-- #RightPane -->
<div id="RightPane" class="ui-layout-east">
	This is the east pane, closable, slidable and resizable

	<!-- this button has its event added dynamically in document.ready -->
	<p><button id="btnCloseEast">Close Me</button></p>

</div>

<!-- #FooterPane -->
<div id="FooterPane" class="ui-layout-south footer">
	<div class="v-label">© JOJO.org 版权所有。</div>
</div>