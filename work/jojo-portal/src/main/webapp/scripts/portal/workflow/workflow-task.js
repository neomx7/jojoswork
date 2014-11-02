$(function()
{
    var instanceId = $("#todoInstId").val();
    var taskInstId = $("#todoInstTaskId").val();
    var approvedByManager = $("#approvedByManager").val();

    $("#taskTODOform_" + taskInstId).validationEngine();
    $("button[id='submitBtn_" + taskInstId + "']").button(
    {
        icons :
        {
            primary : "ui-icon-gear"
        },
        text : true
    }).click(function(event)
    {
        if ($("#taskTODOform_" + taskInstId).validationEngine('validate'))
        {
            completeTask(taskInstId);
        }
    });

    // 同意或不同意的页面不同方式的展示

    $("input[name='apprvFlg']").each(function()
    {
       $(this).bind(
               "change",
               function()
               {
                   if ($(this).val() == '2')
                {
                    //改成同意
                   $("#endTaskBtn").hide();
                   $("#submitBtn_"+taskInstId).show();
                   $("#setNextUser_"+taskInstId).show();

                }else if ($(this).val() == '1') {
                    //改成不同意
                    $("#endTaskBtn").show();
                    $("#submitBtn_"+taskInstId).hide();
                    $("#setNextUser_"+taskInstId).hide();
                }
               });
    });

    // 结束流程
    $("button[id='endTaskBtn']").button(
    {
        icons :
        {
            primary : "ui-icon-gear"
        },
        text : true
    }).click(function(event)
    {
        if ($("#taskTODOform_" + taskInstId).validationEngine('validate'))
        {
            completeTask(taskInstId);
        }
    });

    // 页签展示流程图和流程记录
    $('#taskTabs_' + taskInstId).tabs();

    // 展示提交的表单
    var showTaskURL = $("#taskFormInfo").attr("showURL");
    showTaskURL = $.trim(showTaskURL);

    // 过滤掉第一个'/'
    showTaskURL = showTaskURL.replace(/[/]{1,10}/, "");
    if (showTaskURL)
    {
        $.ajax(
        {
            type : 'POST',
            // contentType : 'application/json',
            url : showTaskURL,
            data : null,
            // dataType : 'html',
            success : function(dataResp)
            {
                $("#taskFormInfo").html(dataResp);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown)
            {
                $("#taskFormInfo").html(XMLHttpRequest.responseText);
            }
        });
    }
    showAllTasks(instanceId, taskInstId);
    viewProcessGraghInfo(instanceId, taskInstId);
});

function completeTask(taskInstId)
{
    // $.toJSON
    var dataRequest = ($('#taskTODOform_' + taskInstId).serializeObject());

    $.ajax(
    {
        async : false,
        type : 'POST',
        // contentType : 'application/json',
        url : "workflow/completeTask",
        data : dataRequest,
        dataType : 'json',
        success : function(dataResp)
        {
            var josnResult = (dataResp);
            $("#err_tip").empty();
            if (josnResult.status == 200)
            {
                $("#tip_suc").html("处理成功");
                var targetHtml = $("#globalSucDiv").html();
                showTipMessage(targetHtml);
            }
            else
            {
                $("#tip").html(josnResult.tip);
                $("#tipDesc").html(josnResult.tipDesc);
                var targetHtml = $("#globalErrDiv").html();
                showTipMessage(targetHtml);
            }
        },
        error : function(XMLHttpRequest, textStatus, errorThrown)
        {
            // $("#consoleDlg").dialog("close");
            alert(textStatus);
            $("#err_tip").empty();
            showTipMessage(XMLHttpRequest.responseText, "出错了~~", errorThrown);
        },
        complete : function(xhr, ts)
        {
            // alert("complete!!");
            // alert(xhr.responseText);
            // alert(ts);
        }
    });
}

