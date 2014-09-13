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
					<div class="form-header">
						流程信息
					</div>
					<div id="taskTabs_${form.processInstanceTask.taskId}" >
						<ul id="taskTabsUL_${form.processInstanceTask.taskId}" >
							<li><a href="#taskTabs_${form.processInstanceTask.taskId}-1">流程图(图形)</a></li>
							<li><a href="#taskTabs_${form.processInstanceTask.taskId}-2">流程记录(文字列表)</a></li>
						</ul>
						<div id="taskTabs_${form.processInstanceTask.taskId}-1" >
						</div>
						<div id="taskTabs_${form.processInstanceTask.taskId}-2" >
							<table id="taskHisGrid_${form.processInstanceTask.taskId}"></table>
							<#--
							<div id="taskHisGrid_${form.processInstanceTask.taskId}Pager"></div>
							-->
						</div>
					</div>
				</div>

				<div class="form">
					<div class="form-header">
						流程处理
					</div>
					<form id="taskTODOform_${form.processInstanceTask.taskId}" method="post">
						<input type="hidden" id="todoInstTaskId" name="theTaskId" value="${form.processInstanceTask.taskId}" readOnly="readOnly"/>
						<input type="hidden" id="todoInstId" name="theInstId" value="${form.processInstanceTask.processInstanceId}" readOnly="readOnly"/>
						<input type="hidden" id="todoBusinessKey" name="businessKey" value="${form.businessKey}" readOnly="readOnly"/>
						<br><input type="radio" name="apprvFlg" value="1" class="validate[required]"/>同意 <input type="radio" name="apprvFlg" value="2"  class="validate[required]"/>打回
						<br>批示意见<textarea rows="3" cols="40" name="apprvContent" ></textarea>
						<br><div id="setNextUser_${form.processInstanceTask.taskId}" class="ui-widget" >
							  <label for="nextUser_${form.processInstanceTask.taskId}">转下个负责人: </label>
							  <input id="nextUser_${form.processInstanceTask.taskId}" name="nextAssignee" class="validate[required] text-input"/>
							</div>
						<div class="btn-nav">
							<input id="submitBtn_${form.processInstanceTask.taskId}" type="submit" value="转后续处理" class="submit"/>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
</div>
