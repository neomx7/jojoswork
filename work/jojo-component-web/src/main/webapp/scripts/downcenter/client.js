var confirmCount = 10000;

$(function() {
	$("a[type='downLink']").click(function() {
		//先判断记录数，给出提示
		total = $("#pagination span.h3:first").text();
		if (total) {
			tt = parseInt(total);
			if (tt && tt > confirmCount) { //如果记录数大于confirmCount条，给出确认提示
				if (!confirm("记录总数【" + tt + "】过大，生成文件可能较慢，你确定要继续吗？")) {
					return false;
				}
			}
		}
		//提交任务
		href = $(this).attr("href");
		$.get(href, function(data) {
			if (data.result == "true") {
				alert("文件【" + data.filename + "】正在生成中,\r\n请稍后从'下载中心'下载。")
			} else {
				alert("无法生成文件: " + data.failReason);
			}
		});
		return false;
	});
});