$(function()
{
    var colNames = [ "编号", "用户名", "创建时间", "备注", "操作"];
    var colModel = [
    {
        name : "number",
        index : "number",
        width : "10%",
        sorttype : "int"
    },
    {
        name : "theName",
        index : "theName",
        width : "20%",
        sorttype : "string"
    },
    {
        name : "crtTime",
        index : "crtTime",
        width : "15%",
        sorttype : "string"
    },
    {
        name : "theRemark",
        index : "theRemark",
        width : "20%",
        sorttype : "string"
    },
    {
        name : "act",
        index : "act",
        sortable : false,
        align : "center",
        width : "35%"
    }
    ];
    try
    {
        var btnsEl = "<input style='height:22px;width:120px;' type='button' value='发起物料申请' onClick='startWorkFlow(\"jqGirdList\",\"#rowid\")'/>";
        var clickEl = '/workflow/getWorkFlowGraph';
        initJqGird('createApplyList', 'demo/createApplyList', colNames, colModel, 'number', '未提交的物料申请列表',
                btnsEl,"",clickEl);
    }
    catch (e)
    {
        alert(e);
    }
});

/**
 * 启动流程  getWorkFlowDefineInfo
 * @param tblId
 * @param rowid
 */
function startWorkFlow(tblId,rowid)
{
    //json格式的对象，可以按照rowdata.属性名依次获取需要的属性
    var rowdata = $("#"+tblId).jqGrid('getRowData',rowid);
//    alert(rowdata.theName);
    var datajson = {};
    datajson['proDefKey']=(rowdata.key);
    datajson = $.toJSON(datajson);
    $.ajax( {
        type : 'POST',
        contentType : 'application/json',
        url : 'workflow/startProcessInstance',
        data : datajson ,
        dataType : 'json',
        success : function(data) {
            alert("流程已经启动！ data: [" +data+"]");
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            alert("error info :" + errorThrown)
        }
      });
}