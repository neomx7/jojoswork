function showResult(action, tid, rtData) {
	if (rtData.result == "true") {
		if (action == "redo") {
			showMsg("重做成功！");
		} else if (action == "cancel") {
			showMsg("取消成功！");
		} else if (action == "delete") {
			showMsg("删除成功！");
		}
		refresh(tid);
	} else {
		showFail(action, rtData);
	}
}

function showMsg(msg) {
	alert(msg);
}

function showFail(action, rtData) {
//	msg = "操作失败, 失败原因: " + rtData.failReason + "\r\n" + rtData.failException;
//	showMsg(msg);
	$("#operFailDiv span[name='reason']").html(rtData.failReason);
	$("#operFailDiv div[name='detail'] pre").html(rtData.failException);
	$("#operFailDiv").css({left: ($(window).width() - $("#operFailDiv").outerWidth())/2, top: ($(window).height() - $("#operFailDiv").outerHeight())/3});
	$("#operFailDiv").show();
}

function doAction(action, tid) {
	$.post("/scppun/downcenter/" + action, {"tid":tid}, function(data) {
		showResult(action, tid, data);
	});
}

function refresh(tid) {
	//OPTIMIZE 刷新该任务状态
	window.location.reload();
}

function download(tid, filename) {
	$("#downform input[name='tid']").val(tid);
	$("#downform input[name='filename']").val(filename);
	$("#downform").submit();
}

$(function(){
	$("a[tid]").mouseover(function(e) {
		tid = $(this).attr("tid");
		$("#" + tid + "-ediv").css({left: e.pageX + 5, top: e.pageY + 5}).show();
	}).mouseout(function() {
		tid = $(this).attr("tid");
		$("#" + tid + "-ediv").hide();
	});
	
	$("#operFailDiv button[name='close']").click(function() {
		$("#operFailDiv div[name='detail']").hide();
		$("#operFailDiv").hide();
	});
	
	$("#operFailDiv button[name='detail']").click(function() {
		$("#operFailDiv div[name='detail']").toggle();
	});
});