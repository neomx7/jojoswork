<!-- #TopPane -->
<!-- manually attach allowOverflow method to pane -->
<div id="TopPane" class="ui-layout-north" style="padding:0px;" onmouseover="myLayout.allowOverflow('north')" onmouseout="myLayout.resetOverflow(this)">
	
	<div id="indexHeader" class="v-app v-theme-activiti v-app-ExplorerApp">
		<div class="v-view Default style" tabindex="1">
			<div class="v-loading-indicator" style="position: absolute; display: none;"></div>
			<div class="v-verticallayout v-verticallayout-main main" style="overflow: hidden; width: 100%;">
				<div style="overflow: hidden; margin: 0px; width: 100%; ">
					<#-- 欢迎界面的头 -->
					<div style="height: 54px; width: 100%; overflow: hidden; padding-left: 0px; padding-top: 0px;">
						<div style="float: left; margin-left: 0px;">
							<div class="v-csslayout v-csslayout-header header" style="width: 100%;">
								<div class="v-csslayout-margin">
									<div class="v-csslayout-container">
										<div class="v-horizontallayout" style="overflow: hidden; height: 54px; width: 100%;">
											<div style="overflow: hidden; margin: 0px 18px 0px 0px; width: 1745px; height: 54px;">
												<div style="height: 54px; width: 650px; overflow: hidden; float: left; padding-left: 0px; padding-top: 0px;">
													<div style="float: left; margin-left: 0px;">
														<#-- TODO 换掉logo class="v-label v-label-h1 h1 v-label-logo logo" -->
														<div class="v-label v-label-h1 h1 v-label-logo " style="width: 650px;"></div>
													</div>
												</div>
												<div style="height: 54px; width: 80px; overflow: hidden; float: left; padding-left: 0px; padding-top: 0px;">
													<div style="float: left; margin-left: 0px;">
														<div tabindex="0" class="v-button v-button-task task v-button-main-menu-button main-menu-button v-button-link link v-button-active active" role="button" style="height: 54px; width: 80px;">
															<span class="v-button-wrap">
																<img alt="" class="v-icon" src="/activiti-explorer/VAADIN/themes/activiti/img/mm-tasks.png">
																<span class="v-button-caption">任务</span>
															</span>
														</div>
													</div>
												</div>
												<div style="height: 54px; width: 80px; overflow: hidden; float: left; padding-left: 0px; padding-top: 0px;">
													<div style="float: left; margin-left: 0px;">
														<div tabindex="0" class="v-button v-button-process process v-button-main-menu-button main-menu-button v-button-link link" role="button" style="height: 54px; width: 80px;">
															<span class="v-button-wrap">
																<img alt="" class="v-icon" src="/activiti-explorer/VAADIN/themes/activiti/img/mm-process.png">
																<span class="v-button-caption">流程</span>
															</span>
														</div>
													</div>
												</div>
												<div style="height: 54px; width: 80px; overflow: hidden; float: left; padding-left: 0px; padding-top: 0px;">
													<div style="float: left; margin-left: 0px;">
														<div tabindex="0" class="v-button v-button-report report v-button-main-menu-button main-menu-button v-button-link link" role="button" style="height: 54px; width: 80px;">
															<span class="v-button-wrap">
																<img alt="" class="v-icon" src="/activiti-explorer/VAADIN/themes/activiti/img/mm-reports.png">
																<span class="v-button-caption">报表</span>
															</span>
														</div>
													</div>
												</div>
												<div style="height: 54px; width: 90px; overflow: hidden; float: left; padding-left: 0px; padding-top: 0px;">
													<div style="float: left; margin-left: 0px;">
														<div tabindex="0" class="v-button v-button-manage manage v-button-main-menu-button main-menu-button v-button-link link" role="button" style="height: 54px; width: 90px;">
															<span class="v-button-wrap">
																<img alt="" class="v-icon" src="/activiti-explorer/VAADIN/themes/activiti/img/mm-manage.png">
																<span class="v-button-caption">管理</span>
															</span>
														</div>
													</div>
												</div>
												<div style="height: 54px; width: 765px; overflow: hidden; float: left; padding-left: 0px; padding-top: 0px;">
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
						<div style="float: left; margin-left: 0px;">
							<div class="v-horizontallayout v-horizontallayout-toolbar toolbar" style="overflow: hidden; height: 36px; width: 100%;">
								<div style="overflow: hidden; margin: 0px 10px; width: 1743px; height: 36px;">
									<div style="height: 27px; width: 69px; overflow: hidden; float: left; padding-left: 0px; padding-top: 9px;">
										<div style="float: left; margin-left: 0px;">
											<div class="v-customcomponent v-customcomponent-clickable clickable" style="width: 69px; height: 18px;">
												<div class="v-horizontallayout" style="overflow: hidden; width: 69px; height: 18px;">
													<div style="overflow: hidden; margin: 0px; width: 69px; height: 18px;">
														<div style="height: 18px; width: 69px; overflow: hidden; float: left; padding-left: 0px; padding-top: 0px;">
															<div style="float: left; margin-left: 0px;">
																<div tabindex="0" class="v-button v-button-link link" role="button">
																	<span class="v-button-wrap"><span class="v-button-caption">我的流程</span></span>
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
									
									<div style="height: 27px; width: 117px; overflow: hidden; float: left; padding-left: 21px; padding-top: 9px;">
										<div style="float: left; margin-left: 0px;">
											<div class="v-customcomponent v-customcomponent-clickable clickable" style="width: 117px; height: 18px;">
												<div class="v-horizontallayout" style="overflow: hidden; width: 117px; height: 18px;">
													<div style="overflow: hidden; margin: 0px; width: 117px; height: 18px;">
														<div style="height: 18px; width: 117px; overflow: hidden; float: left; padding-left: 0px; padding-top: 0px;">
															<div style="float: left; margin-left: 0px;">
																<div tabindex="0" class="v-button v-button-link link v-button-active active" role="button">
																	<span class="v-button-wrap"><span class="v-button-caption">已部署流程定义</span></span>
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
									
									
									<div style="height: 36px; width: 1143px; overflow: hidden; float: left; padding-left: 21px; padding-top: 0px;">
										<div style="float: left; margin-left: 0px;">
											<div class="v-label" style="width: 1143px;">&nbsp;</div>
										</div>
									</div>
									
									<div style="height: 30px; width: 72px; overflow: hidden; float: left; padding-left: 21px; padding-top: 6px;">
										<div style="float: left; margin-left: 0px;">
											<div tabindex="0" class="v-button v-button-toolbar-button toolbar-button" role="button">
												<span class="v-button-wrap"><span class="v-button-caption">启动流程</span></span>
											</div>
										</div>
									</div>
									<div style="height: 30px; width: 120px; overflow: hidden; float: left; padding-left: 21px; padding-top: 6px;">
										<div style="float: left; margin-left: 0px;">
											<div tabindex="-1" class="v-button v-disabled v-button-toolbar-button toolbar-button" role="button">
												<span class="v-button-wrap"><span class="v-button-caption">转换为可编辑模型</span></span>
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

