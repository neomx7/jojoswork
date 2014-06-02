$(function()
{
    var colNames = [ "编号", "流程名称", "key", "备注", "操作"
    ];
    var colModel = [
    {
        name : "theId",
        index : "theId",
        width : "30%",
        sorttype : "String"
    },
    {
        name : "theName",
        index : "theName",
        width : "25%",
        sorttype : "string"
    },
    {
        name : "key",
        index : "key",
        width : "20%",
        sorttype : "string"
    },
    {
        name : "theRemark",
        index : "theRemark",
        width : "10%",
        sorttype : "string"
    },
    {
        name : "act",
        index : "act",
        sortable : false,
        align : "center",
        width : "15%"
    }
    ];
    //var treedata = $("#jqgrid").jqGrid('getRowData',rowid);
    var btnsEl = "<input style='height:22px;width:60px;' type='button' value='编辑' onClick='startWorkFlow(\"jqGirdList\",\"#rowid\")'/>";
    var clickEl = '/workflow/getWorkFlowGraph';
    initJqGird('jqGirdList', 'workflow/queryDefines', colNames, colModel, 'theId', '已经部署的流程定义'
            ,btnsEl,""
            ,clickEl);
    $(window).resize(function()
    {
        $("#jqGirdList").setGridWidth($(window).width() * 0.99);
        // $("#charDataTab").setGridWidth(document.body.clientWidth*0.99);
    });



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

}