function showAllTasks(instanceId, taskInstId, approvedByManager)
{
    if (!instanceId)
    {
        showTipMessage("<span>未获取到有效的流程实例id</span>", "出错了~~");
    }
    // 展示当前的流程处理记录
    var colNames = [ "编号", "Task节点名称", "Task节点备注", "处理人", "到达时间", "处理时间", "流程定义ID", "流程实例ID", "taskID", "节点状态"
    ];
    var colModel = [
    {
        name : "number",
        index : "number",
        width : "5%",
        sorttype : "int"
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
        width : "20%",
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
        sorttype : "string"
    },
    {
        name : "updTime",
        index : "updTime",
        width : "10%",
        sorttype : "string"
    },
    // {
    // name : "act",
    // index : "act",
    // sortable : false,
    // align : "center",
    // width : "20%"
    // },
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
    },
    {
        name : "status",
        index : "status",
        sortable : false,
        hidden : false,
        align : "center",
        width : "5%"
    }
    ];
    try
    {
        var extDataRequest = [];
        extDataRequest['instanceId'] = instanceId;
        initJqGird('taskHisGrid_' + taskInstId, 'workflow/showAllTasks', colNames, colModel, 'number', '流程处理记录列表',
                null, "", null, extDataRequest);

        var currGrid = $('#taskHisGrid_' + taskInstId);
        // 重载jqGrid的事件，单击事件
        // var jqgridId = currGrid.attr('id'); // jqgrid 的 id
        currGrid.jqGrid('setGridParam',
        {
            /** 增加数据行的操作按钮 */
            gridComplete : function()
            {
                // 选中最后一行
                var ids = currGrid.jqGrid('getDataIDs');
                if (ids)
                {
                    var idsLength = ids.length;
                    currGrid.jqGrid('setSelection', (ids[idsLength - 1]), true);
                }
            },
            onSelectRow : function(id)
            {
            }
        });

        // 流程记录不需要翻页
        // currGrid.jqGrid('navGrid', '#taskHisGrid_' + taskInstId + 'Pager',
        // {
        // add : false,
        // edit : false,
        // del : false,
        // search : false,
        // refresh : true,
        // refreshtitle : "刷新"
        // });
        // 重设宽度
        // alert(($("#consoleDlg").width()-20));
        currGrid.jqGrid("setGridWidth", ($(window).width() * 0.8), true);
    }
    catch (e)
    {
        showTipMessage(e, "出错了~~");
    }
}

/**
 * 查看流程图信息，活跃的task红色框住
 *
 * @param tblId
 * @param rowid
 * @param approvedByManager
 */
function viewProcessGraghInfo(instanceId, taskInstId, approvedByManager)
{
    if (!instanceId)
    {
        showTipMessage("<span>[流程图]未获取到有效的流程实例id</span>", "出错了~~");
    }
    if (!taskInstId)
    {
        showTipMessage("<span>[流程图]未获取到有效的流程task任务id</span>", "出错了~~");
    }
    var jsonData = {};
    jsonData["proInsId"] = instanceId;
    jsonData = $.toJSON(jsonData);

    // 弹出页面显示流程信息
    $.ajax(
    {
        type : 'POST',
        contentType : 'application/json',
        url : 'workflow/traceProcessDetails',
        data : jsonData,
        dataType : 'json',
        success : function(dataResult)
        {
            if (!dataResult)
            {
                return;
            }
            var infoV = '<img  ' + 'id="processGragh_' + instanceId + '"' + 'src=\"'
            // + appRelPath + '/workflow/getWorkFlowGraph?proDefId='
            // + proDefId
            + appRelPath + '/styles/workflow/imgs/apply.png'

            + '\" style="border:1px solid #dddddd ;" />';
            // position:absolute; left:'
            // + '0' + 'px; top:'
            // + '0' + 'px;" />';

            $("#taskTabs_" + taskInstId + "-1").html(infoV);
            // 循环增加 activi的task样式
            var xPos = 0;
            var yPos = 0;
            var widthPos = 0;
            var heightPos = 0;

            // 获取左偏移 和 上偏移
            var graphAbslLeft = $("#processGragh_" + instanceId).position().left;
            var graphAbslTop = $("#processGragh_" + instanceId).position().top;
            var taskInfo = null;
            for (var i = 0; i < dataResult.length; i++)
            {
                var taskInfo = dataResult[i];
                if (taskInfo["currentActiviti"])
                {
                    xPos = taskInfo["x"];
                    yPos = taskInfo["y"];
                    widthPos = taskInfo["width"];
                    heightPos = taskInfo["height"];
                    // relative absolute
                    var activiTaskHtml = '<div style="position:absolute; border:2px solid red;left:'
                            + (xPos - 1 + graphAbslLeft) + 'px;top:' + (yPos - 1 + graphAbslTop) + 'px;width:'
                            + (widthPos - 2) + 'px;height:' + (heightPos - 2) + 'px;"></div>' + '';
                    // infoV = infoV + activiTaskHtml;
                    $("#taskTabs_" + taskInstId + "-1").append(activiTaskHtml);
                }
            }
            //
        },
        error : function(XMLHttpRequest, textStatus, errorThrown)
        {
            showTipMessage(XMLHttpRequest.responseText, "出错了~~");
        }
    });
}
