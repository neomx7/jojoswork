
var i = 0;

jQuery(function($) {

	$("#popRuleItemMenu"+i).click(function() {
		showObjDiv(++i,this);
	});
	
	$("#submitCheck").click(function () {
		 //组装itemIds
		 getItemIds();
	});
});

function changeSelect(i){
	 var itemType = $("#itemType"+i).val();
	 itemChange(itemType,i);
}

function showObjDiv(i,menu) {
	$("#tr0").clone(true).attr("id", "tr" + i).appendTo("#tblData"); //clone tr 并重新给定ID,装到table 
	var itemTypeVal=$("#itemType0").find("option:selected").val();
	
	$("#tr" + i + " td").each(function() {//循环克隆的新行里面的td 
		$(this).next().attr("id", "td" + i);
		
		$(this).find("#popRuleItemMenu0").attr("id", "popRuleItemMenu" + i) ;
		$(this).find("#itemType0").attr("id", "itemType" + i).attr("name", "itemType" + i).attr("onchange", "changeSelect("+i+")"); 
		$(this).find("#ruleObjs0").attr("id", "ruleObjs" + i).attr("name", "ruleObjs" + i).attr("onchange", "changeObjs("+i+")");; 
		$(this).next().next().remove();
	}); 
	$("#tr"+i).append("<span class='link' onclick='delRuleTagItem(" + i + ");' id='ruleTagItemDel" + i + "'> - 删除</span>");
	if (i>0) {
		$("#limitDiv").attr("class", "");
	}
	
	$("#itemType"+i+" option[value="+itemTypeVal+"]").attr("selected", "selected");  

}

function itemChange(item,i) {
	var str = [];
	if (item == "0") {
		str.push('<select id="ruleObjs'+i+'" name="ruleObjs'+i+'" onchange="changeObjs('+i+')">');
		for ( var operatorKey in RULE_OBJ_TAGS) {
			str.push('<option value=" ' + operatorKey + ' ">'
					+ RULE_OBJ_TAGS[operatorKey] + '</option>');
		}
		str.push('</select>');
		
	}
	if (item == "1") {
		str.push('<select id="timeInterval'+i+'" name="timeInterval'+i+'" onchange="changeObjs('+i+')">');
		for ( var operatorKey in RULE_TIME_TAGS) {
			str.push('<option value=" ' + operatorKey + ' ">'
					+ RULE_TIME_TAGS[operatorKey] + '</option>');
		}
		str.push('</select>');
	}
	if (item == "2") {
		str.push('<select id="numercialElement'+i+'" name="numercialElement'+i+'" onchange="changeObjs('+i+')">');
		for ( var operatorKey in RULE_COUNT_TAGS) {
			//默认
			if(RULE_COUNT_TAGS[operatorKey] =="【金额】（单笔）"){
				$("#specialOperatordiv").attr("class", "");
				$("#specialOperatorValue").attr("placeholder", "金额，单位为分");
			}
			if(RULE_COUNT_TAGS[operatorKey] =="【失败返回代码】"){
				$("#specialOperatordiv").attr("class", "");
				$("#specialOperatorValue").attr("placeholder", "多个返回码以;相隔");
			}
			str.push('<option value=" ' + operatorKey + ' ">'
					+ RULE_COUNT_TAGS[operatorKey] + '</option>');
		}
		str.push('</select>');
		
	}
	if (item == "3") {
		str.push('<select id="riskObjs'+i+'" name="riskObjs'+i+'" onchange="changeObjs('+i+')">');
		for ( var operatorKey in RULE_RISK_TAGS) {
			
			str.push('<option value=" ' + operatorKey + ' ">'
					+ RULE_RISK_TAGS[operatorKey] + '</option>');
		}
		str.push('</select>');

	}
	if(item!="2"){
		$("#specialOperatordiv").attr("class", "dn");
		$("#specialOperator").find("option:selected").val("");
	}
	$("#td"+i).html(str.join(""));
}

