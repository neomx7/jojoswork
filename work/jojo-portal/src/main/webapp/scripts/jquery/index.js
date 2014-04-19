//工程相对路径
var appRelPath="";

// ### 页面布局排版 部分 ### //
var myLayout = null;
// 作用不明
function toggleLiveResizing()
{
    $.each($.layout.config.borderPanes, function(i, pane)
    {
        var o = myLayout.options[pane];
        o.livePaneResizing = !o.livePaneResizing;
    });
};

// 来回切换布局的‘是否记住页面的临时设置’开关
function toggleStateManagement(skipAlert, mode)
{
    if (!$.layout.plugins.stateManagement)
        return;

    var options = myLayout.options.stateManagement, enabled = options.enabled // current
    // setting
    ;
    if ($.type(mode) === "boolean")
    {
        if (enabled === mode)
            return; // already correct
        enabled = options.enabled = mode
    }
    else
        enabled = options.enabled = !enabled; // toggle option

    if (!enabled)
    { // if disabling state management...
        myLayout.deleteCookie(); // ...clear cookie so will NOT be found on
        // next refresh
        if (!skipAlert)
            alert('This layout will reload as the options specify \nwhen the page is refreshed.');
    }
    else if (!skipAlert)
        alert('This layout will save & restore its last state \nwhen the page is refreshed.');

    // update text on button
    var $Btn = $('#btnToggleState'), text = $Btn.html();
    if (enabled)
        $Btn.html(text.replace(/Enable/i, "Disable"));
    else
        $Btn.html(text.replace(/Disable/i, "Enable"));
};

// set EVERY 'state' here so will undo ALL layout changes
// used by the 'Reset State' button: myLayout.loadState( stateResetSettings )
var stateResetSettings =
{
    north__size : "auto",
    north__initClosed : false,
    north__initHidden : false,
    south__size : "auto",
    south__initClosed : false,
    south__initHidden : false,
    west__size : 200,
    west__initClosed : false,
    west__initHidden : false,
    east__size : 300,
    east__initClosed : false,
    east__initHidden : false
};

$(document).ready(function()
{
    try
    {
        initLayout();
        initJqGird();

        // 增加top窗点击事件（第1、2级menu）

        bindMenuEvents();
        // alert("-------[Done]-------");
    }
    catch (e)
    {
        // TODO: handle exception
        alert(e);
    }

});

/**
 * //去掉全部元素的active样式(下面的白色三角底) $("div[id^='menu_1#']").each(function() {
 * $(this).children(1).children(1).removeClass("v-button-active").removeClass("active"); }
 */
function bindMenuEvents()
{
    // ajax 触发 点击1级菜单刷新 2级top菜单栏
    $("div[id^='menu_1#']").each(function(){
        $(this).bind("click",function(){
            // 去掉全部元素的active样式(下面的白色三角底)
            $("div[id^='menu_1#']").each(function()
            {
                $(this).children(1).children(1).removeClass("v-button-active active");
            });

            // 当前元素置为 active 样式
            var activingDiv = $(this).children(1).children(1);
            activingDiv.addClass("v-button-active").addClass("active");

            var jsonData = {};
            var menduId = ($(this).attr("id").split("#")[1]);
            if (jsonData["theId"] && jsonData["theId"].push)
            {
                jsonData["theId"].push(menduId || '');
            }
            else
            {
                jsonData["theId"] = (menduId || '');
            }
            jsonData = $.toJSON(jsonData);
            // = {"theId" : "'" + menduId + "'"};
            $.ajax({
                type : 'POST',
                contentType : 'application/json',
                url : 'menu/show',
                data : jsonData,
                dataType : 'html',
                success : function(dataResult)
                {
                    // 这里把ajax的结果(html内容)通过js替换dom中的元素
                    // json格式的 List<MenuMO> 数组字符串
                    // 遍历 list, 并生成 html
                    var josnArray = eval(dataResult);
                    $("div[id='div_menu_2']").empty();
                    for (var idx = 0; idx < josnArray.length; idx++)
                    {
                        // alert(josnArray[i].theId + josnArray[i].theName);
                        $("div[id='div_menu_2']")
                                .append(
                                        "<div tabindex=\"0\" class=\"v-button v-button-link link\" role=\"button\" id=\"menu_2#"
                                                + josnArray[idx].theId
                                                + "\"  style=\"width: 69px;\"><span class=\"v-button-wrap\"><span class=\"v-button-caption\">"
                                                + josnArray[idx].theName
                                                + "</span></span></div>");
                    }
                    // do sth more...
                    bind2LvMenuEvents();
                },
                error : function(XMLHttpRequest, textStatus, errorThrown)
                {
                    alert("error info :" + errorThrown)
                }
           });
        });

    });




}

