//行动内容  PUN_ACTS 

var CUR_PUN_ACTS = [];

function showPunActSelect(actType)
{
	CUR_PUN_ACTS = [];
//	var actSelect = $("select[id='punAct']");
//	actSelect.empty();
	for (var key in PUN_ACTS) {
		var prefixSTR = "PUNACT_"+actType+"#";
		if (key.indexOf(prefixSTR)==0) {
			var newKey =  key.replace(prefixSTR, "");
			CUR_PUN_ACTS[newKey] = PUN_ACTS[key];
		}
	}
} 



function checkFormValid()
{
	$("#succId").css('display','none');
 	$("#errorId").css('display','');

	if(!$("input[id='actName']").val() || $("select[id='actName']").val() ==""){
		 $("#errorId").html("友情提醒：请填写行动名称");
		 $("input[name='actName']").focus();
		 return false;
	} 	
 	
	if(!$("select[id='actType']").val() || $("select[id='actType']").val() ==""){
		 $("#errorId").html("友情提醒：请选择行动类型");
		 $("select[id='actType']").focus();
		 return false;
	}
	
	if(!$("select[id='punAct']").val() || $("select[id='punAct']").val() ==""){
		 $("#errorId").html("友情提醒：请选择行动内容");
		 $("select[id='punAct']").focus();
		 return false;
	}	
	//TODO 
	if(!$("input[id='punParm']").val() || $("select[id='punParm']").val() ==""){
		 $("#errorId").html("友情提醒：请填写行动参数");
		 $("input[name='punParm']").focus();
		 return false;
	} 	
	
	if($("input[type='radio'][name='status']:checked").length == 0){
		 $("#errorId").html("友情提醒：请选择状态");
		 return false;
	} 		
	
	//行动名称长度限制  50
	var nameLength = $.trim($("input[id='actName']").val()).length;
	if(nameLength >50 || nameLength<=0){
		 $("#errorId").html("友情提醒：'行动名称'长度在50之内，且不能都为空格");
		 $("input[name='actName']").focus();
		 return false;
	} 		
	
	var punActObj = $("select[id='punAct']");
	var punParmObj = $("input[id='punParm']");
	if (punActObj) {
		var punActVal = punActObj.val();
		if (punActVal=="GA03") {
			// N C F
			if (punParmObj.val()!='N' &&punParmObj.val()!='C'&& punParmObj.val()!='F') 
			{
				 $("#errorId").html("友情提醒：'行动参数'只能输入N 、C或F");
				 $("input[id='punParm']").focus();
				 return false;
			}
		}else if (punActVal=="GA04"||punActVal=="GA05") {
			var reg = new RegExp(/^[0-9]+$/g);
			if (!punParmObj.val().match(reg) || (punParmObj.val() <0 || punParmObj.val() > 100)) {
				 $("#errorId").html("友情提醒：'行动参数'只能为0-100范围内的整数");
				 $("input[id='punParm']").focus();
				 return false;
			}
		}
	}
	
	
	return true;
}

function changeActType(obj)
{
	var actSelect = $("select[id='punAct']");
	actSelect.empty();
	actSelect.append("<option value=''>行动内容选择</option>");
	showPunActSelect(obj.val());
	
	var primaryVal = actSelect.attr("primaryVal");
	for (var k in CUR_PUN_ACTS) {
		//选中  primaryVal
		var selectHtml = (k == primaryVal ? "selected='selected'":"");
		actSelect.append("<option value='"+k+"' "+selectHtml+" >"+CUR_PUN_ACTS[k]+"</option>");
	}
	
}

jQuery(function($) {
	

	
	$("select[id='actType']").bind("change", function() {
		
		changeActType($(this));
		
	});

	//新增时，默认选中有效
	if ($("#autoFlag") && !($("#actId").length)) 
	{
		$("#status0").attr("checked","checked");
	}
	
	//check form before sumbit,bind form
	$("form[id=theForm]").bind("submit",function()
	{
	    return checkFormValid();
	});
	
	$("#sumitBtn").bind("click",function()
	{
		$("form[id=theForm]").submit();
	});	

	
	changeActType($("select[id='actType']"));
	
});