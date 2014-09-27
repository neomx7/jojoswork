$(function()
{
    $("#setNextUser").show();

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
            url : 'equipment/addApply',
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
        var dataRequest = $.toJSON($('#targetForm').serializeObject());
        // ajax提交
        $.ajax(
        {
            type : 'POST',
            contentType : 'application/json',
            url : 'equipment/startProcess4Apply',
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



    $("button[id='saveBtnE']").button(
            {
                icons :
                {
                    primary : "ui-icon-gear"
                },
                text : true
            }).click(function(event)
            {
                // event.preventDefault();
                var dataRequest = $.toJSON($('#targetFormE').serializeObject());
                // ajax提交
                $.ajax(
                {
                    type : 'POST',
                    contentType : 'application/json',
                    url : 'equipment/editApply',
                    data : dataRequest,
                    dataType : 'json', // 'json'
                    success : function(dataResult)
                    {
                        var josnResult = eval(dataResult);
                        $("#err_tip").empty();
                        if (josnResult.status == 200)
                        {
                            //刷新列表
                            $('#jqGirdList4Apply').trigger( 'reloadGrid' );
                            $("#tip_suc").html("保存成功");
                            var targetHtml = $("#globalSucDiv").html();
                            showTipMessage(targetHtml);
                        }
                        else
                        {
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


    $("button[id='submitBtnE']").button(
            {
                icons :
                {
                    primary : "ui-icon-gear"
                },
                text : true
            }).click(function(event)
            {
                // event.preventDefault();
                var dataRequest = $.toJSON($('#targetFormE').serializeObject());
                // ajax提交
                $.ajax(
                {
                    type : 'POST',
                    contentType : 'application/json',
                    url : 'equipment/startProcess4Apply',
                    data : dataRequest,
                    dataType : 'json',
                    success : function(dataResult)
                    {
                        var josnResult = eval(dataResult);
                        $("#err_tip").empty();
                        if (josnResult.status == 200)
                        {
                            //刷新列表
                            $('#jqGirdList4Apply').trigger( 'reloadGrid' );
                            $("#tip_suc").html("提交成功，当前申请已经进入流程处理,请到待办列表中查看");
                            var targetHtml = $("#globalSucDiv").html();
                            showTipMessage(targetHtml);
                        }
                        else
                        {
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


    // binds form submission and fields to the validation engine
    $("#targetForm").validationEngine();

    try
    {
        var colNames = [ "id", "编号", "申请名称", "创建时间","状态","当前流程节点", "备注", "操作"
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
            name : "status",
            index : "status",
            width : "10%",
            sorttype : "int"
        },
        {
            name : "statusDsc",
            index : "statusDsc",
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
        //覆盖翻页方法，定制各自的功能
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

    try
    {
        $("#nextUserE").autocomplete(
        {
            source : function(request, response)
            {
                var obj = {};
                obj.userName = request.term;
                var param = JSON.stringify(obj);
                $.ajax(
                {
                    url : "user/search",
                    contentType : "application/json; charset=utf-8",
                    type : "post",
                    dataType : "json",
                    data : param,
                    success : function( data, status, xhr )
                    {
                        //json 数组
//                        var jsonArr = eval(data);
                        response( data );
                    },
                    error : function(err)
                    {
                        alert("error info: "+ e);
                    }
                });
            },

            minLength : 1,
            select : function(event, ui)
            {
                // log( ui.item ?
                // "Selected: " + ui.item.value + " aka " + ui.item.id :
                // "Nothing selected, input was " + this.value );
                if (ui.item)
                {
                   // alert("Selected: " + ui.item.value + "; itemId: " + ui.item.id);
//                    $("#nextUser").val(ui.item.label)
                }
            }
        });

    }
    catch (e)
    {
        alert("catch error info: "+ e);
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