/**
 * 人员选择
 */
function split(val)
{
    return val.split(/,\s*/);
}
function extractLast(term)
{
    return split(term).pop();
}

$(function()
{

    try
    {
        $("#nextUser").autocomplete(
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