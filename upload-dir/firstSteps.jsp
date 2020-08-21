<head><META HTTP-EQUIV="REFRESH" CONTENT="300;URL=../m/FirstSteps"></head>
<%@include file="../xava/imports.jsp"%>
<%@page import="org.openxava.util.Users"%>
<%@page import="org.openxava.util.Is"%>
<%@page import="org.openxava.web.Browsers"%> 

<% 
String userName = Users.getCurrentUserInfo().getId();
if (Is.equal(userName,"timeclock")) { 
	if ("true".equals(request.getParameter("init"))) {
%>
	<div id="first_steps">
	<script type='text/javascript' src='../naviox/js/webcam.min.js'/>
	<br/><br/><br/>
	<div align="center">
		<input type="button" tabindex="1" style="box-shadow: 0px 1px 3px #033649;font-size: 25px;padding: 15px;background: #033649;color: #e8ddcb;box-shadow: 0px 1px 3px #033649;" onclick="javascript:take_snapshot_in();" value="<xava:label key='Clock-In'/>" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" tabindex="2" style="box-shadow: 0px 1px 3px #033649;font-size: 25px;padding: 15px;background: #033649;color: #e8ddcb;box-shadow: 0px 1px 3px #033649;" onclick="javascript:take_snapshot_out();" value="<xava:label key='Clock-Out'/>" />
	</div>
	<script language="JavaScript">
	//console.log("Before Webcam set: ", Webcam)
	Webcam.set({width: 320,height: 240,image_format: 'jpeg',jpeg_quality: 90,force_flash:true});
	Webcam.attach( '#my_camera' )

	function take_snapshot_in() {
		Webcam.snap( function(data_uri) {
					openxava.executeAction('ERP', 'FirstSteps', '', false, 'FirstSteps.Clock-In');
					setTimeout(function(){
						document.getElementById("ox_ERP_FirstSteps__label_imgInPic").style.display="none";
						document.getElementById("ox_ERP_FirstSteps__imgInPic").style.display="none";
						document.getElementById("ox_ERP_FirstSteps__imgInPic").value='<img src="'+data_uri+'"/>';
						//document.getElementById("ox_ERP_FirstSteps__bottom_buttons").style.display="none";
						//document.getElementById("ox_ERP_FirstSteps__ClockIn___Clock-In").style.display="block";
					}, 2000);
				}
		);
	}

	function take_snapshot_out() {
		Webcam.snap( function(data_uri) {
			openxava.executeAction('ERP', 'FirstSteps', '', false, 'FirstSteps.Clock-Out');
			setTimeout(function(){
				document.getElementById("ox_ERP_FirstSteps__label_imgOutPic").style.display="none";
				document.getElementById("ox_ERP_FirstSteps__imgOutPic").style.display="none";
				document.getElementById("ox_ERP_FirstSteps__imgOutPic").value='<img src="'+data_uri+'"/>';
				}, 2000);
			}
		);
	}
	</script>
	<div id="my_camera" style="display:none"></div>
	</div>
	<% } else {

	%>
	<br/><br/><br/>
	<div align="center">
		<input type="button" tabindex="1" 
		style="box-shadow: 0px 1px 3px #033649;font-size: 25px;padding: 15px;background: #033649;color: #e8ddcb;
		box-shadow: 0px 1px 3px #033649;" onclick="window.location='FirstSteps?init=true'" value="<xava:label key='Time Clock'/>" />
	</div>
	<% } %>
<% } %>
	