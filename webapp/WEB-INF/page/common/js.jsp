<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Le javascript --> 
<!-- Placed at the end of the document so the pages load faster --> 
<script type="text/javascript" src="<c:url value='/boo/assets/js/lib/jquery.js'/>"></script> 
<script type="text/javascript" src="<c:url value='/boo/assets/js/lib/jquery-ui.js'/>"></script>
<script type="text/javascript" src="<c:url value='/boo/assets/js/lib/jquery.cookie.js'/>"></script> 
<%-- <script type="text/javascript" src="<c:url value='/boo/assets/js/lib/jquery.date.js'/>"></script>  --%>
<script type="text/javascript" src="<c:url value='/boo/assets/js/lib/jquery.mousewheel.js'/>"></script> 
<script type="text/javascript" src="<c:url value='/boo/assets/js/lib/jquery.load-image.min.js'/>"></script> 
<script type="text/javascript" src="<c:url value='/boo/assets/js/lib/bootstrap/bootstrap.js'/>"></script>  

<!-- Plugins Bootstrap -->
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/bootstrap-wysihtml5/lib/js/wysihtml5-0.3.0.min.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/bootstrap-wysihtml5/src/bootstrap-wysihtml5.js'/>"></script> 
<%-- <script type="text/javascript" src="<c:url value='/boo/assets/plugins/bootstrap-fuelux/all-fuelux.min.js'/>"></script>   --%>
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/bootstrap-timepicker/js/bootstrap-timepicker.js'/>"></script>  
<%-- <script type="text/javascript" src="<c:url value='/boo/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js'/>"></script>  --%>
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js'/>"></script> 
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/bootstrap-daterangepicker/js/bootstrap-daterangepicker.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/bootstrap-toggle-button/js/bootstrap-toggle-button.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/bootstrap-fileupload/js/bootstrap-fileupload.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/bootstrap-rowlink/js/bootstrap-rowlink.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/bootstrap-progressbar/js/bootstrap-progressbar.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/bootstrap-select/bootstrap-select.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/bootstrap-multiselect/js/bootstrap-multiselect.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/bootstrap-bootbox/bootbox.min.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/bootstrap-modal/js/bootstrap-modalmanager.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/bootstrap-modal/js/bootstrap-modal.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/bootstrap-wizard/js/bootstrap-wizard.min.js'/>"></script> 
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/bootstrap-wizard-2/js/bwizard-only.min.js'/>"></script> 
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/bootstrap-image-gallery/js/bootstrap-image-gallery.min.js'/>"></script> 
 
<!-- Plugins Custom - Only example --> 
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-extension/google-code-prettify/prettify.js'/>"></script> 

<!-- Plugins Custom - System --> 
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-system/nicescroll/jquery.nicescroll.min.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-system/xbreadcrumbs/xbreadcrumbs.js'/>"></script>  

<!-- Plugins Custom - System info -->
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-system-info/qtip2/dist/jquery.qtip.min.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-system-info/gritter/js/jquery.gritter.min.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-system-info/notyfy/jquery.notyfy.js'/>"></script> 

<!-- Plugins Custom - Content -->
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-content/list/js/list.min.js'/>"></script>  
<%-- <script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-content/list/plugins/list.paging.min.js'/>"></script>  --%>
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-content/jpages/js/jPages.js'/>"></script>  

<!-- Plugins Custom - Component -->
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-component/fullcalendar/fullcalendar.min.js'/>"></script> 
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-component/rangeslider/jqallrangesliders.min.js'/>"></script> 

<!-- Plugins Custom - Form -->
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-form/uniform/jquery.uniform.min.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-form/select2/select2.min.js'/>"></script> 
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-form/counter/jquery.counter.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-form/elastic/jquery.elastic.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-form/inputmask/jquery.inputmask.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-form/inputmask/jquery.inputmask.extensions.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-form/validate/js/jquery.validate.min.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-form/duallistbox/jquery.duallistbox.min.js'/>"></script> 

<!-- Plugins Custom - Gallery --> 
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-gallery/nailthumb/jquery.nailthumb.1.1.min.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-gallery/nailthumb/showLoading/js/jquery.showLoading.min.js'/>"></script> 
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-gallery/wookmark/jquery.imagesloaded.js'/>"></script> 
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-gallery/wookmark/jquery.wookmark.min.js'/>"></script> 
 
<!-- Plugins Tables --> 
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-table/datatables/media/js/jquery.dataTables.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-table/datatables/plugin/jquery.dataTables.plugins.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-table/datatables/plugin/jquery.dataTables.columnFilter.js'/>"></script>  

<!-- Plugins data visualization --> 
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-visualization/sparkline/jquery.sparkline.min.js'/>"></script>  
<%-- <script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-visualization/easy-pie-chart/jquery.easy-pie-chart.js'/>"></script>   --%>
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-visualization/percentageloader/percentageloader.min.js'/>"></script> 
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-visualization/knob/knob.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-visualization/flot/jquery.flot.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-visualization/flot/jquery.flot.categories.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-visualization/flot/jquery.flot.grow.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-visualization/flot/jquery.flot.orderBars.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-visualization/flot/jquery.flot.pie.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-visualization/flot/jquery.flot.resize.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-visualization/flot/jquery.flot.selection.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-visualization/flot/jquery.flot.stack.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-visualization/flot/jquery.flot.stackpercent.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/pl-visualization/flot/jquery.flot.time.js'/>"></script>  
<!-- ztree  -->
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/ztree/js/jquery.ztree.core-3.5.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/ztree/js/jquery.ztree.excheck-3.5.js'/>"></script>  
<!-- 日期插件 -->
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/My97DatePicker/WdatePicker.js'/>"></script>  

<script type="text/javascript" src="<c:url value='/boo/assets/plugins/dialog/artDialog.source.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/dialog/plugins/iframeTools.source.js'/>"></script>

<!-- main js --> 
<script type="text/javascript" src="<c:url value='/boo/assets/js/core.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/boo/assets/js/application.js'/>"></script>    
<script type="text/javascript" src="<c:url value='/boo/assets/js/demo/demo-modal.js'/>"></script>    
<script type="text/javascript" src="<c:url value='/js/colResizable-1.3.min.js'/>"></script>    
<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>    
<script type="text/javascript" src="<c:url value='/js/page.js'/>"></script>    
<!-- 省市区 -->
<script type="text/javascript" src="<c:url value='/js/area.js'/>"></script>    