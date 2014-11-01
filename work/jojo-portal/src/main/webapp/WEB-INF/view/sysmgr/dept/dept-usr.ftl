<#if form.userDOs?exists>
<div class="column">
	<#list form.userDOs as datas>

		  <div class="portlet">
		    <div class="portlet-header">${datas.theName}</div>
		    <div class="portlet-content">姓名: ${datas.theName}
		    <br>角色: <#list datas.roles as roleDatas>${roleDatas.theName} </#list>
		    <br>电子邮件: ${datas.email}
			  <br>办公电话: ${datas.tel}
			  <br>移动电话: ${datas.mobile}
		    </div>
		  </div>
	</#list>
</div>
</#if>