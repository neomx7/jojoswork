jQuery(function($) {
	$(".menuParentId").each(function() {
		$(this).change(function() {
			var childMenus = $("input[id^='menu-" + $(this).val() + "']");
			if ($(this).attr("checked")) {
				childMenus.each(function() {
					$(this).attr("checked", true);
				});
			} else {
				childMenus.each(function() {
					$(this).attr("checked", false);
				});
			}
		});
	});

	$(".menuIds").each(function() {
		$(this).change(function() {
			var parentId = $(this).attr("data-parentId");
			if ($(this).attr("checked")) {
				$("#menu-parent-" + parentId + "").attr("checked", true);
			} else {
				if($("input[id^='menu-" + parentId + "']:checked").length == 0){
					$("#menu-parent-" + parentId + "").attr("checked", false);
				}
			}
		});
	});

	$("#roleAddSumitBtn").click(function() {
		if ($.trim($("#roleName").val()).length == 0) {
			alert("请填写角色名称！");
			$("#roleName").focus();
			return;
		}
		
		if ($("input[name='menuIds']:checked").length == 0) {
			alert("请至少选择一种角色权限！");
			return;
		}

		// 提交表单
		$("form[name='menuAddForm']").submit();
	});
});