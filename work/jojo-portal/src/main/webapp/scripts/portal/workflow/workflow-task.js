$(function()
{
    $("button[id='submitBtn_"+$("#todoInstTaskId").val()+"']").button(
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
            showTipMessage(XMLHttpRequest.responseText,"出错了~~",errorThrown);
        }
    });
}