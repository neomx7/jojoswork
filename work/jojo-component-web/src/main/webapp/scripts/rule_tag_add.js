var RULE_PARAM_COMBINE = "~!~";
jQuery(function($) {
	var index = $("#addRuleTagItemBtn").data("index");
	$("#addRuleTagItemBtn").click(function(){
		index = index + 1;
		var innerHtml = "";
		
		var hasTimeInterval = false;
		var hasCount = false;
		var hiddenRisk = false;
		$("select[name^='itemType']").each(function(){
			if($(this).val() == "0"){
				hiddenRisk = true;
			}else if($(this).val() == "1"){
				hasTimeInterval = true;
				hiddenRisk = true;
			}else if($(this).val() == "2"){
				hasCount = true;
				hiddenRisk = true;
			}else if($(this).val() == "3"){
				hiddenRisk = true;
			}
		});
		
		innerHtml += "<tr>";
		innerHtml += "<td><label>　　　　</label>&nbsp;</td>";
		innerHtml += "<td>";
		innerHtml += "<select onchange='changeItemField(\"" + index + "\")' id='itemType" + index + "' name='itemType" + index + "'>";
		for(var itemTypeKey in RULE_ITEM_TYPE){
			if((hasTimeInterval && itemTypeKey == "1") || (hasCount && itemTypeKey == "2") || (hiddenRisk && itemTypeKey == "3")){
				continue;
			}
			innerHtml += "<option value='" + itemTypeKey + "'>" + RULE_ITEM_TYPE[itemTypeKey] + "</option>";
		}
		innerHtml += "</select>";
		innerHtml += "</td>";
		
		innerHtml += "<td>";
		innerHtml += "<select id='itemField" + index + "' name='itemField" + index + "'>";
		for(var filedScopeKey in RULE_ITEM_FILED_SCOPE){
			innerHtml += "<option value='" + filedScopeKey + "'>" + RULE_ITEM_FILED_SCOPE[filedScopeKey] + "</option>";
		}
		innerHtml += "</select>";
		innerHtml += "</td>";
		innerHtml += "<td><span class='link' onclick='delRuleTagItem(" + index + ");' id='ruleTagItemDel" + index + "'>删除</span></td>";
	
		innerHtml += "</tr>";
		
		$(this).parent().parent().before(innerHtml);
	});
	
	var changeItemField = document.changeItemField = function(index){
		var hideAddBtn = false;
		$("select[name^='itemType']").each(function(){
			if($(this).val() == "3"){
				hideAddBtn = true;
			}
		});
		
		if(hideAddBtn){
			$("#addRuleTagItemBtn").hide();
			$("#limitConditionField").hide();
		}else{
			$("#addRuleTagItemBtn").show();
			$("#limitConditionField").show();
		}
		
		var itemType = $("#itemType" + index).val();
		var innerHtml = "";
		if(itemType == "0"){
			for(var filedScopeKey in RULE_ITEM_FILED_SCOPE){
			innerHtml += "<option value='" + filedScopeKey + "'>" + RULE_ITEM_FILED_SCOPE[filedScopeKey] + "</option>";
			}
		}else if(itemType == "1"){
			for(var timeIntervalKey in RULE_ITEM_FILED_TIME_INTERVAL){
			innerHtml += "<option value='" + timeIntervalKey + "'>" + RULE_ITEM_FILED_TIME_INTERVAL[timeIntervalKey] + "</option>";
			}
		}else if(itemType == "2"){
			for(var countKey in RULE_ITEM_FILED_COUNT){
			innerHtml += "<option value='" + countKey + "'>" + RULE_ITEM_FILED_COUNT[countKey] + "</option>";
			}
		}else if(itemType == "3"){
			for(var riskKey in RULE_ITEM_FILED_RISK){
			innerHtml += "<option value='" + riskKey + "'>" + RULE_ITEM_FILED_RISK[riskKey] + "</option>";
			}
		}
		$("#itemField" + index).html(innerHtml);
	}
	
	var delRuleTagItem = document.delRuleTagItem = function(index){
		$("#itemType" + index).parent().parent().remove();
	}
	
	$('#ruleTagAddBtn').click(function(){
		if($.trim($("#tagName").val()).length == 0){
			alert("请填写标签名称！");
			$("#tagName").focus();
			return;
		}
		
		var innerHtml = "";
		var ruleTagItems = "";
		var ruleTagItemsInvalid = false;
		$("select[name^='itemType']").each(function(){
			var index = $(this).attr("name").replace("itemType" , "");
			var ruleTagItem = $(this).val() + RULE_PARAM_COMBINE + $("#itemField" + index).val();
			if(ruleTagItems.indexOf(ruleTagItem) >= 0 || ruleTagItems.indexOf(ruleTagItem.replace("distinct_" , "")) >= 0){
				alert("规则元素重复，请修改！");
				ruleTagItemsInvalid = true;
				return false;
			}
			ruleTagItems += ruleTagItem;
			ruleTagItem = ruleTagItem + RULE_PARAM_COMBINE + $("#itemField" + index).find("option:selected").text();
			innerHtml += "<input type='hidden' name='ruleTagItem' value='" + ruleTagItem + "' />";
		});
		
		if(ruleTagItemsInvalid){
			return false;
		}
		
		$("#ruleTagItemHidden").html(innerHtml);
		
		// 提交表单
		$("form[name='ruleAddForm']").submit();
	});
});