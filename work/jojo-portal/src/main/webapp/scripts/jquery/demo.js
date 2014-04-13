$(document).ready(function(){
    //$('#switcher').themeswitcher();

	$('body').layout({
		resizerClass: 'ui-state-default',
        west__onresize: function (pane, $Pane) {
            $("#west-grid").jqGrid('setGridWidth',$Pane.innerWidth()-2);
		}
	});
	$.jgrid.defaults = $.extend($.jgrid.defaults,{loadui:"enable"});
	var maintab =$('#tabs','#RightPane').tabs({
        add: function(e, ui) {
            // append close thingy
            $(ui.tab).parents('li:first')
                .append('<span class="ui-tabs-close ui-icon ui-icon-close" title="Close Tab"></span>')
                .find('span.ui-tabs-close')
				.show()
                .click(function() {
                    maintab.tabs('remove', $('li', maintab).index($(this).parents('li:first')[0]));
                });
            // select just added tab
            maintab.tabs('select', '#' + ui.panel.id);
        }
    });
    $("#west-grid").jqGrid({
        url: "tree.xml",
        datatype: "xml",
        height: "auto",
        pager: false,
        loadui: "disable",
        colNames: ["id","Items","url"],
        colModel: [
            {name: "id",width:1,hidden:true, key:true},
            {name: "menu", width:150, resizable: false, sortable:false},
            {name: "url",width:1,hidden:true}
        ],
        treeGrid: true,
		caption: "jqGrid Demos",
        ExpandColumn: "menu",
        autowidth: true,
        //width: 180,
        rowNum: 200,
        ExpandColClick: true,
        treeIcons: {leaf:'ui-icon-document-b'},
        onSelectRow: function(rowid) {
            var treedata = $("#west-grid").jqGrid('getRowData',rowid);
            if(treedata.isLeaf=="true") {
                //treedata.url
                var st = "#t"+treedata.id;
				if($(st).html() != null ) {
					maintab.tabs('select',st);
				} else {
					maintab.tabs('add',st, treedata.menu);
					//$(st,"#tabs").load(treedata.url);
					$.ajax({
						url: treedata.url,
						type: "GET",
						dataType: "html",
						complete : function (req, err) {
							$(st,"#tabs").append(req.responseText);
							try { var pageTracker = _gat._getTracker("UA-5463047-4"); pageTracker._trackPageview(); } catch(err) {};
							var clck = '<p style="border: 1px solid; background-color: lemonchiffon; width:654px;height:25px;margin-bottom: 8px;padding-top: 8px;text-align: center">';
							clck += '<b> Please, support the jqGrid project by clicking on our sponsors ad!</b></p>';
                                                        //var click = ' ';
							var fs = '<iframe src="adds.html" style="width:336px; height:290px;" scrolling="no" marginwidth="0" marginheight="0" frameborder="0" vspace="0" hspace="0"/>\
									&nbsp;&nbsp;&nbsp;&nbsp;<iframe src="adds3.html" style="width:336px; height:290px;" scrolling="no" marginwidth="0" marginheight="0" frameborder="0" vspace="0" hspace="0"/>\
									<br/><iframe src="adds2.html" style="width:728px; height:95px;" scrolling="no" marginwidth="0" marginheight="0" frameborder="0" vspace="0" hspace="0"/><br>\
									&nbsp;&nbsp;&nbsp;&nbsp;<iframe src="adds4.html" style="width:728px; height:95px;"scrolling="no" marginwidth="0" marginheight="0" frameborder="0" vspace="0" hspace="0"/>\
							';
							$(st,"#tabs").append(clck);
							$(st,"#tabs").append(fs);
						}
					});
				}
            }
        }
    });
	
// end splitter

});