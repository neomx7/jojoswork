
jQuery(function($) {
	$("#bWCardNo").blur( function () { 
		$.get("/scppun/bwlist/queryCardInfoByCardNo", {
			cardNo : this.value
		},function(data){
			if(data.indexOf(",")>0){
				 var arrtemp=data.split(",");
				 $("#bWBankNameSpan").html(arrtemp[0]);
				 $("#bWBankName").val(arrtemp[0]);
				 $("#bWBankNo").val(arrtemp[1]);
				 $("#bWCardTypeDesSpan").html(arrtemp[3]);
				 $("#bWCardType").val(arrtemp[2]);
				 $("#bWCardTypeDes").val(arrtemp[3]);
			}else{
				 $("#bWBankNameSpan").html("");
				 $("#bWBankName").val("");
				 $("#bWBankNo").val("");
				 $("#bWCardTypeDesSpan").html("");
				 $("#bWCardType").val("");
				 $("#bWCardTypeDes").val("");
			}
		  });
	
	} );
	
});




jQuery(function($) {
	$("#bWCardBin").blur( function () { 
		$.get("/scppun/bwlist/queryCardInfoByCardBin", {
			cardBin : this.value
		},function(data){
			if(data.indexOf(",")>0){
				 var arrtemp=data.split(",");
				 $("#bWBankNameSpan").html(arrtemp[0]);
				 $("#bWBankName").val(arrtemp[0]);
				 $("#bWBankNo").val(arrtemp[1]);
				 $("#bWCardTypeDesSpan").html(arrtemp[3]);
				 $("#bWCardType").val(arrtemp[2]);
				 $("#bWCardTypeDes").val(arrtemp[3]);
			}else{
				 $("#bWBankNameSpan").html("");
				 $("#bWBankName").val("");
				 $("#bWBankNo").val("");
				 $("#bWCardTypeDesSpan").html("");
				 $("#bWCardType").val("");
				 $("#bWCardTypeDes").val("");
			}
		  });
	
	} );
	
});

