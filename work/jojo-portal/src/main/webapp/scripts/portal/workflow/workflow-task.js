$(function()
{
//展示提交的表单
    var showTaskURL = $("#taskFormInfo").attr("showURL");
    $.ajax( {
        type : 'POST',
        contentType : 'application/json',
        url : showTaskURL,
        data : {} ,
        dataType : 'html',
        success : function(data) {
            $("#taskFormInfo").html(data);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            $("#taskFormInfo").html(XMLHttpRequest.responseText);
        }
      });

});

