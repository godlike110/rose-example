<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<meta content="telephone=no" name="format-detection" />
<title>  ${building.projName}最新动态-${_city.cityName }${building.projName}楼盘资讯-搜狐焦点</title>
<meta name="keywords" content="${building.projName}最新动态，${_city.cityName }${building.projName}楼盘资讯" >
<meta name="description" content="${_city.cityName }${building.projName}最新动态为您提供最新、最及时的${building.projName}最新动态信息，${building.projName}相关资讯。让您更快捷、更方便的了解${building.projName}相关信息，${building.projName}更多信息尽在搜狐焦点网">
<%@ include file="/views/icon.jsp" %>
<link rel="stylesheet" type="text/css" href="http://192.168.242.44/sceapp/focus_static/wap/common/css/css_mobile_common_v1.1.css">
<link rel="stylesheet" type="text/css" href="http://192.168.242.44/sceapp/focus_static/wap/phone/css/wap_public_v1.0.css">
<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/zepto-1.1.3.min.js"></script>
<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/iscroll_v4.2.5.js"></script>
<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/phone/snippets/wap_common_v1.0.js"></script>
<script>
var city = ${city};
var groupId = ${building.groupId}; 
var phone400 = ${building.phone400};
</script>

</head>
<body class="wap_hotquestion_all wap_hotquestion_all2">
	<div class="wap_container scroller" id="wrapper">
		<div id="scroller">
		
		<!-- 最新动态页—顶部导航 -->
		<header class="top_nav">
			<c:choose>
						<c:when test="${returnFlag == 1}">
								<a href="${ctx }/${_city.cityPinyinAbbr }/" class="go_back" data-title="返回上一级">首页</a>
						</c:when>
						<c:when test="${returnFlag == 0}">
								<a href="javascript:history.back();" class="go_back" data-title="返回上一级">返回</a>
						</c:when>
			</c:choose>		
			<span class="search_title">最新动态</span>			
		</header>
		
		<!-- 最新动态页主要内容板块 // -->
		<section class="hotquestion_list_main">
			<div class="hotquestion_list_container">				
				<section class="hotquestion_container" data-title="动态">
					<ul class="hotquestion_list_ul">
						<c:forEach var="rw" items="${recentNewsList}" varStatus="status">
						<li>
							<a href="${ctx }/${_city.cityPinyinAbbr }/loupan/${building.groupId}/dongtai/${rw.infoid}/" role="text" url="${ctx }/${_city.cityPinyinAbbr }/loupan/${building.groupId}/dongtai/${rw.infoid}/" class="clearfix">
								<div class="fl its_question_icon"><em class="dynamic_icon"></em></div>
								<div class="its_content">
									<div class="ask_paragram2">
										<p>${rw.infoname }</p>
										<div class="ask_paragram2_date">${rw.infotime }</div>
									</div>
									<p class="answer_paragram">${rw.infocontent }</p>
								</div>
							</a>
						</li>
						</c:forEach>
					</ul>
					<c:if test="${hasNext}">
					<div class="pull font_gray">
						<div class="icon fl"></div>
						<p>滑动加载更多</p>
					</div>
					</c:if>
				</section>
			</div>
		</section>
		
		<!-- 面包屑 -->
			<div class="link_boxs"><a href="/${_city.cityPinyinAbbr }/">新房</a>
			<span class="h_space1">></span><a href="${ctx }/${_city.cityPinyinAbbr }/loupan/">${_city.cityName }楼盘</a>
			<span class="h_space1">></span><a href="${ctx }/${_city.cityPinyinAbbr }/loupan/${building.groupId}/">
			<c:choose>
				   <c:when test="${fn:length(building.projName) > 4}">
						  ${fn:substring(building.projName, 0, 4)}...
				   </c:when>
				   <c:otherwise>
				   		  ${building.projName }
				   </c:otherwise>
			</c:choose>
			</a>
			<span class="h_space1">></span>楼盘动态</div>
		
		</div>
	</div>
		
	<div class="back2top" data-title="返回顶部"></div>
	
	<!-- 400电话 -->
	<!-- 400电话 -->
		<c:if test="${building.phone400 != 0 }">
		<div class="hotline_phone">
			<a href="tel:4008882200,${building.phone400}" class="hotline_phone_btn" phone="${building.phone400}"><span class="pnone_icon"></span>免费咨询 400-888-2200 转 ${building.phone400}</a>
		</div>
		</c:if>			
		<c:if test="${building.phone400==0 }">
			<div class="hotline_phone">
				<a href="tel:4006782020" class="hotline_phone_btn" phone="${building.phone400}"><span class="pnone_icon"></span>免费咨询 400-678-2020</a>
			</div>
		</c:if>
	<!-- 电话弹层 // -->
	<div class="over"></div>
	<div class="alert_box" id="check_net">
     	<p class="p1">请在接通后拨分机号</p>
     	<p class="p1">${building.phone400 }</p>
     	<div class="itos_btn1" id="check_net_confirm"><a href="javascript:;">好的</a></div>
		<div class="its_close_btn"></div>
    </div>
	<!-- // 电话弹层 -->
	
	<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/phone/snippets/wap_dynamic_list_v1.0.js"></script>
	<%@ include file="/views/pv.jsp"%>
</body>
</html>