$(function()
{
    var colNames = [ "编号", "userName", "status"];
    var colModel = [
                    {
                        name : "id",
                        index : "id",
                        width : 60,
                        sorttype : "int"
                    },
                    {
                        name : "userName",
                        index : "userName",
                        width : 100,
                        sorttype : "string"
                    },
                    {
                        name : "status",
                        index : "status",
                        width : 90,
                        sorttype : "string"
                    }
                    ];
    initJqGird('jqGirdList','demo/list',colNames,colModel,'id','测试 jqGird 在 jqueryUI 的 tabs 里面展示 list');
});