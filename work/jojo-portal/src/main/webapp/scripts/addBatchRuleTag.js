
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
	 var itemType = $("#itemType"+i).val()
	 itemChange(itemType,i);
}
function showObjDiv(i,menu) {
	$("#tr0").clone(true).attr("id", "tr" + i).appendTo("#tblData"); //clone tr 并重新给定ID,装到table 

	$("#tr" + i + " td").each(function() {//循环克隆的新行里面的td 
		$(this).next().attr("id", "td" + i);
		$(this).find("#popRuleItemMenu0").attr("id", "popRuleItemMenu" + i) ;
		$(this).find("#itemType0").attr("id", "itemType" + i).attr("name", "itemType" + i).attr("onchange", "changeSelect("+i+")"); 
		$(this).find("#ruleObjs0").attr("id", "ruleObjs" + i).attr("name", "ruleObjs" + i); 
		$(this).next().next().remove();
	}); 
	$("#tr"+i).append("<span class='link' onclick='delRuleTagItem(" + i + ");' id='ruleTagItemDel" + i + "'> - 删除</span>");
	if (i>0) {
		$("#limitDiv").attr("class", "");
	}
}

function itemChange(item,i) {
	var str = [];
	if (item == "0") {
		str.push('<select id="ruleObjs'+i+'" name="ruleObjs'+i+'">');
		for ( var operatorKey in RULE_OBJ_TAGS) {
			str.push('<option value=" ' + operatorKey + ' ">'
					+ RULE_OBJ_TAGS[operatorKey] + '</option>');
		}
		str.push('</select>');
	}
	if (item == "1") {
		str.push('<select id="timeInterval'+i+'" name="timeInterval'+i+'">');
		for ( var operatorKey in RULE_TIME_TAGS) {
			str.push('<option value=" ' + operatorKey + ' ">'
					+ RULE_TIME_TAGS[operatorKey] + '</option>');
		}
		str.push('</select>');
	}
	if (item == "2") {
		str.push('<select id="numercialElement'+i+'" name="numercialElement'+i+'">');
		for ( var operatorKey in RULE_COUNT_TAGS) {
			str.push('<option value=" ' + operatorKey + ' ">'
					+ RULE_COUNT_TAGS[operatorKey] + '</option>');
		}
		str.push('</select>');
	}
	if (item == "3") {
		str.push('<select id="riskObjs'+i+'" name="riskObjs'+i+'">');
		for ( var operatorKey in RULE_RISK_TAGS) {
			str.push('<option value=" ' + operatorKey + ' ">'
					+ RULE_RISK_TAGS[operatorKey] + '</option>');
		}
		str.push('</select>');

	}

	$("#td"+i).html(str.join(""));
}

function showItemDiv() {
	showObjDiv("1");
}

function getItemIds() {
	var str = [];
	 for (var t = 0; t<i+1;t++){
		 var ruleObjs =$("select[name=ruleObjs"+t+"] option:selected").val();
		 if(ruleObjs!=""){
			 str.push(ruleObjs);
		 }
		 
		 str.push(",");
		 var timeSelect = $("select[name=timeInterval"+t+"] option:selected").val();
		 if(timeSelect!=""){
			 str.push(timeSelect);
		 }
		 str.push(",");
		 
		 var numSelect = $("select[name=numercialElement"+t+"] option:selected").val();
		 if(numSelect!=""){
			 str.push(numSelect);
		 }
		 str.push(",");
		 
		 var riskSelect = $("select[name=riskObjs"+t+"] option:selected").val();
		 if(riskSelect!=""){
			 str.push(riskSelect);
		 }
		 str.push(",");
		 
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
}


