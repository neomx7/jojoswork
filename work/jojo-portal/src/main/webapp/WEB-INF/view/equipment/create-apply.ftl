<#-- 增加初始化 js -->
<#--
<link href="${rc.contextPath}/styles/reset.css" rel="stylesheet" type="text/css">
<link href="${rc.contextPath}/styles/style.css" rel="stylesheet" type="text/css">
<link href="${rc.contextPath}/styles/form.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${rc.contextPath}/scripts/jquery/jqueryJstree/jquery.address-1.6.js"></script>
<script type="text/javascript" src="${rc.contextPath}/scripts/jquery/jqueryJstree/vakata.js"></script>
 -->
<script type="text/javascript" src="${rc.contextPath}/scripts/portal/equipment/apply.js"></script>

<!-- 树形菜单js -->

<#--
<script type="text/javascript" src="${rc.contextPath}/scripts/jquery/jqueryJstree/jstree.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/scripts/portal/common/deprt-tree.js"></script>
<link href="${rc.contextPath}/scripts/jquery/jqueryJstree/themes/default/style.min.css" rel="stylesheet" type="text/css">
 -->

<script type="text/javascript" src="${rc.contextPath}/scripts/portal/common/user-search.js"></script>

<div class="v-csslayout" style="width: 100%;">
	<div class="v-csslayout-margin">
		<div class="v-csslayout-container">
			<!-- 申请表单 -->
			<div class="content">
				<div class="form">
					<div class="form-header">
						发起新物料申请
					</div>
					<form id="targetForm" method="post">
							<#--
							<div id="succ_tip" class="field succ" style="display:none;">物料申请发起成功！ </div>
							<div id="err_tip" class="field error" style="display:none;"></div>
							-->
						<div class="field">
							<label for="theName">申请名称　</label>
							<input id="theName" name="theName" type="text" value="${form.theName}" class="validate[required] text-input"/>
						</div>

						<div class="field">
							<label for=theRemark>备注</label>
							<input id="theRemark" name="theRemark" type="text" value="${form.theRemark}" />
						</div>

						<div class="field">
							<label for=theAttach>申请附件</label>
							<input id="theAttach" name="" type="text" value="" />
						</div>
					</form>
				</div>
				<div id="btn-nav">
					<button id="saveBtn">保存草稿</button>
					<button id="submitBtn">提交申请</button>
				</div>
				<div id="setNextUser" class="ui-widget" style="display:none;">
				  <label for="nextUser">人员姓名: </label>
				  <input id="nextUser" name="nextUser"/>
				</div>


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


			</div>
		</div>
	</div>
</div>
