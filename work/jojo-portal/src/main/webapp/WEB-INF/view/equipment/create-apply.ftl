<#-- 增加初始化 js -->
<script type="text/javascript" src="${rc.contextPath}/scripts/portal/equipment/apply.js"></script>
<script type="text/javascript" src="${rc.contextPath}/scripts/portal/common/user-search.js"></script>
 <style>
  label {
    display: inline-block;
    width: 5em;
  }
  </style>
<div class="v-csslayout" style="width: 100%;">
	<div class="v-csslayout-margin">
		<div class="v-csslayout-container">
			<!-- 申请表单 -->
			<div class="content">
				<div class="form">
					<div class="form-header">
						发起新物料申请
					</div>
					<p>
					<form id="targetForm" method="post">
						<div class="field">
							<label for="theName">申请名称　</label>
							<input id="theName" name="theName" type="text" value="" class="validate[required] text-input" title="必填项"/>
							<span><font  color="red">*</font></span>
						</div>

						<div class="field">
							<label for=theRemark>备注</label>
							<input id="theRemark" name="theRemark" type="text" value="" />
						</div>

						<div class="field">
							<label for=theAttach>申请附件</label>
							<input id="theAttach" name="" type="text" value="" />
						</div>
						<div id="setNextUser" class="ui-widget" >
						  <label for="nextUser">人员姓名: </label>
						  <input id="nextUser"  title="输入要提交的人员姓名"/>
						  <input id="nextUsrId" name="nextUsrId" style="display:none;"/>

						</div>
					</form>
				</div>
				<div class="btn-nav">
					<button id="saveBtn" class="ui-button-primary">保存草稿</button>
					<button id="submitBtn">提交申请</button>
				</div>


				<#--
				<div class="container" id="content" style="display:none;">
					<div class="row page" id="demo" style="display:block;">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-2 col-sm-4 col-xs-4" style="text-align:left;">
									<input type="text" value="" style="box-shadow:inset 0 0 4px #eee; width:120px; margin:0; padding:6px 12px; border-radius:4px; border:1px solid silver; font-size:1.1em;" id="demo_q" placeholder="Search" />
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div id="jstree_demo" class="demo" style="margin-top:1em; min-height:200px;"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				-->

			</div>
		</div>
	</div>
</div>
