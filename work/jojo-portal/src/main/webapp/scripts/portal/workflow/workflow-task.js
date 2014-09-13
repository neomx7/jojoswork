$(function()
{
    $("button[id='submitBtn_" + $("#todoInstTaskId").val() + "']").button(
    {
        icons :
        {
            primary : "ui-icon-gear"
        },
        text : true
    }).click(function(event)
    {
        completeTask();
    });

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

    var instanceId = $("#todoInstId").val();
    showAllTasks(instanceId);

});

function completeTask()
{
    var dataRequest = $.toJSON($('#taskTODOform').serializeObject());
    $.ajax(
    {
        type : 'POST',
        contentType : 'application/json',
        url : "workflow/completeTask",
        data : dataRequest,
        dataType : 'json',
        success : function(dataResp)
        {
            var josnResult = eval(dataResp);
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
            showTipMessage(XMLHttpRequest.responseText, "出错了~~", errorThrown);
        }
    });
}

function showAllTasks(instanceId)
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
//    {
//        name : "act",
//        index : "act",
//        sortable : false,
//        align : "center",
//        width : "20%"
//    },
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
//        extDataRequest=$.toJSON(extDataRequest);
        initJqGird('taskHisGrid', 'workflow/showAllTasks', colNames, colModel, 'number', '流程处理记录列表',null,"",null,extDataRequest);

        var currGrid = $('#taskHisGrid');
        // 重载jqGrid的事件，单击事件
        // var jqgridId = currGrid.attr('id'); // jqgrid 的 id
        currGrid.jqGrid('setGridParam',
        {
            /** 增加数据行的操作按钮 */
            gridComplete : function()
            {
            },
            onSelectRow : function(id)
            {
            }
        });

        currGrid.jqGrid('navGrid', '#todoTaskGridPager',
        {
            add : false,
            edit : false,
            del : false,
            search : false,
            refresh : true,
            refreshtitle : "刷新"
        });

    }
    catch (e)
    {
        showTipMessage(e, "出错了~~");
    }
}