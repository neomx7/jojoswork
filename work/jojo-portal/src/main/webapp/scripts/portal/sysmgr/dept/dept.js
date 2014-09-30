$(function()
{
        // alert(appRelPath);
        $('#dept_tree').jstree(
        {
            'core' :
            {
                'animation': 1,
                'data' :
                {
                    'type': 'POST',
                    'dataType': 'json',
// 'contentType : "text"', //加上反而不好用了
                    'url' :
                        function(node)
                    {
                        return node.id === '#' ? 'sysmgr/showDeptTree?currendNodeCode=0'
                                : 'sysmgr/showDeptTree?currendNodeCode=' + node.id;
                    }
                }
            }
       })
       .on('select_node.jstree', function (e, data) {
           var targetId = data.selected[0];
           //展示部门用户列表
           var dataRequest = "currendNodeCode="+targetId;
           $.ajax({
               url: "sysmgr/queryDeptUsers",
               type: "POST",
               data : dataRequest,
               dataType: "html",
               success : function(req, err){
                   $("#deptUsrsDiv").empty();
                  $("#deptUsrsDiv").append(req);
                  initPortlet();
//                  $( "#user-list-table" ).sortable();
//                  $( "#user-list-table" ).disableSelection();
               },
               error : function(XMLHttpRequest, textStatus, errorThrown)
               {
                   alert("处理失败，错误信息为 :" + XMLHttpRequest.responseText);
               }
           });





// var i, j, r = [];
// for(i = 0, j = data.selected.length; i < j; i++) {
// r.push(data.instance.get_node(data.selected[i]).text);
// }
// $('#jstree_demo_div').jstree(
// "close_all"
// );
// $('#jstree_demo_div').jstree(
// "open_node", [ "#" + $('#jstree_demo_div').jstree("get_parent", targetId)]
// );
//           $('#dept_tree').jstree( "open_node",[ "#" +data.instance.get_node(targetId)]);
       })
       ;

        // 增加展开节点的方法
// $('#dept_tree').bind("select_node.jstree", function (node,evt) {
// if (node.length)
// {
// alert(node[0]);
// }
// });
});

function initPortlet(){
    $( ".column" ).sortable({
        connectWith: ".column",
        handle: ".portlet-header",
        cancel: ".portlet-toggle",
        placeholder: "portlet-placeholder ui-corner-all"
    });

    $( ".portlet" )
    .addClass( "ui-widget ui-widget-content ui-helper-clearfix ui-corner-all" )
    .find( ".portlet-header" )
    .addClass( "ui-widget-header ui-corner-all" )
    .prepend( "<span class='ui-icon ui-icon-minusthick portlet-toggle'></span>");

    $( ".portlet-toggle" ).click(function() {
        var icon = $( this );
        icon.toggleClass( "ui-icon-minusthick ui-icon-plusthick" );
        icon.closest( ".portlet" ).find( ".portlet-content" ).toggle();
    });
}
