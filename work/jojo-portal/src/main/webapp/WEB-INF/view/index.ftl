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
												<!--
												<div id="menu_1#001" style="height: 54px; width: auto; overflow: hidden; float: left; padding-left: 0px; padding-top: 0px;">
													<div style="float: left; margin-left: 0px;">
														<div tabindex="0" class="v-button v-button-main-menu-button main-menu-button v-button-link link v-button-active active" role="button" style="height: 54px; width: 80px;">
															<span class="v-button-wrap">
																<img alt="" class="v-icon" src="${rc.contextPath}/styles/activiti/img/mm-tasks.png">
																<span class="v-button-caption">工作提醒</span>
															</span>
														</div>
													</div>
												</div>
												-->
												<div id="menu_1#002" style="height: 54px; width: auto; overflow: hidden; float: left; padding-left: 0px; padding-top: 0px;">
													<div style="float: left; margin-left: 0px;">
														<div tabindex="0" class="v-button v-button-main-menu-button main-menu-button v-button-link link" role="button" style="height: 54px; width: 90px;">
															<span class="v-button-wrap">
																<img alt="" class="v-icon" src="${rc.contextPath}/styles/activiti/img/mm-manage.png">
																<span class="v-button-caption">管理</span>
															</span>
														</div>
													</div>
												</div>
												<div id="menu_1#003" style="height: 54px; width: auto; overflow: hidden; float: left; padding-left: 0px; padding-top: 0px;">
													<div style="float: left; margin-left: 0px;">
														<div tabindex="0" class="v-button v-button-main-menu-button main-menu-button v-button-link link" role="button" style="height: 54px; width: 90px;">
															<span class="v-button-wrap">
																<img alt="" class="v-icon" src="${rc.contextPath}/styles/activiti/img/mm-process.png">
																<span class="v-button-caption">物料申请</span>
															</span>
														</div>
													</div>
												</div>

												<div style="height: 54px; overflow: hidden; float: left; padding-left: 0px; padding-top: 0px;">
													<div style="float: left; margin-left: 649px;">
														<div tabindex="0" class="v-menubar v-menubar-person person">
															<span class="v-menubar-menuitem v-menubar-menuitem-person-menu">
																<span class="v-menubar-submenu-indicator">►</span>
																<span class="v-menubar-menuitem-caption">Kermit The Frog</span>
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
					<div style="height: 36px; overflow: hidden; padding-left: 0px; padding-top: 0px; left: 0px; top: 0px; width: 100%;">
						<div style="float: left; margin-left: 0px;width: 100%;">
							<div class="v-horizontallayout v-horizontallayout-toolbar toolbar" style="overflow: hidden; height: 36px; width: 100%;">
								<div style="overflow: hidden; margin: 0px 10px; width: 100%; height: 36px;">
									<!-- #遍历每个2级菜单 -->
									<div style="height: 27px; width: 100%; overflow: hidden; float: left; padding-left: 0px; padding-top: 9px;">
										<div style="float: left; margin-left: 0px;width:100%;">
											<div class="v-customcomponent v-customcomponent-clickable clickable" style="width: 100%; height: 18px;">
												<div id="div_menu_2#parent" class="v-horizontallayout" style="overflow: hidden; width: 100%;; height: 18px;">
													<div style="overflow: hidden; margin: 0px; width: 100%; height: 18px;">
														<div style="height: 18px; width: 100%; overflow: hidden; float: left; padding-left: 0px; padding-top: 0px;">
															<div style="float: left; margin-left: 0px; width:100%;" id="div_menu_2">
																<div id="menu_2#000" tabindex="0" class="v-button v-button-link link" role="button" style="width: 69px;">
																	<span class="v-button-wrap"><span class="v-button-caption">你赢了</span></span>
																</div>
															</div>
														</div>
														<div style="height: 9px; width: 0px; overflow: hidden; float: left; padding-left: 0px; padding-top: 9px;">
															<div style="float: left; margin-left: 0px;">
																<div tabindex="0" class="v-button" role="button" style="display: none;">
																	<span class="v-button-wrap"><span class="v-button-caption"></span></span>
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
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- #LeftPane -->
<!-- allowOverflow auto-attached by option: west__showOverflowOnHover = true -->
<div id="LeftPane" class="ui-layout-west">
	<div style="height: auto; overflow: hidden; padding-left: 0px; padding-top: 0px; position: absolute; left: 0px; top: 36px; width: 100%;">
		<div style="float: left; margin-left: 0px;">
			<div class="v-table v-table-task-list task-list v-table-scrollable scrollable" style="height: 100%; width: 100%;">
				<div class="v-table-header-wrap" style="width: 100%; display: none;">
					<div class="v-table-header" style="overflow: hidden;">
						<div style="width: 100%;">
							<table>
								<tbody>
									<tr>
										<td style="width: 100%;">
											<div class="v-table-resizer"></div>
											<div class="v-table-sort-indicator"></div>
											<div class="v-table-caption-container" style="width: 100%;"></div>
										</td>
										<td style="width: 100%;">
											<div class="v-table-resizer"></div>
											<div class="v-table-sort-indicator"></div>
											<div class="v-table-caption-container" style="width: 100%;"></div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="v-table-column-selector" style="display: none;"></div>
				</div>
				<div tabindex="-1" class="v-scrollable v-table-body-wrapper v-table-body" style="zoom: 1; position: relative; overflow: auto; height: 100%; width: 100%;">
					<div style="height: 100%;">
						<div class="v-table-row-spacer" style="height: 0px;"></div>
						<table class="v-table-table" id="tbl_menu_3">
							<tbody>
							</tbody>
						</table>
						<div class="v-table-row-spacer" style="height: 0px;"></div>
					</div>
					<div tabindex="0" style="position: fixed; top: 0px; left: 0px;"></div>
				</div>
				<div class="v-table-footer-wrap" style="display: none; width: 100%;">
					<div class="v-table-footer" style="overflow: hidden;">
						<div style="width: 100%;">
							<table>
								<tbody>
									<tr>
										<td style="width: 33px;">
											<div class="v-table-footer-container" style="width: 21px;">&nbsp;
											</div>
										</td>
										<td style="width: 100%;">
											<div class="v-table-footer-container" style="width: 100%;">&nbsp;
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
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