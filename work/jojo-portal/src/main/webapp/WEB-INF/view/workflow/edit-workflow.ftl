<#-- 增加初始化 js -->
<script type="text/javascript" src="${rc.contextPath}/scripts/portal/workflow/workflow-task.js"></script>
<script type="text/javascript" src="${rc.contextPath}/scripts/portal/common/user-search.js"></script>

<link rel="stylesheet" type="text/css" href="${rc.contextPath}/styles/reset.css" />


<div class="v-csslayout" style="width: 100%;">
	<div class="v-csslayout-margin">
		<div class="v-csslayout-container">
			<!-- 申请表单 -->
			<div class="content">

				<#-- tab标签页面展示流程表单和流程信息 -->
				<div class="form">
					<div class="form-header">
						申请信息
					</div>
					<div id="taskFormInfo" showURL="${form.businessKeyURL}"></div>
				</div>
				<div style="width:100%;">
					<#--
					<div class="form-header">
						流程信息
					</div>
					<div id="taskTabs" style="width:100%;">
						<ul id="taskTabsUL_Diagram" >
							<li><a href="#taskTabs-1">流程图(图形)</a></li>
						</ul>
						<div id="taskTabs-1" >
						</div>

						<ul id="taskTabsUL_List" >
							<li><a href="#taskTabs-2">流程记录(文字列表)</a></li>
						</ul>
						<div id="taskTabs-2" >
							<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" align="center">
								<tr>
									<td>
										<table id="taskHisGrid"></table>
										<div id="taskHisGridPager"></div>
									</td>
								</tr>
							</table>
						</div>
					</div>
					 -->
				</div>

				<div class="form">
					<form id="taskTODOform">
					<input type="hidden" id="todoInstTaskId" name="theTaskId" value="${form.processInstanceTask.taskId}" />
					<input type="hidden" id="todoInstId" name="theInstId" value="${form.processInstanceTask.processInstanceId}" />
					<input type="hidden" id="todoBusinessKey" name="businessKey" value="${form.businessKey}" />
					<div class="form-header">
						流程处理
					</div>
					<br><input type="radio" name="apprvFlg" value="1" />同意 <input type="radio" name="apprvFlg" value="2" />打回
					<br>批示意见<textarea rows="5" cols="30" name="apprvContent"></textarea>
					<br><div id="setNextUser_${form.processInstanceTask.taskId}" class="ui-widget" >
						  <label for="nextUser_${form.processInstanceTask.taskId}">转下个负责人: </label>
						  <input id="nextUser_${form.processInstanceTask.taskId}" name="nextAssignee" class="validate[required] text-input"/>
						</div>
					</form>
				</div>

				<div class="btn-nav">
					<button id="submitBtn_${form.processInstanceTask.taskId}">转后续处理</button>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="taskTabs-2" class="v-csslayout" style="width: 100%;">
	<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" align="center">
		<tr>
			<td>
				<table id="taskHisGrid"></table>
				<div id="taskHisGridPager"></div>
			</td>
		</tr>
	</table>
</div>
