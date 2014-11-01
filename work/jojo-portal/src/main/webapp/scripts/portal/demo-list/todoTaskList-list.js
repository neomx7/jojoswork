$(function()
{
    var colNames = [ "编号","发起人","流程名称","流程启动时间", "流程节点名称", "流程节点备注",  "任务节点创建时间",  "操作", "流程定义ID", "流程实例ID", "taskID"
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
        name : "processName",
        index : "processName",
        width : "20%",
        sorttype : "string"
    },

    {
        name : "formatProcessCrtTime",
        index : "formatProcessCrtTime",
        width : "10%",
        sorttype : "date"
    },

    {
        name : "taskName",
        index : "taskName",
        width : "20%",
        sorttype : "string"
    },
    {
        name : "description",
        index : "description",
        width : "20%",
        sorttype : "string"
    },

    {
        name : "formatCrtTime",
        index : "formatCrtTime",
        width : "10%",
        sorttype : "date"
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
    },
    {
        name : "taskId",
        index : "taskId",
        sortable : false,
        hidden : true,
        align : "center",
        width : "5%"
    }
    ];
    try
    {
        initJqGird('todoTaskGrid', 'process/qryMyTaskList', colNames, colModel, 'number', '我的待办任务列表');

        var currGrid = $('#todoTaskGrid');
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
                                    var btnHtml = "<input style='height:22px;width:90px;' type='button' value='处理' onClick='toProcessTODOTask(\"todoTaskGrid\",\"#instanceId\",\"#rowid\")'/>"
                                            + "&nbsp;";
//                                            + "<input style='height:22px;width:90px;' type='button' value='查看流程' onClick='viewProcessInfo(\"todoTaskGrid\",\"#rowindex\")'/>";
                                    for (var i = 0; i < ids.length; i++)
                                    {
                                        var rowdata = currGrid.jqGrid('getRowData', ids[i]);// 行数据
                                        // 按钮的html内容
                                        var finalHtml = btnHtml.replaceAll("#instanceId", rowdata.processInstanceId).replaceAll("#rowid", rowdata.taskId).replaceAll("#rowindex", ids[i]);
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

        currGrid.jqGrid('navGrid', '#todoTaskGridPager',
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
        showTipMessage(e,"出错了~~");
    }
//    $(window).resize(function(){　　
//        $("#todoTaskGrid").jqGrid('setGridWidth',$(window).width()*0.9);　　
//     });　
});

/**
 * @param gridId
 * @param taskId
 */
function toProcessTODOTask(gridId,instanceId,taskId)
{
// var currGrid = $('#' + gridId);
    var dataRequest = 'theInstId='+instanceId + "&theTaskId=" + taskId;
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
            consoleDlg.dialog("option", "title", "流程处理").dialog("open");

        },
        error : function(XMLHttpRequest, textStatus, errorThrown)
        {
            showTipMessage(XMLHttpRequest.responseText,"出错了~~");
        }
    });
}

/**
 * 弹出页面中进行操作，ajax获取弹出页面的信息
 *
 * @param tblId
 * @param rowid
 */
function comleteTODOTask(tblId, rowid)
{
    // json格式的对象，可以按照rowdata.属性名依次获取需要的属性
    var rowdata = $("#" + tblId).jqGrid('getRowData', rowid);
    // alert(rowdata.processName);
    var datajson = {};
    datajson['taskId'] = (rowdata.taskId);
    datajson = $.toJSON(datajson);
    $.ajax(
    {
        type : 'POST',
        contentType : 'application/json',
        url : 'process/toProcessTask',
        data : datajson,
        dataType : 'json',
        success : function(data)
        {
            var consoleDlg = $("#consoleDlg");
            consoleDlg.empty();
            var infoV = data;
            consoleDlg.append(infoV);
            consoleDlg.dialog("option", "title", "流程处理").dialog("open");

        },
        error : function(XMLHttpRequest, textStatus, errorThrown)
        {
            showTipMessage(XMLHttpRequest.responseText,"出错了~~");
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
            showTipMessage(XMLHttpRequest.responseText,"出错了~~");
        }
    });
}





/**
 * 结束当前流程节点，进入下一个流程处理节点
 */
function processTODOTask()
{
    // 提交请求
    var datajson = {};
    datajson['taskId'] = $("#taskId").val();
    datajson['nextAssignee'] = $("#nextAssignee").val();
    datajson = $.toJSON(datajson);
    $.ajax(
    {
        type : 'POST',
        contentType : 'application/json',
        url : 'workflow/processTODOTask',
        data : datajson,
        dataType : 'json',
        success : function(data)
        {
            var consoleDlg = $("#consoleDlg");
            // 关闭弹出窗口
            consoleDlg.empty();
            consoleDlg.dialog('close');

        },
        error : function(XMLHttpRequest, textStatus, errorThrown)
        {
            showTipMessage(XMLHttpRequest.responseText,"出错了~~");
        }
    });
}
