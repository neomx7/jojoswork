/**
 * 部门树选择
 */
$(function () {
    var to = false;
    $('#demo_q').keyup(function () {
        if(to) { clearTimeout(to); }
        to = setTimeout(function () {
            var v = $('#demo_q').val();
            $('#jstree_demo').jstree(true).search(v);
        }, 250);
    });

    $('#jstree_demo').jstree({
      "core" : {
        "animation" : 1,
        "check_callback" : true,
        "themes" : { "stripes" : true },

//        "data" : {
//          'url' : function (node) {
//            var targetURL= ( node.id === '#' ?
//              'ajax_demo_roots.json'
//              : 'ajax_demo_children.json');
//            return targetURL;
//          },
//          'data' : function (node) {
//            return { 'id' : node.id };
//          }
//        }

        "data" : [
        {
            "text" : "Same but with checkboxes",
            "children" : [
                { "text" : "initially selected", "state" : { "selected" : true } },
                { "text" : "custom icon URL", "icon" : "http://jstree.com/tree-icon.png" },
                { "text" : "initially open", "state" : { "opened" : true }, "children" : [ "Another node" ] },
                { "text" : "custom icon class", "icon" : "glyphicon glyphicon-leaf" }
            ]
        },
        "And wholerow selection"
    ]


      },
      "types" : {
        "#" : {
          "max_children" : 1,
          "max_depth" : 4,
          "valid_children" : ["root"]
        },
        "root" : {
         // "icon" : "/static/3.0.3/assets/images/tree_icon.png",
          "valid_children" : ["default"]
        },
        "default" : {
          "valid_children" : ["default","file"]
        },
        "file" : {
          "icon" : "glyphicon glyphicon-file",
          "valid_children" : []
        }
      },
      "plugins" : [
        "search",
//        "dnd", //拖拽节点
        "state",
        "types",
        "wholerow"
      ]
    });
});