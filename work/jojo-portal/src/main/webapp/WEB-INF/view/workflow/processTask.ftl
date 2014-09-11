<#-- 增加初始化 js -->
<script type="text/javascript" src="${rc.contextPath}/scripts/portal/demo-list/todoTaskList-list.js"></script>
<div class="v-csslayout" style="width: 100%;">
	<div class="v-csslayout-margin">
		<div id="formDiv" class="v-csslayout-container">
			<form action="" method="post" id="form">
			<input type="text" id="taskId" name="taskId" value="${form.taskId}"/>
			下一位：<input type="text" id="nextAssignee" name="nextAssignee" value=""/>
			<input type="button" value="提交" id="submit" onclick="processTODOTask()"/>
			</form>
		</div>
	</div>
</div>