<!-- #LeftPane -->
<!-- allowOverflow auto-attached by option: west__showOverflowOnHover = true -->
<div id="LeftPane" class="ui-layout-west">
	<div style="height: 100%; overflow: hidden; padding-left: 0px; padding-top: 0px; position: absolute; left: 0px; top: 72px; width: 100%;">
		<div style="float: left; margin-left: 0px;">
			<div class="v-table v-table-task-list task-list v-table-scrollable scrollable" style="height: 100%; width: 100%;">
				<div tabindex="-1" class="v-scrollable v-table-body-wrapper v-table-body" style="zoom: 1; position: relative; overflow: auto; height: 100%; width: 100%;">
					<div style="height: 100%;">
						<div class="v-table-row-spacer" style="height: 0px;"></div>
						<table class="v-table-table">
							<tbody>
								<tr class="v-table-row v-selected">
									<td class="v-table-cell-content" style="width: 22px;">
										<div class="v-table-cell-wrapper" style="width: 22px;">
											<div class="v-embedded v-embedded-image" style="width: 22px; height: 22px;">
												<img src="${rc.contextPath}/styles/activiti/img/task-22.png" />
											</div>
										</div>
									</td>
									<td class="v-table-cell-content" style="width: 100%;">
										<div class="v-table-cell-wrapper" style="width: 100%;">待办任务1</div>
									</td>
								</tr>
							</tbody>
						</table>
						<div class="v-table-row-spacer" style="height: 0px;"></div>
					</div>
					<div tabindex="0" style="position: fixed; top: 0px; left: 0px;"></div>
				</div>
			</div>
		</div>
	</div>
