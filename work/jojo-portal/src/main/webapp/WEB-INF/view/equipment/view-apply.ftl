<div class="v-csslayout" style="width: 100%;">
	<div class="v-csslayout-margin">
		<div class="v-csslayout-container">
			<!-- 申请表单 -->
			<div class="content">
				<div class="form">
					<div class="form-header">
						物料申请查看
					</div>
						<div class="field">
							<label for="theName">申请名称　</label>
							<input id="theName" name="theName" type="text" value="${form.theName}" class="validate[required] text-input" readOnly="readOnly" />
						</div>

						<div class="field">
							<label for=theRemark>备注</label>
							<input id="theRemark" name="theRemark" type="text" value="${form.theRemark}" readOnly="readOnly" />
						</div>

						<div class="field">
							<label for=theAttach>申请附件</label>
							<input id="theAttach" name="" type="text" value="" readOnly="readOnly" />
						</div>
				</div>
			</div>
		</div>
	</div>
</div>
