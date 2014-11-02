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
					<form id="taskTODOform_${form.processInstanceTask.taskId}" method="post">
						<input type="hidden" id="todoInstTaskId" name="theTaskId" value="${form.processInstanceTask.taskId!}" readOnly="readOnly"/>
						<input type="hidden" id="todoInstId" name="theInstId" value="${form.processInstanceTask.processInstanceId!}" readOnly="readOnly"/>
						<input type="hidden" id="todoBusinessKey" name="businessKey" value="${form.businessKey!}" readOnly="readOnly"/>
						<input type="hidden" id="approvedRequired" name="approvedRequired" value="${form.approvedRequired? string('true', 'false')}" readOnly="readOnly"/>
						<input type="hidden" id="lastNode" name="lastNode" value="${form.lastNode? string('true', 'false')}" readOnly="readOnly"/>

						<#if form.approvedRequired>
							<br><input type="radio" name="apprvFlg" value="1" class="validate[required]" checked="checked"/>同意 <input type="radio" name="apprvFlg" value="2"  class="validate[required]"/>打回
							<br>批示意见<textarea rows="3" cols="40" name="apprvContent" ></textarea>
							  <#if (form.lastNode??) && ((form.lastNode? string('true', 'false')) == 'true')>
							  	<br><div id="setNextUser_${form.processInstanceTask.taskId}" style="display:none;" class="ui-widget" >
								  <label for="nextUser_${form.processInstanceTask.taskId}">转下个负责人: </label>
								  <input id="nextUser_${form.processInstanceTask.taskId}" name="nextAssigneeName" class="validate[required] text-input"/>
								  <input id="nextUserId_${form.processInstanceTask.taskId}" name="nextAssignee" type="hidden"/>
								</div>
							  <#else>
								<br><div id="setNextUser_${form.processInstanceTask.taskId}" class="ui-widget" >
								  <label for="nextUser_${form.processInstanceTask.taskId}">转下个负责人: </label>
								  <input id="nextUser_${form.processInstanceTask.taskId}" name="nextAssigneeName" class="validate[required] text-input"/>
								  <input id="nextUserId_${form.processInstanceTask.taskId}" name="nextAssignee" type="hidden"/>
								</div>
							  </#if>


					  <#else>
						<br><div id="setNextUser_${form.processInstanceTask.taskId}" class="ui-widget" >
						  <label for="nextUser_${form.processInstanceTask.taskId}">转下个负责人: </label>
						  <input id="nextUser_${form.processInstanceTask.taskId}" name="nextAssigneeName" class="validate[required] text-input"/>
						  <input id="nextUserId_${form.processInstanceTask.taskId}" name="nextAssignee" type="hidden"/>
						</div>
					   </#if>

					</form>
				</div>
				<div class="btn-nav">

				<#if (form.lastNode??) && ((form.lastNode? string('true', 'false')) == 'true')>
					<button id="endTaskBtn" >结束流程</button>
					<button id="submitBtn_${form.processInstanceTask.taskId}" style="display:none;">转后续处理</button>
			  	<#else>
					<button id="submitBtn_${form.processInstanceTask.taskId}">转后续处理</button>
			  	</#if>
				</div>
			</div>
		</div>
	</div>
</div>