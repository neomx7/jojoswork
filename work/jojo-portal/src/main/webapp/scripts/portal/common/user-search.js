/**
 * 人员选择
 */

$(function()
{

    try
    {
        $("input[id^=nextUser]").each(function()
        {
            searchUser($(this));
        });

    }
    catch (e)
    {
        alert("catch error info: " + e);
    }

});

function searchUser(userInputObj)
{
    if (!userInputObj)
    {
        return;
    }
    userInputObj.autocomplete(
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
                success : function(data, status, xhr)
                {
                    // json 数组
                    // var jsonArr = eval(data);
                    response(data);
                },
                error : function(err)
                {
                    alert("error info: " + e);
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
                // $("#nextUser").val(ui.item.label)
            }
        }
    });
}