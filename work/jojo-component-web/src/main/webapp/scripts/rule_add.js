
var RULE_PARAM_COMBINE = "~!~";

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
		var checkedRuleItemStr = "";
		checkedRuleItems.each(function(){
			checkedRuleItemStr += $(this).val() + ";";
		});
		
		var ruleTagIdStr = "";
		$("input[name='ruleTagId']:hidden").each(function(){
			var ruleTagIdVal = $(this).val();
			if(checkedRuleItemStr.indexOf(ruleTagIdVal) >= 0){
				ruleTagIdStr += ruleTagIdVal + ";";
			}else{
				$("#tr-" + ruleTagIdVal).remove();
			}
		});
		
		var ruleItemsHtml = "";
		checkedRuleItems.each(function(){
			var ruleTagId = $(this).val();
			if(ruleTagIdStr.indexOf(ruleTagId) >= 0){
				return true;
			}
			ruleItemsHtml += "<tr id='tr-" + ruleTagId + "'><td><label>　　　　</label></td>";
			// add rule tag desc
			ruleItemsHtml += "<td style='overflow: hidden;' title=\"" + RULE_TAGS_LIMIT_DESCS[ruleTagId] + "\">";
			var tagDesc = $(this).next().text();
			ruleItemsHtml += tagDesc;
			ruleItemsHtml += "</td>";
			
			// add operators
			ruleItemsHtml += "<td>";
			ruleItemsHtml += "<select  id='operator" + ruleTagId + "' name='operator" + ruleTagId + "' >";
			for(var operatorKey in OPERATORS){
				ruleItemsHtml += "<option value='" + operatorKey + "'>" + OPERATORS[operatorKey] + "</option>";
			}
			ruleItemsHtml += "</select>";
			ruleItemsHtml += "</td>";
			
			// add value type
			ruleItemsHtml += "<td>";
			ruleItemsHtml += "<select onchange='changeValueSpan(\"" + ruleTagId + "\");' id='valueType" + ruleTagId + "' name='valueType" + ruleTagId + "' >";
			ruleItemsHtml += "<option value='0'>数值</option>";
			ruleItemsHtml += "<option value='1'>数据项</option>";
			ruleItemsHtml += "<option value='2'>省份</option>";
			ruleItemsHtml += "<option value='3'>百分数</option>";
			ruleItemsHtml += "</select>";
			ruleItemsHtml += "</td>";
			
			ruleItemsHtml += "<td>";
			ruleItemsHtml += "<span id='valueSpan" + ruleTagId + "'>";
			var amountTip = tagDesc.indexOf("金额") > 0 && tagDesc.indexOf("笔数") < 0 ? "【金额】单位为分" : "";
			ruleItemsHtml += "<input title='" + amountTip + "' placeholder='" + amountTip + "' id='value" + ruleTagId + "' name='value" + ruleTagId + "' type='text' value='' />";
			ruleItemsHtml += "</span>  <span title='删除规则标签' class='link' onclick='delRuleTag(\"" + ruleTagId + "\")'> x </span>";
			
			ruleItemsHtml += "<input type='hidden' name='ruleTagId' value='" + ruleTagId + "' />";
			ruleItemsHtml += "</td>";
			ruleItemsHtml += "</tr>";
		});
		
		$("#ruleItems").append(ruleItemsHtml);
		
		$('#popRuleItem').hide();
	});
	
	var delRuleTag = document.delRuleTag = function(tagId){
		$("#tr-" + tagId).remove();
		$("#" + tagId).attr("checked", false);;
	};
	
	
	var changeValueSpan = document.changeValueSpan = function(tagId){
		var valueType = $("#valueType" + tagId + "").val();
		if(valueType == "0"){
			 $("#valueSpan" + tagId + "").html("<input id='value" + tagId + "' name='value" + tagId + "' type='text' value='' />");
		}else if(valueType == "1"){
			 var selectRuleTags = "<select id='value" + tagId + "' name='value" + tagId + "' >";
			 selectRuleTags += "<option value=''>数据项选择</option>";
		     for(var ruleTagKey in RULE_DATA_TAGS){
		    	 selectRuleTags += "<option value='" + ruleTagKey + "'>" + RULE_DATA_TAGS[ruleTagKey] + "</option>";
		     }
		     selectRuleTags += "</select>"; 
			 $("#valueSpan" + tagId + "").html(selectRuleTags);
		}else if (valueType == "3"){
			$("#valueSpan" + tagId + "").html("<input id='value" + tagId + "' name='value" + tagId + "' type='text' value='' />%");
		}else{
			var selectProvs = "<select id='value" + tagId + "' name='value" + tagId + "' >";
			selectProvs += "<option value=''>省份选择</option>";
		     for(var provKey in PROVS){
		    	 selectProvs += "<option value='" + provKey + "'>" + PROVS[provKey] + "</option>";
		     }
		     selectProvs += "</select>"; 
			 $("#valueSpan" + tagId + "").html(selectProvs);
		}
	};
	
	var isInt = document.isInt = function(intVal){
		return parseInt(intVal) == intVal;
	};
	
	$('#ruleAddBtn').click(function(){
		// 验证规则名称
		if($.trim($("#ruleName").val()).length == 0){
			alert("请填写规则名称！");
			$("#ruleName").focus();
			return;
		}
		
		if($.trim($("#ruleType").val()).length == 0){
			alert("请选择规则类型！");
			return;
		}
		
		if($("input[name='tradeType']").length > 0 && $("input[name='tradeType']:checked").length == 0){
			alert("请选择交易类型！");
			return;
		}
		
		// 处理数据项
		$("#ruleParams").html("");
		var ruleParams = "";
		var ruleTagIds = $("input[name='ruleTagId']");
		if(ruleTagIds.length == 0){
			alert("请添加数据项！");
			return;
		}
		var ruleParamsInvalid = false;
		ruleTagIds.each(function(){
			var ruleTagId = $(this).val();
			var ruleParam = ruleTagId;
			if($("#operator" + ruleTagId).val().length == 0){
				alert("请选择运算符！");
				ruleParamsInvalid = true;
				return false;
			}
			if($("#operator" + ruleTagId).val().length > 0 && $.trim($("#value" + ruleTagId).val()).length == 0){
				alert("请填写运算符对应的数据（项）！");
				$("#value" + ruleTagId).focus();
				ruleParamsInvalid = true;
				return false;
			}else if($("#operator" + ruleTagId).val().length == 0 && $.trim($("#value" + ruleTagId).val()).length > 0){
				alert("请选择数据（项）对应的运算符！");
				ruleParamsInvalid = true;
				return false;
			}else{
				if($("#operator" + ruleTagId).val() == "between"){
					var betweenVals = $.trim($("#value" + ruleTagId).val()).split("-");
					if(betweenVals.length != 2 || !isInt(betweenVals[0]) || !isInt(betweenVals[1]) || parseInt(betweenVals[0]) >= parseInt(betweenVals[1])){
						alert("请填写正确的运算符对应的数据，如：minNumber-maxNumber！");
						$("#value" + ruleTagId).focus();
						ruleParamsInvalid = true;
					}
				}else if($("#valueType" + ruleTagId).val() == "0" && ($("#operator" + ruleTagId).val() == "gt" || $("#operator" + ruleTagId).val() == "gte" || $("#operator" + ruleTagId).val() == "lt" || $("#operator" + ruleTagId).val() == "lt") && !isInt($.trim($("#value" + ruleTagId).val()))){
					alert("请填写正确的运算符对应的数据（整数）");
					$("#value" + ruleTagId).focus();
					ruleParamsInvalid = true;
				}else if(($("#valueType" + ruleTagId).val() == "0" || $("#valueType" + ruleTagId).val() == "3") && ($("#operator" + ruleTagId).val() == "gt" || $("#operator" + ruleTagId).val() == "gte" || $("#operator" + ruleTagId).val() == "lt" || $("#operator" + ruleTagId).val() == "lt") && !isInt($.trim($("#value" + ruleTagId).val()))){
					alert("请填写正确的运算符对应的数据（整数）");
					$("#value" + ruleTagId).focus();
					ruleParamsInvalid = true;
				}
				ruleParam += RULE_PARAM_COMBINE + $("#operator" + ruleTagId).val();
				ruleParam += RULE_PARAM_COMBINE + $("#valueType" + ruleTagId).val();
				ruleParam += RULE_PARAM_COMBINE + $.trim($("#value" + ruleTagId).val()).replace(/,/g,";").replace(/，/g,";");
				
				ruleParams += "<input type='hidden' name='ruleParam' value='" + ruleParam + "' />";
			}
		});
		if(ruleParamsInvalid){
			return;
		}
		$("#ruleParams").html(ruleParams);
		
		// 验证处罚动作
		var punTypeCodes = $("input[name='punTypeCode']:checked");
		if(punTypeCodes.length==0){
			alert("请选择处罚动作！");
			return;
		}
		
		// 提交表单
		$("form[name='ruleAddForm']").submit();
	});
	
	// 修改了规则的执行方式
	$("input[name='ruleWay']").click(function(){
		var ruleWay = $("input[name='ruleWay']:checked").val();
		// 批处理规则
		if ("1" == ruleWay)
		{
			$("input[name='requestType']").attr("disabled" +
					"", "false");
			$("input[name='tradeType']").attr("disabled", "false");
		}
		// 准实时规则
		else if ("2" == ruleWay)
	    {
			$("input[name='requestType']").removeAttr("disabled");
			$("input[name='tradeType']").removeAttr("disabled");
	    }
	});
	
	$('#gamRuleAddBtn').click(function(){
		// 验证规则名称
		if($.trim($("#ruleName").val()).length == 0){
			alert("请填写规则名称！");
			$("#ruleName").focus();
			return;
		}
		
		if($.trim($("#ruleType").val()).length == 0){
			alert("请选择规则类型！");
			return;
		}
		
		if($("input[name='tradeType']").length > 0 && $("input[name='tradeType']:checked").length == 0){
			alert("请选择交易类型！");
			return;
		}
		
		// 处理数据项
		$("#ruleParams").html("");
		var ruleParams = "";
		var ruleParamIds = $("input[name='ruleParamId']");
		var ruleTagIds = $("input[name='ruleTagId']");
		if(ruleTagIds.length == 0){
			alert("请添加数据项！");
			return;
		}
		var ruleParamsInvalid = false;
		for (var i = 0; i < ruleParamIds.length; i++)
		{
//		ruleTagIds.each(function(){
			var ruleParamId = $(ruleParamIds[i]).val();
			var ruleTagId = $(ruleTagIds[i]).val();
			var ruleParam = ruleParamId + RULE_PARAM_COMBINE + ruleTagId;
			if($("#operator" + ruleTagId).val().length == 0){
				alert("请选择运算符！");
				ruleParamsInvalid = true;
				return false;
			}
			if($("#operator" + ruleTagId).val().length > 0 && $.trim($("#value" + ruleTagId).val()).length == 0){
				alert("请填写运算符对应的数据（项）！");
				$("#value" + ruleTagId).focus();
				ruleParamsInvalid = true;
				return false;
			}else if($("#operator" + ruleTagId).val().length == 0 && $.trim($("#value" + ruleTagId).val()).length > 0){
				alert("请选择数据（项）对应的运算符！");
				ruleParamsInvalid = true;
				return false;
			}else{
				if($("#operator" + ruleTagId).val() == "between"){
					var betweenVals = $.trim($("#value" + ruleTagId).val()).split("-");
					if(betweenVals.length != 2 || !isInt(betweenVals[0]) || !isInt(betweenVals[1]) || parseInt(betweenVals[0]) >= parseInt(betweenVals[1])){
						alert("请填写正确的运算符对应的数据，如：minNumber-maxNumber！");
						$("#value" + ruleTagId).focus();
						ruleParamsInvalid = true;
					}
				}else if($("#valueType" + ruleTagId).val() == "0" && ($("#operator" + ruleTagId).val() == "gt" || $("#operator" + ruleTagId).val() == "gte" || $("#operator" + ruleTagId).val() == "lt" || $("#operator" + ruleTagId).val() == "lt") && !isInt($.trim($("#value" + ruleTagId).val()))){
					alert("请填写正确的运算符对应的数据（整数）");
					$("#value" + ruleTagId).focus();
					ruleParamsInvalid = true;
				}else if(($("#valueType" + ruleTagId).val() == "0" || $("#valueType" + ruleTagId).val() == "3") && ($("#operator" + ruleTagId).val() == "gt" || $("#operator" + ruleTagId).val() == "gte" || $("#operator" + ruleTagId).val() == "lt" || $("#operator" + ruleTagId).val() == "lt") && !isInt($.trim($("#value" + ruleTagId).val()))){
					alert("请填写正确的运算符对应的数据（整数）");
					$("#value" + ruleTagId).focus();
					ruleParamsInvalid = true;
				}
				ruleParam += RULE_PARAM_COMBINE + $("#operator" + ruleTagId).val();
				ruleParam += RULE_PARAM_COMBINE + $("#valueType" + ruleTagId).val();
				ruleParam += RULE_PARAM_COMBINE + $.trim($("#value" + ruleTagId).val()).replace(/,/g,";").replace(/，/g,";");
				
				ruleParams += "<input type='hidden' name='ruleParam' value='" + ruleParam + "' />";
			}
//		});
		}
		if(ruleParamsInvalid){
			return;
		}
		$("#ruleParams").html(ruleParams);
		
		// 验证处罚动作
		var punTypeCodes = $("input[name='punTypeCode']:checked");
		if(punTypeCodes.length==0){
			alert("请选择处罚动作！");
			return;
		}
		
		// 提交表单
		$("form[name='ruleAddForm']").submit();
	});
});