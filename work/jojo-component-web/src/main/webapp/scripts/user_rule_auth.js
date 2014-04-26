
jQuery(function($) {
	$('#popRuleItemMenu').click(function(){
		var $popObj = $('#popRuleItem');
		
        var docEle = document.documentElement || document.body;
        var objLeft = $(window).scrollLeft() + (docEle.clientWidth - $popObj.width()) / 2 + "px";
        var objTop = $(window).scrollTop() + (docEle.clientHeight - $popObj.height()) / 2 + "px";
        $popObj.css({
            'left': objLeft,
            'top': objTop,
            'z-index': 101
        });

        $popObj.show();
       
	});
	
	$('#tipClose,#tipCancelBtn').click(function(){
		$('#popRuleItem').hide();
	});
	
	$('#tipOkBtn').click(function(){
		var checkedRuleItems = $("input[name='ruleItem']:checked");
		if (checkedRuleItems.length == 0) {
			alert("没有规则被选中");
			return false;
		}
		var checkedRuleItemStr = "";
		var ruleIds="";
		checkedRuleItems.each(function(){
			ruleIds+=$(this).val()+";";
		});
		$('#popRuleItem').hide();
		addUserRule(ruleIds);
	});
	
	var delRuleTag = document.delRuleTag = function(tagId){
		var checkedRuleItems = $("input[name='checkedRuleId']:checked");
		if (checkedRuleItems.length == 0) {
			alert("没有规则被选中");
			return false;
		}
		var ruleIds="";
		checkedRuleItems.each(function(){
			ruleIds+=$(this).val()+";";
		});
		$("#checkRuleIds").attr("value",ruleIds);
		//alert($("#checkRuleIds").val());
		$("form[name='registForm']").submit();
		
	}
	
	$('#queryRuleBtn').click(function(){
		var ruleId=$("#ruleId").val();
		var ruleName=$("#ruleName").val();
		var str="";
		$.getJSON("/scppun/userRuleAuth/list", { ruleId: ruleId, ruleName: ruleName }, function(data){
			if(data.length>0){
				$.each(data, function(i,item){
					 str+="<tr>";
					 str+="<td><input type='checkbox' name='ruleItem' id="+item.ruleId+" value="+item.ruleId+" /></td> ";
					 str+="<td>"+item.ruleId+"</td> ";
					 str+="<td>";
					 for(var v=0;v<item.ruleDesc.length;v++){
						 str+="<label title='"+item.ruleDesc[v]+"'>"+item.ruleDesc[v]+"</label></br>";
					 }
					 str+="</td>";
					 str+="</tr>";
				 });
				 $("#btn-nav").show();
			}else{
				 $("#btn-nav").hide();
			}
			 $("#popRuleTags").html(str);
			});
	});

});
function addUserRule(ruleIds){
	var userId=$("#userId").val();
	$.get("/scppun/userRuleAuth/add", {
		ruleIds : ruleIds,
		userId:userId
	},function(data){
		window.location.href ="/scppun/userRuleAuth/detail?id="+userId;
	   });
	
}