function showItemDiv() {
	showObjDiv("1");
}
function changeObjs(i){
	var objVal = $("#numercialElement"+i).find("option:selected").text();
	//alert(objVal);
	$("#orderField").css('display','none');
	$("#orderField0").attr('checked','true');
	if(i>=2&&objVal=="【金额】（单笔）"){
		$("#specialOperatordiv").attr("class", "");
		$("#specialOperatorValue").attr("placeholder", "金额，单位为分");
	}else if(i>=2&&objVal=="【失败返回代码】"){
		$("#specialOperatordiv").attr("class", "");
		$("#specialOperatorValue").attr("placeholder", "多个返回码以;相隔");
	}else if(i>=2&&objVal=="【付款人是否在乘机人范围】"){
		$("#specialOperatordiv").attr("class", "");
		$("#specialOperatorValue").attr("placeholder", "是：Y 否：N");
	}else if(i>=2&&objVal=="【收发时间差】"){
		$("#specialOperatordiv").attr("class", "");
		$("#specialOperatorValue").attr("placeholder", "数字：单位为小时");
	}else if(i>=2&&objVal=="【金额】（列表）"){
		$("#specialOperatordiv").attr("class", "dn");
		$("#specialOperator").find("option:selected").val("");
		$("#orderField").css('display','');
		$("#orderField1").attr('checked','true');
	}else{
		$("#specialOperatordiv").attr("class", "dn");
		$("#specialOperator").find("option:selected").val("");
		//alert($("#specialOperator").find("option:selected").val());
	}
}

function getItemIds() {
	var 	title=$.trim($("#title").val());
	if(""==title){
		alert("请填写标签名称！");
		return;
	}
	var str = [];
	 for (var t = 0; t<i+1;t++){
		 var ruleObjs =$("select[name=ruleObjs"+t+"] option:selected").val();
		 if(ruleObjs != undefined && ruleObjs!=""){
			 str.push(ruleObjs);
			 str.push(",");
		 }
		 
		 var timeSelect = $("select[name=timeInterval"+t+"] option:selected").val();
		 if(timeSelect != undefined && timeSelect!=""){
			 str.push(timeSelect);
			 str.push(",");
		 }
		 
		 var numSelect = $("select[name=numercialElement"+t+"] option:selected").val();
		 if(numSelect != undefined && numSelect!=""){
			 str.push(numSelect);
			 str.push(",");
		 }
		 
		 var riskSelect = $("select[name=riskObjs"+t+"] option:selected").val();
		 if(riskSelect != undefined && riskSelect!=""){
			 str.push(riskSelect);
			 str.push(",");
		 }
	 }

	if (i>0) {
			if ($("input[name='tradeType']").length > 0 && $("input[name='tradeType']:checked").length == 0) {
				alert("请选择限制交易类型！");
				return;
			}
			if ($("input[name='cardType']").length > 0 && $("input[name='cardType']:checked").length == 0) {
				alert("请选择限制卡类型！");
				return;
			}
			if ($("select[name='requestType']").length > 0 && $("select[name=requestType] option:selected").length == 0) {
				alert("请选择限制事件类型！");
				return;
			}
	}

	$("#itemIds").attr("value", str.join(""));
	// 提交表单
	$("form[name='ruleDataForm']").submit();
}

function delRuleTagItem(index){
	$("#itemType" + index).parent().parent().remove();
	if (index<2) {
		$("#limitDiv").attr("class", "dn");
	}
	
	$("#orderField").css('display','none');
	$("#orderField0").attr('checked','true');
}
function delSpecialOperatordiv(){
	$("#specialOperatordiv").attr("class", "dn");
	$("#specialOperator").find("option:selected").val("");
	
	$("#orderField").css('display','none');
	$("#orderField0").attr('checked','true');
}

function changeSpecial(){
	var objVal = $.trim($("#specialSelect").find("option:selected").text());
	$("#orderField").css('display','none');
	$("#orderField0").attr('checked','true');
	if(objVal=="【金额】（列表）"){
			$("#orderField").css('display','');
			$("#orderField1").attr('checked','true');
	}
}

var activationRule = function(ruleId,title) {
	if (confirm("激活表示该规则标签有效可以用于规则中，激活后不可修改，确认激活")) {
		$.post("/scppun/ruledata/edit", {
			id : ruleId,
			title:title,
			status : "1"
		}, function(data) {
			if (data == "1") {
				window.location.reload();
			}
		});
	}
};

var invalidRule = function(ruleId,title) {
	if (confirm("删除表示将该规则标签从数据库中删除，删除后不能恢复，确认失效")) {
		$.post("/scppun/ruledata/edit", {
			id : ruleId,
			title:title,
			status : "2"
		}, function(data) {
			if (data == "1") {
				window.location.reload();
			}
		});
	}
};