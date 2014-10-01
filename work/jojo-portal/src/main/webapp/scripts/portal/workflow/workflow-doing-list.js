$(function()
{
    var colNames = [ "编号", "发起人", "流程节点名称", "流程节点备注", "当前任务处理人", "创建时间", "流程名称", "操作", "流程定义ID", "流程实例ID", "taskID"
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
    {
        name : "taskName",
        index : "taskName",
        width : "20%",
        sorttype : "string"
    },
    {
        name : "taskRemark",
        index : "taskRemark",
        width : "10%",
        sorttype : "string"
    },
    {
        name : "assignee",
        index : "assignee",
        width : "10%",
        sorttype : "string"
    },
    {
        name : "crtTime",
        index : "crtTime",
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
        hidden : false,
        align : "center",
        width : "5%"
    },
    {
        name : "taskId",
        index : "taskId",
        sortable : false,
        hidden : false,
        align : "center",
        width : "5%"
    }
    ];
    try
    {
        initJqGird('doingTaskGrid', 'process/qryDOingTaskList', colNames, colModel, 'number', '我的在办任务列表');

        var currGrid = $('#doingTaskGrid');
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
                                    var btnHtml = "<input style='height:22px;width:90px;' type='button' value='处理' onClick='toProcessDOingTask(\"doingTaskGrid\",\"#instanceId\",\"#rowid\")'/>"
                                            + "&nbsp;"
                                            + "<input style='height:22px;width:90px;' type='button' value='查看流程' onClick='viewProcessInfo(\"doingTaskGrid\",\"#rowindex\")'/>";
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

        currGrid.jqGrid('navGrid', '#doingTaskGridPager',
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
    // $("#doingTaskGrid").jqGrid('setGridWidth',$(window).width()*0.9);
    // });
});

/**
 * @param gridId
 * @param taskId
 */
function toProcessDOingTask(gridId, instanceId, taskId)
{
    // var currGrid = $('#' + gridId);
    var dataRequest = 'theInstId=' + instanceId + "&theTaskId=" + taskId;
    $.ajax(
    {
        type : 'POST',
        url : 'workflow/showTask',
        data : dataRequest,
        success : function(dataResp)
        {
            var consoleDlg = $("#consoleDlg");
            consoleDlg.empty();
            var infoV = dataResp;
            consoleDlg.append(dataResp);
            consoleDlg.dialog("option", "title", "流程查看").dialog("open");

        },
        error : function(XMLHttpRequest, textStatus, errorThrown)
        {
            showTipMessage(XMLHttpRequest.responseText, "出错了~~");
        }
    });
}

function viewProcessInfo(tblId, rowid)
{
    var rowdata = $("#" + tblId).jqGrid('getRowData', rowid);
    var proDefId = rowdata.processDefinitionId;
    var proInsId = rowdata.processInstanceId;
    var jsonData = {};
    jsonData["proDefId"] = proDefId;
    jsonData["proInsId"] = proInsId;
    jsonData = $.toJSON(jsonData);
    // 弹出页面显示流程信息
    $.ajax(
    {
        type : 'POST',
        contentType : 'application/json',
        url : 'workflow/traceProcess',
        data : jsonData,
        dataType : 'json',
        success : function(dataResult)
        {
            var consoleDlg = $("#consoleDlg");
            consoleDlg.empty();
            var infoV = '<img  src=\"'
            // + appRelPath + '/workflow/getWorkFlowGraph?proDefId='
            // + proDefId
            + appRelPath + '/styles/workflow/imgs/apply.png'

            + '\" style="border:1px solid #dddddd ;position:absolute; left:'
            // +dataResult.defX
            + '0' + 'px; top:'
            // +dataResult.defY
            + '0' + 'px;" />' + '<div style="position:absolute; border:2px solid red;left:' + (dataResult.x - 1)
                    + 'px;top:' + (dataResult.y - 1) + 'px;width:' + (dataResult.width - 2) + 'px;height:'
                    + (dataResult.height - 2) + 'px;"></div>' + '';
            // alert(infoV);
            consoleDlg.append(infoV);
            consoleDlg.dialog("option", "title", "查看流程信息").dialog("open");
        },
        error : function(XMLHttpRequest, textStatus, errorThrown)
        {
            showTipMessage(XMLHttpRequest.responseText, "出错了~~");
        }
    });
}
