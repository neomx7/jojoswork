function validate(){
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	if(startTime!=""&&endTime!=""&&startTime>endTime){
		alert("开始时间不能大于结束时间！");
		return false;
	}
	return true;
	
}


function resetForm(){
	var obj = null;
	var length=document.forms[0].elements.length;
    for (var i = 0; i < length - 2; i++) {
        obj = document.forms[0].elements[i];
        if (obj.tagName == "INPUT" && obj.type == "text") {
            obj.setAttribute("value", "");
        }
        if (obj.tagName == "INPUT" && obj.type == "checkbox") {
            obj.setAttribute("checked", false);
        }
        if (obj.tagName == "SELECT") {
            obj.options[0].selected = true;
        }
    }
    return false;
}

var today = new Date();
//获取当前日(1-31)
var day = today.getDate();
//显示月份比实际月份小1,所以要加1 
var month = today.getMonth() + 1;
//获取完整的年份(4位,1970-????)  
var year  = today.getFullYear();
//数字<10，实际显示为，如5，要改成05   
month = month<10?"0"+month:month; 
day = day<10?"0"+day:day;     
var currDate  = year + "-" + month + "-" + day; 

function resetDate(objNameStr) {
	if (objNameStr != "") {
		var objNameArr = objNameStr.split(",");
		for (var i = 0; i < objNameArr.length; i++) {
			$(objNameArr[i]).val(currDate);
		}
	}
}

