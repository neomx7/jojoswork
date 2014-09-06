$(function()
{
    $("button[id='saveBtn']").button(
            {
                icons :
                {
                    primary : "ui-icon-gear"
                },
                text : true
            }).click(function( event ) {
//                event.preventDefault();
                var dataRequest = $.toJSON($('#targetForm').serializeObject());
                //ajax提交
                $.ajax({
                            type : 'POST',
                            contentType : 'application/json',
                            url : 'equipment/saveApply',
                            data : dataRequest,
                            dataType : 'json',  //'json'
                            success : function(dataResult)
                            {
                                var josnResult = eval(dataResult);
                                $("#err_tip").empty();
                                if (josnResult.status == 200)
                                {
                                    $("#tip_suc").html("保存成功");
                                    var errHtml = $("#globalSucDiv").html();
                                    showTipMessage(errHtml);
                                }else {
//                                    $("#err_tip").text(josnResult.tipDesc);
//                                    $("#err_tip").show();
                                    $("#tip").html(josnResult.tip);
                                    $("#tipDesc").html(josnResult.tipDesc);
                                    var errHtml = $("#globalErrDiv").html();
                                    showTipMessage(errHtml);
                                }
                            },
                            error : function(XMLHttpRequest, textStatus, errorThrown)
                            {
                                alert("提交失败，错误信息为 :" + XMLHttpRequest.responseText);
//                                alert("提交失败，错误信息为 :" + errorThrown);
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
            }).click(function( event ) {
//                event.preventDefault();
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

function handleF(id)
{
    alert("handle:" + id);
}
function viewF(id)
{
    alert("view:" + id);
}