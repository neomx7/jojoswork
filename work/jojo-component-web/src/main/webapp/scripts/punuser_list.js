var activationUser = function(userId) {
	if (confirm("确认激活")) {
		$.post("/scppun/punuser/status/edit", {
			id : userId,
			status : "0"
		}, function(data) {
			if (data == "1") {
				window.location.reload();
			}
		}, "text");
	}
}

var invalidUser = function(userId) {
	if (confirm("确认失效")) {
		$.post("/scppun/punuser/status/edit", {
			id : userId,
			status : "1"
		}, function(data) {
			if (data == "1") {
				window.location.reload();
			}
		}, "text");
	}
}