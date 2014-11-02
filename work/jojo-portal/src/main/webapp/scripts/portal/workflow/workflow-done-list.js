$(function()
{
    //"流程节点名称", "流程节点备注",
    var colNames = [ "编号", "发起人",  "启动时间", "完成时间", "流程名称", "操作", "流程定义ID", "流程实例ID"
    ];
    var colModel = [
    {
        name : "number",
        index : "number",
        width : "5%",
        sorttype : "int"
    },
    {
        name : "initialAssignee",
        index : "initialAssignee",
        width : "10%",
        sorttype : "string"
    },
//    {
//        name : "taskName",
//        index : "taskName",
//        width : "20%",
//        sorttype : "string"
//    },
//    {
//        name : "taskRemark",
//        index : "taskRemark",
//        width : "10%",
//        sorttype : "string"
//    },
//    {
//        name : "assignee",
//        index : "assignee",
//        width : "10%",
//        sorttype : "string"
//    },
    {
        name : "formatCrtTime",
        index : "formatCrtTime",
        width : "10%",
        sorttype : "date"
    },
    {
        name : "formatUpdTime",
        index : "formatUpdTime",
        width : "10%",
        sorttype : "date"
    },
    {
        name : "processName",
        index : "processName",
        width : "20%",
        sorttype : "string"
    },
    {
        name : "act",
        index : "act",
        sortable : false,
        align : "center",
        width : "20%"
    },
    {
        name : "processDefinitionId",
        index : "processDefinitionId",
        sortable : false,
        hidden : true,
        align : "center",
        width : "5%"
    },
    {
        name : "processInstanceId",
        index : "processInstanceId",
        sortable : false,
        hidden : true,
        align : "center",
        width : "5%"
    }
    ];
    try
    {
        initJqGird('doneTaskGrid', 'process/qryDoneTaskList', colNames, colModel, 'number', '我的已完成任务列表');

        var currGrid = $('#doneTaskGrid');
        // 重载jqGrid的事件，单击事件
        // var jqgridId = currGrid.attr('id'); // jqgrid 的 id
        currGrid
                .jqGrid(
                        'setGridParam',
                        {

                            /** 增加数据行的操作按钮 */
                            gridComplete : function()
                            {
                                // var userData = (currGrid.jqGrid('getGridParam', 'userData'));
                                // alert(userData.status);

                                var ids = currGrid.jqGrid('getDataIDs');
                                if (ids)
                                {
                                    var btnHtml = "<input style='height:22px;width:90px;' type='button' value='查看流程' onClick='viewProcessInfo(\"doneTaskGrid\",\"#instanceId\",\"#rowid\")'/>";
                                    for (var i = 0; i < ids.length; i++)
                                    {
                                        var rowdata = currGrid.jqGrid('getRowData', ids[i]);// 行数据
                                        // 按钮的html内容
                                        var finalHtml = btnHtml.replaceAll("#instanceId", rowdata.processInstanceId)
                                                .replaceAll("#rowid", rowdata.taskId).replaceAll("#rowindex", ids[i]);
                                        currGrid.jqGrid('setRowData', ids[i], // rowdata.processInstanceId
                                        {
                                            act : finalHtml
                                        });
                                    }
                                }
                            },
                            onSelectRow : function(id)
                            {
                                // 重载 ondblClickRow 事件
                                var rowdata = currGrid.jqGrid('getRowData', id);// 行数据
                                var key = rowdata.taskName;
                                // var datajson = {};
                                // datajson['proDefId']=(key);
                                // datajson = $.toJSON(datajson);

                            }

                        });

        currGrid.jqGrid('navGrid', '#doneTaskGridPager',
        {
            add : false,
            edit : false,
            del : false,
            refresh : true,
            search : true,
            refreshtitle : "刷新",
            searchtitle : "搜索"
        });

    }
    catch (e)
    {
        showTipMessage(e, "出错了~~");
    }
    // $(window).resize(function(){
    // $("#doneTaskGrid").jqGrid('setGridWidth',$(window).width()*0.9);
    // });
});


function viewProcessInfo(tblId, instanceId, taskId)
{
    var dataRequest = 'theInstId=' + instanceId ;
    $.ajax(
    {
        type : 'POST',
        url : 'workflow/showHistoryTask',
        data : dataRequest,
        success : function(dataResp)
        {
            var consoleDlg = $("#consoleDlg");
            consoleDlg.empty();
            var infoV = dataResp;
            consoleDlg.append(dataResp);
            consoleDlg.dialog("option", "title", "历史流程查看").dialog("open");

        },
        error : function(XMLHttpRequest, textStatus, errorThrown)
        {
            showTipMessage(XMLHttpRequest.responseText, "出错了~~");
        }
    });
}
