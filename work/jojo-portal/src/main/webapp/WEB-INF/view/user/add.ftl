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
						用户新增
					</div>
					<form action="${rc.contextPath}/punuser/regist" method="post">
						<#if form.resultCode=="1">
							<div class="field succ">物料申请发起成功！ </div>
						<#else>
							<div class="field error">${form.errors["result_error"]!}&nbsp;</div>
						</#if>
						<div class="field">
							<label for="logUserName">登录名　</label>
							<input id="logUserName" name="logUserName" type="text" value="${form.logUserName}" />
						</div>

						<div class="field">
							<label for=logPassword>账户密码</label>
							<input id="logPassword" name="logPassword" type="password" value="" />
						</div>

						<div class="field">
							<label for=comfirmPassword>确认密码</label>
							<input id="comfirmPassword" name="comfirmPassword" type="password" value="" />
						</div>

						<div class="field">
							<label for=userName>用户名　</label>
							<input id="userName" name="userName" type="text" value="${form.userName}" />
						</div>

						<#--
						<div class="field">
							<label for=userDep>部　门　</label>
							<input id="userDep" name="userDep" type="text" value="${form.userDep}" />
						</div>
						-->

						<div class="field">
							<label for=userTel>电　话　</label>
							<input id="userTel" name="userTel" type="text" value="${form.userTel}" />
						</div>

						<div class="field">
							<label for=userEmail>电子邮箱</label>
							<input id="userEmail" name="userEmail" type="text" value="${form.userEmail}" />
						</div>

						<div class="field">
							<label for=userRole>用户角色</label>
							<#if form.roles?exists>
								<select id="userRole" name="userRole">
								<option value="">用户角色选择</option>
								<#list form.roles as role>
									<option title="${role.roleDesc}" value="${role.id}" <#if role.id == form.userRole> selected="selected" </#if> >${role.roleName}</option>
								</#list>
							</select>
							<#else>
								用户角色不存在，请<a href="${rc.contextPath}/role/add" target="blank">新增风控角色</a>
							</#if>
						</div>

						<div class="field">
							<label>状　态　</label>
							<input type="radio" style="width:30px; border:none;" id="status0" name="status" value="0" <#if form.status=="0"> checked="checked" </#if> /><label for=status0>有效</label>
							<input type="radio" style="width:30px; border:none;" id="status1" name="status" value="1" <#if form.status=="1"> checked="checked" </#if> /><label for=status1>无效</label>
						</div>

						<div id="btn-nav">
							<button type="submit" class="btn" style="padding: 5px 12px;">提　交</button>
							<#if form.hasAuthentication("${rc.contextPath}/punuser/list")>
							<a href="${rc.contextPath}/punuser/list" class="btn">取　消</a>
							</#if>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>