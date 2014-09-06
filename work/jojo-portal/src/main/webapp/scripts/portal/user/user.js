

$(document).ready(
    function() {
//      jQuery.ajax( {
//        type : 'GET',
//        contentType : 'application/json',
//        url : 'user/list',
//        dataType : 'json',
//        success : function(data) {
//          if (data && data.success == "true") {
//            $('#info').html("共" + data.total + "条数据。<br/>");
//            $.each(data.data, function(i, item) {
//              $('#info').append(
//                  "编号：" + item.id + "，姓名：" + item.username
//                      + "，年龄：" + item.age);
//            });
//          }
//        },
//        error : function(e) {
//          alert("error info :" + e)
//        }
//      });
      $("#submit").click(function() {
        var jsonuserinfo = $.toJSON($('#form').serializeObject());
//        alert(jsonuserinfo);
        $.ajax( {
          type : 'POST',
          contentType : 'application/json',
          url : 'user/add',
          data : jsonuserinfo,
          dataType : 'json',
          success : function(data) {
            alert("新增成功！");
            //do sth more...
          },
          error : function(XMLHttpRequest, textStatus, errorThrown) {
        	  alert("error info :" + errorThrown)
          }
        });
      });
    });