function bind2LvMenuEvents()
{
    // 2级菜单
    $("div[id^='menu_2#']").each(function(){
        //先解除绑定
        $(this).unbind("click");
        $(this).bind("click", function(){
            // 去掉全部元素的active样式(下面的白色三角底)
            $("div[id^='menu_2#']").each(function()
            {
                $(this).removeClass("v-button-active active");
            });

            // 当前元素置为 active 样式
            $(this).addClass("v-button-active").addClass("active");

            // ajax 触发 点击2级菜单刷新3级左侧菜单栏
            var jsonData = {};
            var menduId = ($(this).attr("id").split("#")[1]);
            if (jsonData["theId"] && jsonData["theId"].push)
            {
                jsonData["theId"].push(menduId || '');
            }
            else
            {
                jsonData["theId"] = (menduId || '');
            }
            jsonData = $.toJSON(jsonData);
            $.ajax({
                type : 'POST',
                contentType : 'application/json',
                url : 'menu/show',
                data : jsonData,
                dataType : 'html',
                success : function(dataResult)
                {
                    // 这里把ajax的结果(html内容)通过js替换dom中的元素
                    // json格式的 List<MenuMO> 数组字符串
                    // 遍历 list, 并生成 html
                    var josnArray = eval(dataResult);
                    $("table[id='tbl_menu_3']").children("tbody").empty();
                    for (var idx = 0; idx < josnArray.length; idx++)
                    {
                        // alert(josnArray[i].theId + josnArray[i].theName);
                        $("table[id='tbl_menu_3']").children("tbody")
                                .append(
                                        "<tr class=\"" + (idx%2==0?"v-table-row":"v-table-row-odd")+ "\" "
                                        + "id=\"menu_3#" + josnArray[idx].theId + "\">"
                                        + "<td class=\"v-table-cell-content\" style=\"width: 100%;\"><div class=\"v-table-cell-wrapper\" style=\"width: 22px;\">"
                                        + "<div class=\"v-embedded v-embedded-image\" style=\"width: 22px; height: 22px;\">"
                                        + "<img src=\""+appRelPath+"/styles/activiti/img/task-22.png\"></div></div></td>"
                                        + "<td class=\"v-table-cell-content\" style=\"width: 100%;\"><div class=\"v-table-cell-wrapper\" style=\"width: 100%;\">"
                                        + josnArray[idx].theName +"</div></td></tr>"
                                        );
                    }
                    // do sth more...
                    bind3LvMenuEvents();
                },
                error : function(XMLHttpRequest, textStatus, errorThrown)
                {
                    alert("error info :" + errorThrown)
                }
            });
        });
    });
}

/**
 *
 */
function bind3LvMenuEvents()
{
    // 3级菜单,在内容区域里增加新标签(添加时根据 id 去重复),标签内容使用 ajax 的页面返回的 html 填充
    $("tr[id^='menu_3#']").each(function(){
        //先解除绑定
        $(this).unbind("click");
        $(this).bind("click", function(){
            // 去掉全部元素的select样式(淡蓝色背景)
            $("tr[id^='menu_3#']").each(function()
            {
                $(this).removeClass("v-selected");
            });

            // 当前元素置为 active 样式
            $(this).addClass("v-selected");
        });
    });
}

