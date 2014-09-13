// 工程相对路径
var appRelPath = "";

var mainTabs = null;
// tab个数
var tabCounter = 0;

$.fn.tabIndex = function()
{
    return $(this).parent().find(this).index() - 1;
};
$.fn.selectTabByID = function(tabID)
{
    $(this).tabs("option", "active", $('#' + tabID).tabIndex());
};
$.fn.selectTabByIndex = function(tabIndex)
{
    $(this).tabs("option", "active", tabIndex);
};
String.prototype.replaceAll = function(regexp, replaceSTR)
{
    return this.replace(new RegExp(regexp, "gm"), replaceSTR);
}
/**
 * UTF-8 data encode / decode http://www.webtoolkit.info/
 */

var Utf8 =
{

    // public method for url encoding
    encode : function(string)
    {
        string = string.replace(/\r\n/g, "\n");
        var utftext = "";

        for (var n = 0; n < string.length; n++)
        {

            var c = string.charCodeAt(n);

            if (c < 128)
            {
                utftext += String.fromCharCode(c);
            }
            else if ((c > 127) && (c < 2048))
            {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            }
            else
            {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }

        }

        return utftext;
    },

    // public method for url decoding
    decode : function(utftext)
    {
        var string = "";
        var i = 0;
        var c = c1 = c2 = 0;
        while (i < utftext.length)
        {
            c = utftext.charCodeAt(i);
            if (c < 128)
            {
                string += String.fromCharCode(c);
                i++;
            }
            else if ((c > 191) && (c < 224))
            {
                c2 = utftext.charCodeAt(i + 1);
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            }
            else
            {
                c2 = utftext.charCodeAt(i + 1);
                c3 = utftext.charCodeAt(i + 2);
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }
        }
        return string;
    }
}
/**
*
* @param {jqObject} the field where the validation applies
* @param {Array[String]} validation rules for this field
* @param {int} rule index
* @param {Map} form options
* @return an error string if validation failed
*/
function checkAvbVal(field, rules, i, options,avbVal){
    if (field.val() != avbVal) {
        // this allows to use i18 for the error msgs
        return options.allrules.validate2fields.alertText;
    }
}


//将一个表单的数据返回成JSON对象
$.fn.serializeObject = function() {
var o = {};
var arr = this.serializeArray();
$.each(arr, function() {
  if (o[this.name]) {
    if (!o[this.name].push) {
      o[this.name] = [ o[this.name] ];
    }
    o[this.name].push(this.value || '');
  } else {
    o[this.name] = this.value || '';
  }
});
return o;
};

function split(val)
{
  return val.split(/,\s*/);
}
function extractLast(term)
{
  return split(term).pop();
}

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
    west__size : 240,
    west__initClosed : false,
    west__initHidden : false,
    east__size : 300,
    east__initClosed : false,
    east__initHidden : true
// 先隐藏右边栏
};

