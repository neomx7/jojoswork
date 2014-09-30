<ul id="user-list-table">
<#if form.userDOs?exists>
	<#list form.userDOs as datas>
	  <li class="ui-state-default"  id="deptUsr_${datas.usrId}">
		  姓名:${datas.theName}
		  <br>电子邮件:${datas.email}
		  <br>办公电话:${datas.tel}
		  <br>移动电话:${datas.mobile}</span>
	  	</li>
	</#list>
</#if>
</ul>