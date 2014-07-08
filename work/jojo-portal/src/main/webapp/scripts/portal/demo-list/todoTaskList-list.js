$(function()
{
    var colNames = [ "编号", "任务名称", "任务备注", "发起人", "创建时间", "流程名称", "操作"
    ];
    var colModel = [
    {
        name : "number",
        index : "number",
        width : "10%",
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
        name : "initialAssignee",
        index : "initialAssignee",
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
        width : "35%"
    }
    ];
    try
    {
        initJqGird('todoTaskGrid', 'demo/qryMyTaskList', colNames, colModel, 'number', '待办任务列表');

        var currGrid = $('#todoTaskGrid');
        // 重载jqGrid的事件，单击事件
        // var jqgridId = currGrid.attr('id'); // jqgrid 的 id
        currGrid.jqGrid('setGridParam',
        {

            /** 增加数据行的操作按钮 */
            gridComplete : function()
            {
                var ids = $('#todoTaskGrid').jqGrid('getDataIDs');
                if (ids)
                {
                    var btnHtml = "<input style='height:22px;width:120px;' type='button' value='处理' onClick='processTODOTask(\"todoTaskGrid\",\"#rowid\")'/>";
                    for (var i = 0; i < ids.length; i++)
                    {
                        var cl = ids[i];
                        //按钮的html内容
                        btnHtml = btnHtml.replaceAll("#rowid",cl);
                        $('#todoTaskGrid').jqGrid('setRowData', ids[i],
                        {
                            act : btnHtml
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
                alert(key);
            }
        });

    }
    catch (e)
    {
        alert(e);
    }
});

/**
 * 弹出页面中进行操作，ajax获取弹出页面的信息
 *
 * @param tblId
 * @param rowid
 */
function processTODOTask(tblId, rowid)
{
    // json格式的对象，可以按照rowdata.属性名依次获取需要的属性
    var rowdata = $("#" + tblId).jqGrid('getRowData', rowid);
     alert(rowdata.processName);
//    var datajson = {};
//    datajson['proDefKey'] = (rowdata.key);
//    datajson = $.toJSON(datajson);
//    $.ajax(
//    {
//        type : 'POST',
//        contentType : 'application/json',
//        url : 'workflow/startProcessInstance',
//        data : datajson,
//        dataType : 'json',
//        success : function(data)
//        {
//            alert("流程已经启动！ data: [" + data + "]");
//        },
//        error : function(XMLHttpRequest, textStatus, errorThrown)
//        {
//            alert("error info :" + errorThrown)
//        }
//    });
}