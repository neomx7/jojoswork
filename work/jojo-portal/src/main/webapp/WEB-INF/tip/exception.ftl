<div class="page">
	<div class="content">
		<div class="errorTip">
			<div class="tipTitle">
				系统异常
			</div>
			您好 <span class="h4">${form.contextUserName}</span>，温馨提示：您访问的功能出现异常，请与开发人员联系！
			<#if form.tip??>
			<div class="tipContent">
				<span class="h5">异常信息如下：</span>
				</br>
				${form.tip!}
				${form.tipDesc!}
			</div>
			</#if>
		</div>
	</div>
</div>
