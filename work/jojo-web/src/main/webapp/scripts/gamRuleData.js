
var i = 0;

var RULE_OBJ_TAGS = {};
var RULE_TIME_TAGS = {};
var RULE_COUNT_TAGS = {};
var RULE_RISK_TAGS = {};

jQuery(function($) {
	initRuleItems("B", 1);
	
	$("input[name='ruleWay']").click(function() {
		var ruleWay = $("input[name='ruleWay']:checked").val();
		var ruleCategory = $("#ruleCategory").val();
		initRuleItems(ruleCategory, ruleWay);
	});
});

function initRuleItems(ruleCategory,  ruleWay)
{
	// 清空原有已经添加的标签项配置，将页面恢复原样
	for (var j = 1; j <= i; j++)
	{
		$("#tr" + j).remove();
	}
	//　清空页面上的缓存数据
	RULE_OBJ_TAGS = {};
	RULE_TIME_TAGS = {};
	RULE_COUNT_TAGS = {};
	RULE_RISK_TAGS = {};
	
    $.post("/scppun/ruledata/getGamBatchRuleItems", {
    	ruleCategory : ruleCategory,
    	ruleWay : ruleWay
	}, function(data) {
		var jsonObj = $.parseJSON(data);
		var ruleItems = jsonObj.ruleItem;
		var ruleItemsLen = ruleItems.length;
		for (var i = 0; i < ruleItemsLen; i++)
		{
			var ruleItem = ruleItems[i];
			if (0 == ruleItem.itemType)
			{
				var objItems = ruleItem.itemList;
				for (var j = 0; j < objItems.length; j++)
				{
					RULE_OBJ_TAGS[objItems[j].id] = objItems[j].itemDesc;
				}
			} 
			else if (1 == ruleItem.itemType)
			{
				var timeItems = ruleItem.itemList;
				for (var j = 0; j < timeItems.length; j++)
				{
					RULE_TIME_TAGS[timeItems[j].id] = timeItems[j].itemDesc;
				}
			}
			else if (2 == ruleItem.itemType)
			{
				var countItems = ruleItem.itemList;
				for (var j = 0; j < countItems.length; j++)
				{
					RULE_COUNT_TAGS[countItems[j].id] = countItems[j].itemDesc;
				}
			}
			else if (3 == ruleItem.itemType)
			{
				var riskItems = ruleItem.itemList;
				for (var j = 0; j < riskItems.length; j++)
				{
					RULE_RISK_TAGS[riskItems[j].id] = riskItems[j].itemDesc;
				}
			}
		}
	}, "text");
}

var activationGamRule = function(ruleId,tagTitle) {
	if (confirm("激活表示该规则标签有效可以用于规则中，激活后不可修改，确认激活")) {
		$.post("/scppun/ruledata/editGamRuleTag", {
			id : ruleId,
			tagTitle:tagTitle,
			status : 1
		}, function(data) {
			if (data == 1) {
				window.location.reload();
			}
		});
	}
};

var invalidGamRule = function(ruleId,tagTitle) {
	if (confirm("删除表示将该规则标签从数据库中删除，删除后不能恢复，确认失效")) {
		$.post("/scppun/ruledata/editGamRuleTag", {
			id : ruleId,
			tagTitle:tagTitle,
			status : 2
		}, function(data) {
			if (data == 1) {
				window.location.reload();
			}
		});
	}
};
