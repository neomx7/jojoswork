$(function()
{
    $("button[id='saveBtn']").button(
    {
        icons :
        {
            primary : "ui-icon-gear"
        },
        text : true
    }).click(function(event)
    {
        // event.preventDefault();
        var dataRequest = $.toJSON($('#targetForm').serializeObject());
        // ajax提交
        $.ajax(
        {
            type : 'POST',
            contentType : 'application/json',
            url : 'equipment/saveApply',
            data : dataRequest,
            dataType : 'json', // 'json'
            success : function(dataResult)
            {
                var josnResult = eval(dataResult);
                $("#err_tip").empty();
                if (josnResult.status == 200)
                {
                    $("#tip_suc").html("保存成功");
                    var errHtml = $("#globalSucDiv").html();
                    showTipMessage(errHtml);
                }
                else
                {
                    // $("#err_tip").text(josnResult.tipDesc);
                    // $("#err_tip").show();
                    $("#tip").html(josnResult.tip);
                    $("#tipDesc").html(josnResult.tipDesc);
                    var errHtml = $("#globalErrDiv").html();
                    showTipMessage(errHtml);
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown)
            {
                alert("提交失败，错误信息为 :" + XMLHttpRequest.responseText);
                // alert("提交失败，错误信息为 :" + errorThrown);
            }
        });
    });
    $("button[id='submitBtn']").button(
    {
        icons :
        {
            primary : "ui-icon-gear"
        },
        text : true
    }).click(function(event)
    {
        // event.preventDefault();
    });

    // binds form submission and fields to the validation engine
    $("#targetForm").validationEngine();

    try
    {
        var colNames = [ "id", "编号", "申请名称", "创建时间", "备注", "操作"
        ];
        var colModel = [
        {
            name : "theId",
            index : "theId",
            sortable : false,
            hidden : true,
            align : "center",
            width : 1
        },
        {
            name : "number",
            index : "number",
            width : "5%",
            sorttype : "number"
        },
        {
            name : "theName",
            index : "theName",
            width : "25%",
            sorttype : "string"
        },
        {
            name : "formatCrtTime",
            index : "formatCrtTime",
            width : "10%",
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
        initJqGird('jqGirdList4Apply', 'equipment/listEquipment', colNames, colModel, 'theId', '查看申请列表');
        var currGrid = $('#jqGirdList4Apply');
        currGrid
                .jqGrid(
                        'setGridParam',
                        {
                            /** 增加数据行的操作按钮 */
                            gridComplete : function()
                            {
                                var ids = currGrid.jqGrid('getDataIDs');
                                if (ids)
                                {
                                    var btnHtml = "<input style='height:22px;width:120px;' type='button' value='处理' onClick='handleF(\"#rowid\")'/>"
                                            + ""
                                            + "<input style='height:22px;width:120px;' type='button' value='查看' onClick='viewF(\"#rowid\")'/>";
                                    for (var i = 0; i < ids.length; i++)
                                    {
                                        // 按钮的html内容
                                        var finalBtnHtml = btnHtml.replaceAll("#rowid", ids[i]);
                                        currGrid.jqGrid('setRowData', ids[i],
                                        {
                                            act : finalBtnHtml
                                        });
                                    }
                                }
                            }
                        });
        currGrid.jqGrid('navGrid', '#jqGirdList4ApplyPager',
        {
            refresh : true,
            edit : false,
            add : false,
            del : true,
            search : true,
            refreshtitle : "刷新",
            deltitle : "删除",
            searchtitle : "搜索"
        });

    }
    catch (e)
    {
        alert("............" + e);
    }

});

/**
 * 处理节点，可以保存或发起申请流程；这里要实现的是查询并展示节点信息 TODO 注意现在的弹出页面有个同一问题，就是都来自于 index.ftl里面的consoleDlg部分，未能解决同时有多个窗口弹出的问题，最后一个会覆盖之前的。
 *
 * @param id
 */
function handleF(id)
{
    // alert("handle:" + id);
    var rowdata = $('#jqGirdList4Apply').jqGrid('getRowData', id);// 行数据
     var dataRequest = "theId=" + $.trim(rowdata.theId);
//    var dataRequest = {};
//    dataRequest["form.theId"] = $.trim(rowdata.theId);
//    dataRequest = $.toJSON(dataRequest);
    // alert(dataRequest);
    // ajax提交
    $.ajax(
    {
        type : 'POST',
        contentType : 'application/x-www-form-urlencoded',
        url : 'equipment/showApply',
        data : dataRequest,
        dataType : 'html',
        success : function(dataResult)
        {
            showTipMessage(dataResult, "查看申请");
        },
        error : function(XMLHttpRequest, textStatus, errorThrown)
        {
            alert("处理申请失败，错误信息为 :" + XMLHttpRequest.responseText);
            // alert("提交失败，错误信息为 :" + errorThrown);
        }
    });

}
function viewF(id)
{
    alert("view:" + id);
}