var activationRuleElement = function(ruleId) {
	if (confirm("激活表示将该规则标签加入到规则添加标签列表，激活后不可修改，确认激活")) {
		$.post("/scppun/rule/tag/status/edit", {
			id : ruleId,
			status : "2"
		}, function(data) {
			if (data == "1") {
				window.location.reload();
			}
		}, "text");
	}
}

var invalidRuleElement = function(ruleId) {
	if (confirm("失效表示将该规则标签删除，修改后才能被激活，确认失效")) {
		$.post("/scppun/rule/tag/status/edit", {
			id : ruleId,
			status : "3"
		}, function(data) {
			if (data == "1") {
				window.location.reload();
			}
		}, "text");
	}
}