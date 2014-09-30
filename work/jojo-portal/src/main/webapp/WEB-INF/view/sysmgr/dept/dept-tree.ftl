<#-- 增加初始化 js -->
<script type="text/javascript" src="${rc.contextPath}/scripts/portal/sysmgr/dept/dept.js"></script>
<style>
  #user-list-table { list-style-type: none; margin: 0; padding: 0; width: 900px; }
  #user-list-table li { margin: 3px 3px 3px 0; padding: 1px; float: left; width: 150px; height: 90px; font-size: 1em; text-align: left; }
  body {
    min-width: 620px;
  }
  .column {
    width: 200px;
    float: left;
    padding-bottom: 100px;
  }
  .portlet {
    margin: 0 1em 1em 0;
    padding: 0.3em;
  }
  .portlet-header {
    padding: 0.2em 0.3em;
    margin-bottom: 0.5em;
    position: relative;
  }
  .portlet-toggle {
    position: absolute;
    top: 50%;
    right: 0;
    margin-top: -8px;
  }
  .portlet-content {
    padding: 0.4em;
  }
  .portlet-placeholder {
    border: 1px dotted black;
    margin: 0 1em 1em 0;
    height: 110px;
  }
  </style>

<div class="v-csslayout" style="width: 100%;">
	<div class="v-csslayout-margin">
 		<div id="dept_tree"></div>
	</div>
	<div id="deptUsrsDiv"></div>
</div>