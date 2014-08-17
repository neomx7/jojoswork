<#-- 增加初始化 js -->
<script type="text/javascript" src="${rc.contextPath}/scripts/portal/equipment/equipment.js"></script>
<link href="${rc.contextPath}/styles/reset.css" rel="stylesheet" type="text/css">
<link href="${rc.contextPath}/styles/style.css" rel="stylesheet" type="text/css">
<link href="${rc.contextPath}/styles/form.css" rel="stylesheet" type="text/css">

<div class="v-csslayout" style="width: 100%;">
	<div class="v-csslayout-margin">
		<div class="v-csslayout-container">
			<!-- 申请表单 -->
			<div class="content">
				<div class="form">
					<div class="form-header">
						发起新物料申请
					</div>
					<form action="${rc.contextPath}/equipment/applyEquipment" method="post">
						<#if form.resultCode==200>
							<div class="field succ">物料申请发起成功！ </div>
						<#elseif form.resultCode !=0>
							<div class="field error">${form.errors["result_error"]!}&nbsp;</div>
						</#if>
						<div class="field">
							<label for="theName">申请名称　</label>
							<input id="theName" name="theName" type="text" value="" />
						</div>

						<div class="field">
							<label for=theRemark>备注</label>
							<input id="theRemark" name="theRemark" type="text" value="" />
						</div>

						<div class="field">
							<label for=theRemark>申请附件</label>
							<input id="theRemark" name="theRemark" type="text" value="" />
						</div>

						<div id="btn-nav">
							<button id="okBtn">确定</button>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
</div>