jQuery(function($) {
	$("#menuLevel").change(function(){
		var menuLevel = $("#menuLevel").val();
		if(menuLevel==="2"){
			$("#parent-menu").show();
		}else{
			$("#parent-menu").hide();
		}
	});
	
	$("#menuAddSumitBtn").click(function() {
		if ($.trim($("#menuName").val()).length == 0) {
			alert("请填写菜单名称！");
			$("#menuName").focus();
			return;
		}

		if ($("#menuLevel").val().length == 0) {
			alert("请选择菜单级别！");
			return;
		}
		
		if ($("#parentMenuId").val().length == 0 && $("#menuLevel").val() == "2") {
			alert("二级菜单请选择父级菜单！");
			return;
		}

		if ($("#menuLink").val().length == 0) {
			alert("请填写菜单链接！");
			return;
		}
		
		// 提交表单
		$("form[name='menuAddForm']").submit();
	});
});