var tabTemplate = "<li id='#li_id'><a href='#href'>#label</a><span class='ui-icon ui-icon-close' role='presentation'>Remove Tab</span></li>";
// 增加新标签
// actual addTab function: adds new tab using the input from the form above
function addTab(tabs, tabLabel, tabContentHtml, theId)
{
    mainTabs = $(tabs);
    var label = tabLabel;
    var newTabId = "tabs-" + theId;
    if ($("#" + newTabId).length)
    {
        $("#" + newTabId).remove();
        // 首先检查一下是否已经加载过了同样的id的tab，如果加载过，则删除原来的，重新生成
        tabCounter = (tabCounter - 1);
        // close icon: removing the tab on click
        $("#tabs_Li_" + theId).children("span.ui-icon-close").trigger("click");
    }

    var liContent = tabTemplate.replace(/#li_id/g, 'tabs_Li_' + theId).replace(/#href/g, '#' + newTabId).replace(/#label/g, label).replaceAll("\n","").replaceAll("\r","");
    var liObj = $(liContent);
    $('#tabsUL').append(liObj);
    $('#tabs').append("<div id='" + newTabId + "' style='width:98%;'>" + tabContentHtml + "</div>");

    tabCounter = (tabCounter + 1);
    mainTabs.tabs("refresh");
    // 焦点指向这里
    $("#tabs").selectTabByID(newTabId);// worked.

//    $('#tabs_Li_' + theId).trigger("dblclick");
}

$(document).ready(function()
{
    try
    {
        initLayout();
        myLayout.loadState(stateResetSettings);
        // initJqGird();

        mainTabs = $('#tabs').tabs(
        // {
        // add: function(e, ui) {
        // // append close thingy
        // $(ui.tab).parents('li:first')
        // .append('<span class="ui-tabs-close ui-icon ui-icon-close" title="Close Tab"></span>')
        // .find('span.ui-tabs-close')
        // .show()
        // .click(function() {
        // maintab.tabs('remove', $('li', maintab).index($(this).parents('li:first')[0]));
        // });
        // // select just added tab
        // maintab.tabs('select', '#' + ui.panel.id);
        // }
        // }
        );
        tabCounter = mainTabs.length;

        // tab 标签
        // close icon: removing the tab on click
        mainTabs.delegate("span.ui-icon-close", "click", function()
        {
            var panelId = $(this).closest("li").remove().attr("aria-controls");
            $("#" + panelId).remove();
            mainTabs.tabs("refresh");
        });
        mainTabs.bind("keyup", function(event)
        {
            if (event.altKey && event.keyCode === $.ui.keyCode.BACKSPACE)
            {
                var panelId = mainTabs.find(".ui-tabs-active").remove().attr("aria-controls");
                $("#" + panelId).remove();
                mainTabs.tabs("refresh");
            }
        });
        // 拖拽改变次序
        mainTabs.find(".ui-tabs-nav").sortable(
        {
            axis : "x",
            stop : function()
            {
                mainTabs.tabs("refresh");
            }
        });

        // 增加top窗点击事件（第1、2级menu）

        bindMenuEvents();

        // 配置模态对话框
        $("#consoleDlg").dialog(
        {
            autoOpen : false, // 是否自动弹出窗口
            modal : true, // 设置为模态对话框
            resizable : true,
            width : window.screen.availWidth-300,
            height : window.screen.availHeight-200,
            position : "center" // 窗口显示的位置
        });

        // 左侧菜单栏
        var icons =
        {
            header : "ui-icon-circle-arrow-e",
            activeHeader : "ui-icon-circle-arrow-s"
        };
        $("#accordion").accordion(
        {
            icons : icons,
            heightStyle : "content"
        });
        $("#toggle").button().click(function()
        {
            if ($("#accordion").accordion("option", "icons"))
            {
                $("#accordion").accordion("option", "icons", null);
            }
            else
            {
                $("#accordion").accordion("option", "icons", icons);
            }
        });
        // 默认打开左侧菜单的第一个1级菜单
        var memnus1lv = $("[id^='menu1#']");
        var menus1Count = memnus1lv.length;
        if (menus1Count > 0)
        {
            $(memnus1lv[0]).trigger("click");
        }

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
    $("[id^='menu1#']").each(
            function()
            {
                $(this).bind(
                        "click",
                        function()
                        {
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
                            jsonData["dictCode"] = (($(this).attr("code").split("#")[1]) || '');
                            jsonData = $.toJSON(jsonData);
                            $.ajax(
                            {
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
                                    var divEl = $("div[id='div#" + menduId + "']");
                                    divEl.empty();
                                    for (var idx = 0; idx < josnArray.length; idx++)
                                    {
                                        // alert(josnArray[idx].theId + josnArray[idx].action);
                                        var lv2MenuHtml = "<p>" + "<span class='v-button-caption ' " + "id='menu2#"
                                                + josnArray[idx].theId + "'" + "menuName='" + josnArray[idx].theName
                                                + "'" + "uri='" + josnArray[idx].action + "'"
                                                // + "extraParams='" + josnArray[idx].extraParams + "'"
                                                + "><a href='###' style='color: #06c;' >" + josnArray[idx].theName
                                                + "</a></span>" + "</p>";
                                        divEl.append(lv2MenuHtml);
                                    }
                                    // do sth more...
                                    bind2LvMenuEvents();
                                },
                                error : function(XMLHttpRequest, textStatus, errorThrown)
                                {
                                    alert("error info :" + XMLHttpRequest.responseText)
                                }
                            });
                        });

            });

}

/**
 * 打开模态对话框，显示错误信息
 */
function showTipMessage(tipHtml,dlgTitle,extMsg)
{
    var consoleDlg = $("#consoleDlg");
    consoleDlg.empty();
    var infoV = tipHtml;// $("#globalErrDiv").html();
    consoleDlg.append(infoV);
    if (extMsg)
    {
        consoleDlg.append("<p class='errorTip'>"+extMsg+"</p>");
    }
    consoleDlg.dialog("option", "title", dlgTitle).dialog("open");

}

function bind2LvMenuEvents()
{
    // 2级菜单
    $("[id^='menu2#']").each(function()
    {
        // 先解除绑定
        $(this).unbind("click");
        $(this).bind("click", function()
        {
            var theId = ($(this).attr("id").split("#")[1]);
            // ajax 触发 点击2级菜单刷新3级左侧菜单栏
            var menuName = $(this).attr("menuName");
            // ajax 刷新action 到内容区域,如果有额外参数,通过 action附带
            var textData = $(this).attr("extraParams");
            textData = (textData == null ? "" : textData || '');
            var action = ($(this).attr("uri"));
            if (action.indexOf('/') == 0)
            {
                action = action.substring(1, action.length);
            }
            // alert(action);
            $.ajax(
            {
                type : 'POST',
                contentType : 'application/json',
                url : action,
                data : textData,
                dataType : 'html',
                success : function(dataResult)
                {
                    // 这里把ajax的结果(html内容)通过js替换dom中的元素
                    // 通过 jquery-UI 放入新的标签内
                    addTab(tabs, menuName, dataResult, theId);
                    // do sth more...
                },
                error : function(XMLHttpRequest, textStatus, errorThrown)
                {
                    alert(textStatus);
                    alert("error info :" + XMLHttpRequest.responseText)
                }
            });
        });
    });
}

// 已废弃
// function addMainTab(menuName, dataResult, theId)
// {
// $(mainTabs).tabs('add',("#tabs-"+theId), menuName);
// }

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
            center__minWidth : 300

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

/**
 * <summary> 生成 jqGird 的富客户端可编辑 list 表格, </summary>
 *
 * @param girdId
 *            表格dom元素的 id
 * @param listAction
 *            展示列表内容的 url
 * @param colNames
 *            列表表头,<br>
 *            形式为 [ "编号", "userName", "status"]
 * @param colModel
 *            返回list结果,json数组<br>
 *            形式为 colModel : [ { name : "id", index : "id", width : 60, sorttype : "int" }, { name : "userName", index :
 *            "userName", width : 100, sorttype : "string" }, { name : "status", index : "status", width : 90, sorttype :
 *            "string" } ]
 * @param sortname
 *            指定默认的排序列，可以是列名也可以是数字。此参数会在被传递到Server端;其他非默认的排序都在colModel中的sorttype属性设定.其值为java 列表对象中的属性,如'id'
 * @param caption
 *            表格标题
 */
function initJqGird(girdId, listAction, colNames, colModel, sortname, caption, btns, editUrl, clickEl,extDataRequest)
{
    $('#' + girdId).jqGrid(
            { // jqGrid固定的写法:$("#list").jqGrid({参数})
                contentType : 'application/json',
                datatype : "json", // 将这里改为使用JSON数据
                url : listAction, // 这是Action的请求地址，注意相对路径需要去掉最前面的一个'/'
                mtype : "post", // 提交类型
                prmNames :
                {
                    search : "search"
                },
                jsonReader :
                {
                    id : "0", // repeatitems为 false 时,给'0'
                    root : "rows",// json中代表实际模型数据的入口,即列表对象list<xxx>
                    page : "page",// json中代表当前页码的数据
                    total : "total",// json中代表页码总数的数据
                    records : "records", // json中代表数据行总数的数据
                    repeatitems : false, // 为 false 时传值不区分次序,只根据 name 获取,而所使用的name是来自于colModel中的name设定。
                    // id: "id", cell: "cell",
                    // //注：id/cell在repeatitems为true时可以用到，即每一个记录是由一对id和cell组合而成，即可以适用另一种json结构。
                    userdata : "userData"
                // , subgrid: {
                // root:"rows",
                // repeatitems: true,
                // cell:"cell"
                // }
                },

                height : "auto", // 表格高度
                width : window.screen.availWidth-150,//$('#' + girdId).width(), // 表格宽度
                shrinkToFit: true,
                autowidth : true,//考虑去掉宽度自适应，否则会挤窄列表，很难看
                // 表格结构定义
                colNames : colNames,
                colModel : colModel,
                pager : "#"+ girdId + "Pager", // 分页工具栏
                // imgpath : "themes/redmond/images", // 图片路径
                autoWidth : true,
                // rownumbers : true, // 是否显示列数
                viewrecords : true, // 是否显示行数
                rowNum : 20, // 每页默认显示记录数
                rowList : [ 10, 20, 30 ], // 可调整每页显示的记录数
                multiselect : false, // 是否支持多选
                sortname : sortname,// 根据哪个字段排序,如'id'
                caption : caption, // 表格标题
                recordtext : "记录 {0} - {1} 总记录数 {2}",// 显示记录数的格式
                emptyrecords : "无数据",// 空记录时的提示信息
                loadtext : "获取数据中...",// 获得数据时的提示信息
                pgtext : "跳转第几页 {0} 总页数 {1}"// 页数显示格式

                ,beforeRequest: function() {
                    if (extDataRequest)
                    {
                        var postData = $('#' + girdId).jqGrid('getGridParam','postData');
                        for ( var dataKey in extDataRequest)
                        {
                            if (extDataRequest[dataKey] && !postData[dataKey])
                            {
                                postData[dataKey] = extDataRequest[dataKey];
                            }
                        }
                        postData = $.toJSON(postData);
                        $('#' + girdId).jqGrid("setGridParam",postData);
                    }
                  }

                /** 增加数据行的操作按钮 */
                ,
                gridComplete : function(jqXHR, textStatus)
                {
                    var ids = $('#' + girdId).jqGrid('getDataIDs');
                    if (ids && btns)
                    {
                        for (var i = 0; i < ids.length; i++)
                        {
                            var cl = ids[i];
                            var be = btns.replaceAll("#rowid", cl);
                            $('#' + girdId).jqGrid('setRowData', ids[i],
                            {
                                act : be
                            // + se + ce
                            });
                        }
                    }
                },
                ondblClickRow : function(rowid)
                {
                    // 双击行
                    alert("You double click row with id: " + rowid);
                },
                onSelectRow : function(rowid)
                { // 单击选择行
                    var rowdata = $("#" + girdId).jqGrid('getRowData', rowid);
                    var key = rowdata.theId;
                    var datajson = {};
                    datajson['proDefId'] = (key);
                    datajson = $.toJSON(datajson);
                    // alert(datajson);
                    // 得到流程图片
                    if (clickEl)
                    {
                        $.ajax(
                        {
                            type : 'POST',
                            contentType : 'application/json',
                            url : 'workflow/locationWorkFlowGraph',
                            data : datajson,
                            dataType : 'json',
                            success : function(data)
                            {
                                // alert(appRelPath);
                                $("#extraDiv").empty();
                                // 在流程列表下面放置图片
                                var graphHTML = '<h2>流程图 <span style=\"display:hidden;\">x=' + data.x + ',y=' + data.y
                                        + '</span>' + '</h2><img style=\"border:1px solid #dddddd\" src=\"'
                                        + appRelPath + '/workflow/getWorkFlowGraph?proDefId=' + key + '\"/>';
                                $("#extraDiv").append(graphHTML);
                            },
                            error : function(XMLHttpRequest, textStatus, errorThrown)
                            {
                                alert("error info :" + XMLHttpRequest.responseText)
                            }
                        });
                    }
                }
                /** 增加加载后的处理 */
                ,
                loadComplete : function(data)
                {
                    // alert('loadComplete: ' + '\n' + data.status);
                    if (data.status != 200)
                    {
                        $("#tip").html((data.tip));
                        $("#tipDesc").html((data.tipDesc));
                        var errHtml = $("#globalErrDiv").html();
                        showTipMessage(errHtml);
                    }
                }

                /** 增加异常处理 */
                ,
                loadError : function(jqXHR, textStatus, errorThrown)
                {
//                     alert('HTTP status code: ' + jqXHR.status + '\n' +
//                     'textStatus: ' + textStatus + '\n' +
//                     'errorThrown: ' + errorThrown);
//                    alert('HTTP message body (jqXHR.responseText): ' + '\n' + jqXHR.responseText);
                    showTipMessage(jqXHR.responseText + "<p>" +  errorThrown,"出错了~~");
                }

                ,
                editurl : editUrl
            });
    // 定义默认按键的显示
    // ,refresh刷新按钮是否显示、edit编辑按钮是否显示、add添加按钮是否显示、del删除按钮是否显示、refreshtitle刷新按钮提示信息
//    $('#' + girdId).jqGrid('navGrid', '#pager',
//    {
//        refresh : true,
////        edit : true,
////        add : true,
//        del : true,
//        search : true,
//        refreshtitle : "刷新",
////        edittitle : "修改",
////        addtitle : "添加",
//        deltitle : "删除",
//        searchtitle : "搜索"
//    });
}


///**
//*
//* @param obj all values are Array
//* @param options value can be string or Array
//*/
//function concatJson(obj, options) {
// obj = formatSegment(obj);
// options = formatSegment(options);
//
// for (var i in options) {
//   // obj[key] exist and type is array
//   if (obj[i]) {
//     obj[i] = setConcat(obj[i], options[i]);
//   } else {
//     obj[i] = options[i];
//   }
// }
// return obj;
//}
//
///**
//*
//* @param obj all values are Array
//* @param options value can be string or Array
//*/
//function spliceJson(obj, options) {
// obj = formatSegment(obj);
// options = formatSegment(options);
//
// for (var i in options) {
//   if (obj[i]) {
//     for (var j in options[i]) {﻿﻿
//       for (var k in obj[i]) {
//         if (obj[i][k] == options[i][j]) {
//           obj[i].splice(k, 1);
//           break;
//         }
//       }
//     }
//   }
// }
// return obj;
//}
//
///**
//* {key: value}, all values should be Array
//* @param obj
//* @returns {*}
//*/
//function formatSegment(obj) {
// for (var i in obj) {
//   if (!(obj[i] instanceof Array)) {
//     obj[i] = [obj[i]];
//   }
// }
// return obj;
//}
//
///**
//* filter duplicate element
//* @param arr1
//* @param arr2
//* @returns {*}
//*/
//function setConcat(arr1, arr2) {
// if ((arr1 instanceof Array) && (arr2 instanceof Array)) {
//   var moreArr = [];
//   for (var i = 0; i < arr1.length; i++) {
//     for (var j = 0; j < arr2.length; j++) {
//       if (arr1[i] == arr2[j]) {
//         break;
//       }
//       if (j == arr2.length - 1) {
//         moreArr.push(arr1[i]);
//       }
//     }
//   }
//   return arr2.concat(moreArr);
// }
// return null;
//}