</div>



<!-- #CenterPane -->
<div id="CenterPane" class="ui-layout-center">
	<div
		style="height: 100%; overflow: hidden; padding-left: 0px; padding-top: 0px; position: absolute; left: auto; top: 36px; width: 100%;">
		<div style="float: left; margin-left: 0px;">
			<div class="v-verticallayout v-verticallayout-detail-panel detail-panel v-verticallayout-white white" style="overflow: hidden; height: 100%; width: 100%;">
				<div style="overflow: hidden; margin: 18px; width: width: 100%; height: 90%;">
					<div style="height: 90%; width: 100%; overflow: hidden; padding-left: 0px; padding-top: 0px;">
						<div style="float: left; margin-left: 0px;">
							<div class="v-csslayout v-csslayout-detail-panel detail-panel" style="height: 90%; width: 100%;">
								<div class="v-csslayout-margin">
									<div class="v-csslayout-container">
										<div class="v-panel v-panel-light light" style="overflow: hidden; height: 90%; width: 100%;">
											<div class="v-panel-captionwrap">
												<div class="v-panel-nocaption v-panel-nocaption-light">
													<span></span>
												</div>
											</div>
											<div class="v-panel-content v-panel-content-light v-scrollable" tabindex="-1" style="position: relative; height: 100%;">
												<div class="v-verticallayout" style="overflow: hidden; width: 100%;; height: 100%;;">
													<div style="overflow: hidden; margin: 18px; width: auto; height: auto;">
														<div style="height: 100%; width: auto; overflow: hidden; padding-left: 0px; padding-top: 0px;">
															<div style="float: left; margin-left: 0px;">
																<div class="v-verticallayout v-verticallayout-block-holder block-holder" style="overflow: hidden; width: auto; height: 31px;">
																	<div style="overflow: hidden; margin: 0px; width: auto; height: 31px;">
																		<div style="height: 18px; width: auto; overflow: hidden; padding-left: 0px; padding-top: 0px;">
																			<div style="float: left; margin-left: 0px;">
																				<div class="v-label v-label-h3 h3" style="width: auto;">相关内容</div>
																			</div>
																		</div>
																		<div style="width: 0px; height: 0px; clear: both; overflow: hidden;"></div>
																	</div>
																</div>
																
																<div class="v-csslayout v-csslayout-block-holder block-holder" style="width: 100%;">
																	<div class="v-csslayout-margin">
																		<div class="v-csslayout-container">
																			<!-- 调用表格并分页 -->
																			<table width="990" height="560" border="0" cellpadding="0" cellspacing="0" align="center">
																				<tr>
																					<td>
																						<table id="jqGirdList"></table>
																						<div id="pager"></div>
																					</td>
																				</tr>
																			</table>
																		</div>
																	</div>
																</div>
															</div>
														</div>
														
														<#-- 华丽的分割线 -->
														<#--
														<div style="height: 19px; width: 100%; overflow: hidden; padding-left: 0px; padding-top: 0px;">
															<div style="float: left; margin-left: 0px;">
																<div class="v-csslayout v-csslayout-block-holder block-holder" style="width: auto;">
																	<div class="v-csslayout-margin">
																		<div class="v-csslayout-container"></div>
																	</div>
																</div>
															</div>
														</div>
														-->
													</div>
												</div>
											</div>
											<div class="v-panel-deco v-panel-deco-light"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div style="width: 0px; height: 0px; clear: both; overflow: hidden;"></div>
				</div>
			</div>
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