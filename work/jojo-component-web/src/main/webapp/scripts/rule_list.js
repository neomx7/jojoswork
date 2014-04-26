var activationRule = function(id,ruleId) {
	if (confirm("激活表示将该规则加入到规则引擎中执行，确认激活")) {
		$.post("/scppun/rule/status/edit", {
			id : id,
			ruleId : ruleId,
			status : "2"
		}, function(data) {
			if (data == "1") {
				window.location.href = location.protocol + "//" + window.location.host + window.location.pathname;
				// window.location.reload();
			}
		}, "text");
	}
};

var invalidRule = function(id,ruleId) {
	if (confirm("失效表示将该规则从到规则引擎执行中移除，确认失效")) {
		$.post("/scppun/rule/status/edit", {
			id : id,
			ruleId : ruleId,
			status : "3"
		}, function(data) {
			if (data == "1") {
				window.location.href = location.protocol + "//" + window.location.host + window.location.pathname;
				// window.location.reload();
			}
		}, "text");
	}
};

var deleteRule = function(id,ruleId) {
	if (confirm("删除后改规则将不再显示，确认删除")) {
		$.post("/scppun/rule/status/edit", {
			id : id,
			ruleId : ruleId,
			status : "4"
		}, function(data) {
			if (data == "1") {
				window.location.reload();
			}
		}, "text");
	}
};

var changeSalience = function(id,ruleId){
	var salience = $("#" + id).data("salience");
	var newSalience = $.trim($("#" + id).val());
	if(salience != newSalience){
		if (confirm("您确认将该风控规则优先级设置为：" + newSalience)) {
			$.post("/scppun/rule/salience/edit", {
				id : id,
				ruleId : ruleId,
				salience : newSalience
			}, function(data) {
				if (data == "1") {
					window.location.reload();
				}
			}, "text");
		}
	}
};

var activationGamRule = function(id,ruleId) {
	if (confirm("激活表示将该规则加入到规则引擎中执行，确认激活")) {
		$.post("/scppun/rule/status/editGamRule", {
			id : id,
			ruleId : ruleId,
			status : "2"
		}, function(data) {
			if (data == "1") {
				window.location.href = location.protocol + "//" + window.location.host + window.location.pathname;
			}
		}, "text");
	}
};

var invalidGamRule = function(id,ruleId) {
	if (confirm("失效表示将该规则从到规则引擎执行中移除，确认失效")) {
		$.post("/scppun/rule/status/editGamRule", {
			id : id,
			ruleId : ruleId,
			status : "3"
		}, function(data) {
			if (data == "1") {
				window.location.href = location.protocol + "//" + window.location.host + window.location.pathname;
			}
		}, "text");
	}
};

var deleteGamRule = function(id,ruleId) {
	if (confirm("删除后改规则将不再显示，确认删除")) {
		$.post("/scppun/rule/status/editGamRule", {
			id : id,
			ruleId : ruleId,
			status : "4"
		}, function(data) {
			if (data == "1") {
				window.location.reload();
			}
		}, "text");
	}
};

var changeGamSalience = function(id,ruleId){
	var salience = $("#" + id).data("salience");
	var newSalience = $.trim($("#" + ruleId).val());
	if(salience != newSalience){
		if (confirm("您确认将该风控规则优先级设置为：" + newSalience)) {
			$.post("/scppun/rule/salience/editGamRule", {
				id : id,
				ruleId : ruleId,
				salience : newSalience
			}, function(data) {
				if (data == "1") {
					window.location.reload();
				}
			}, "text");
		}
	}
};