function initLayout()
{
    // this layout could be created with NO OPTIONS - but showing some
    // here just as a sample...
    // myLayout = $('body').layout(); -- syntax with No Options
    try
    {

        myLayout = $('body').layout(
        {

            // reference only - these options are NOT required because
            // 'true' is the default
            closable : true // pane can open & close
            ,
            resizable : true // when open, pane can be resized
            ,
            slidable : true // when closed, pane can 'slide' open over
            // other
            // panes - closes on mouse-out
            ,
            livePaneResizing : true

            // some resizing/toggling settings
            ,
            north__slidable : false // OVERRIDE the pane-default of
            // 'slidable=true'
            ,
            north__spacing_open : 0,
            north__togglerLength_closed : '100%' // toggle-button is
            // full-width of
            // resizer-bar
            ,
            north__spacing_closed : 20 // big resizer-bar when open
            // (zero
            // height)
            ,
            north__resizable : false,

            south__resizable : false // OVERRIDE the pane-default of
            // 'resizable=true'
            ,
            south__spacing_open : 0 // no resizer-bar when open (zero
            // height)
            ,
            south__spacing_closed : 20 // big resizer-bar when open
            // (zero
            // height)

            // some pane-size settings
            ,
            west__minSize : 100,
            east__size : 300,
            east__minSize : 200,
            east__maxSize : .5 // 50% of layout width
            ,
            center__minWidth : 100

            // some pane animation settings
            ,

            // east__spacing_open : 0,

            // west__spacing_open : 0,
            west__animatePaneSizing : false,
            west__fxSpeed_size : "fast" // 'fast' animation when
            // resizing
            // west-pane
            ,
            west__fxSpeed_open : 1000 // 1-second animation when
            // opening
            // west-pane
            ,
            west__fxSettings_open :
            {
                easing : "easeOutBounce"
            } // 'bounce' effect when opening
            ,
            west__fxName_close : "none" // NO animation when closing
            // west-pane

            // enable showOverflow on west-pane so CSS popups will
            // overlap
            // north pane
            ,
            west__showOverflowOnHover : true

            // enable state management
            ,
            stateManagement__enabled : true // automatic cookie load &
            // save
            // enabled by default

            ,
            showDebugMessages : true
        // log and/or display messages from debugging & testing code
        });

    }
    catch (e)
    {
        // TODO: handle exception
        alert(e);
    }

    // if there is no state-cookie, then DISABLE state management
    // initially
    // var cookieExists = !$.isEmptyObject(myLayout.readCookie());
    // if (!cookieExists)
    // toggleStateManagement(true, false);

    // myLayout
    // // add event to the 'Close' button in the East pane dynamically...
    // .bindButton('#btnCloseEast', 'close', 'east')
    //
    // // add event to the 'Toggle South' buttons in Center AND South panes
    // // dynamically...
    // .bindButton('.south-toggler', 'toggle', 'south')
    //
    // // add MULTIPLE events to the 'Open All Panes' button in the Center
    // // pane dynamically...
    // .bindButton('#openAllPanes', 'open', 'north').bindButton(
    // '#openAllPanes', 'open', 'south').bindButton(
    // '#openAllPanes', 'open', 'west').bindButton(
    // '#openAllPanes', 'open', 'east')
    //
    // // add MULTIPLE events to the 'Close All Panes' button in the Center
    // // pane dynamically...
    // .bindButton('#closeAllPanes', 'close', 'north').bindButton(
    // '#closeAllPanes', 'close', 'south').bindButton(
    // '#closeAllPanes', 'close', 'west').bindButton(
    // '#closeAllPanes', 'close', 'east')
    //
    // // add MULTIPLE events to the 'Toggle All Panes' button in the
    // // Center pane dynamically...
    // .bindButton('#toggleAllPanes', 'toggle', 'north').bindButton(
    // '#toggleAllPanes', 'toggle', 'south').bindButton(
    // '#toggleAllPanes', 'toggle', 'west').bindButton(
    // '#toggleAllPanes', 'toggle', 'east');
    //
    // // 'Reset State' button requires updated functionality in rc29.15+
    // if ($.layout.revision && $.layout.revision >= 0.032915)
    // $('#btnReset').show();
}

function initJqGird()
{

    $("#jqGirdList").jqGrid(
    { // jqGrid固定的写法:$("#list").jqGrid({参数})
        contentType : 'application/json',
        datatype : "json", // 将这里改为使用JSON数据
        url : "demo/list", // 这是Action的请求地址，注意相对路径需要去掉最前面的一个'/'
        mtype : "post", // 提交类型
        prmNames :
        {
            search : "search"
        },
        jsonReader :
        {
            // id : "0",
            root : "rows",
            page : "page",
            total : "total",
            records : "records",
            repeatitems : false,
            userdata : "userdata"
        // repeatitems : false
        },

        height : "auto", // 表格高度
        width : 900, // 表格宽度
        // 表格结构定义
        colNames : [ "编号", "userName", "status"
        ],
        colModel : [
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
        ],
        // ,{
        // name : "status",
        // index : "status",
        // width : 90,
        // sorttype : "date"
        // }

        // jsonReader : {
        // root : "rows",
        // page : "page",
        // total : "total",
        // records : "records",
        // repeatitems : false
        // },
        pager : "#pager", // 分页工具栏
        // imgpath : "themes/redmond/images", // 图片路径
        autoWidth : true,
        // rownumbers : true, // 是否显示列数
        viewrecords : true, // 是否显示行数
        rowNum : 10, // 每页默认显示记录数
        rowList : [ 10, 20, 30
        ], // 可调整每页显示的记录数
        multiselect : false, // 是否支持多选
        sortname : "id",// 根据哪个字段排序
        caption : "jqGrid表格测试", // 表格标题
        recordtext : "记录 {0} - {1} 总记录数 {2}",// 显示记录数的格式
        emptyrecords : "无数据",// 空记录时的提示信息
        loadtext : "获取数据中...",// 获得数据时的提示信息
        pgtext : "跳转第几页 {0} 总页数 {1}"// 页数显示格式
    });
    // 定义默认按键的显示
    // ,refresh刷新按钮是否显示、edit编辑按钮是否显示、add添加按钮是否显示、del删除按钮是否显示、refreshtitle刷新按钮提示信息
    $('#jqGirdList').jqGrid('navGrid', '#pager',
    {
        refresh : true,
        edit : true,
        add : true,
        del : true,
        search : true,
        refreshtitle : "刷新",
        edittitle : "修改",
        addtitle : "添加",
        deltitle : "删除",
        searchtitle : "搜索"
    });
}