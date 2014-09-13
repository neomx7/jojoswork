<#-- 增加初始化 js -->
<script type="text/javascript" src="${rc.contextPath}/scripts/portal/workflow/workflow-task.js"></script>

<link rel="stylesheet" type="text/css" href="${rc.contextPath}/styles/reset.css" />


<div class="v-csslayout" style="width: 100%;">
	<div class="v-csslayout-margin">
		<div class="v-csslayout-container">
			<!-- 申请表单 -->
			<div class="content">
				<div class="form">
					<div class="form-header">
						申请信息
					<input type="hidden" id="todoInstId" name="processInstanceTask.processInstanceId" value="${form.processInstanceTask.processInstanceId}" />
					<input type="hidden" id="todoInstTaskId" name="processInstanceTask.taskId" value="${form.processInstanceTask.taskId}" />
					</div>
					<div id="taskFormInfo" showURL="${form.businessKeyURL}"></div>
				</div>

				<div class="form">
					<form id="taskTODOform">
					<div class="form-header">
						流程处理
					</div>
					<br><input type="radio" name="apprvFlg" value="1" />同意 <input type="radio" name="apprvFlg" value="2" />打回
					<br>批示意见<textarea rows="5" cols="120" name="apprvContent"></textarea>
					</form>
				</div>

				<div class="btn-nav">
					<button id="submitBtn_${form.processInstanceTask.taskId}">转后续处理</button>
				</div>


			</div>
		</div>
	</div>
</div>
