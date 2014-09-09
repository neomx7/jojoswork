<#-- 增加初始化 js -->
<script type="text/javascript" src="${rc.contextPath}/scripts/portal/equipment/apply.js"></script>

<div class="v-csslayout" style="width: 100%;">
	<div class="v-csslayout-margin">
		<div class="v-csslayout-container">
			<!-- 申请表单 -->
			<div class="content">
				<div class="form">
					<div class="form-header">
						处理物料申请
					</div>
					<form id="targetFormE" method="post">
						<input id="theId" name="theId" type="hidden" value="${form.theId}"/>
						<div class="field">
							<label for="theNameE">申请名称　</label>
							<input id="theNameE" name="theName" type="text" value="${form.theName}" class="validate[required] text-input"/>
						</div>

						<div class="field">
							<label for=theRemarkE>备注</label>
							<input id="theRemarkE" name="theRemark" type="text" value="${form.theRemark}" />
						</div>

						<div class="field">
							<label for=theAttachE>申请附件</label>
							<input id="theAttachE" name="" type="text" value="" />
						</div>
						<div id="setNextUser" class="ui-widget" style="display:none;">
						  <label for="nextUserE">人员姓名: </label>
						  <input id="nextUserE" name="nextUsrId"/>
						</div>
					</form>
				</div>
				<div id="btn-nav">
					<button id="saveBtnE">保存草稿</button>
					<button id="submitBtnE">提交申请</button>
				</div>


			</div>
		</div>
	</div>
</div>
