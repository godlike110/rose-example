<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<meta content="telephone=no" name="format-detection" />
<title>${housingGuide.title }_购房宝典-搜狐焦点${_city.cityName }房产</title>
<meta name="keywords" content="购房宝典，购房指南"/>
<meta name="description" content="${housingGuide.summary}"/>
<%@ include file="/views/icon.jsp" %>
<link rel="stylesheet" type="text/css" href="http://192.168.242.44/sceapp/focus_static/wap/common/css/css_mobile_common_v1.1.css">
<link rel="stylesheet" type="text/css" href="http://192.168.242.44/sceapp/focus_static/wap/phone/css/wap_public_v1.0.css">
<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/zepto-1.1.3.min.js"></script>
<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/phone/snippets/wap_common_v1.0.js"></script>
</head>
<body class="wap_hotquestion_all wap_baodian_info_all wap_baodian_info_self">
	<div class="wap_container">
		<!-- 宝典正文页—顶部导航 -->
		<header class="top_nav">
			<c:choose>
						<c:when test="${returnFlag == 1}">
								<a href="${ctx }/${_city.cityPinyinAbbr }/" class="go_back" data-title="返回上一级">首页</a>
						</c:when>
						<c:when test="${returnFlag == 0}">
								<a href="javascript:history.back();" class="go_back" data-title="返回上一级">返回</a>
						</c:when>
			</c:choose>		
			<span class="search_title">宝典正文</span>
			<a class="go_home" href="${ctx }/${_city.cityPinyinAbbr }/"></a>
			<script>
			var city = ${city};
			</script>
		</header>
		
		<!-- 宝典正文页主要内容板块 // -->
		<section class="baodian_info_main">
			<div class="baodian_info_container">	
				<section class="dynamic_info_container" data-title="宝典正文">
					<div class="dynamic_info_ask">
						<h1 class="its_biaoti">${housingGuide.title }</h1>
						<p class="its_writer_time">${housingGuide.catagoryName }<span class="h_space2">
						<fmt:formatDate value="${housingGuide.createTime }" pattern="yyyy年M月d日"/>
						</span></p>
						<div class="dynamic_info_nr">
							<div class="img_box1"><img src="${housingGuide.picUrl}" alt="${housingGuide.title }" /></div>
							<p>${housingGuide.content }</p>
						</div>
					</div>
				</section>
				
			</div>
		</section>
		
		<!-- 面包屑 -->
		<div class="link_boxs"><a href="/${_city.cityPinyinAbbr }/">新房</a><span class="h_space1">></span><a href="${ctx }/${_city.cityPinyinAbbr }/baodian/">购房宝典</a><span class="h_space1">></span>购房宝典正文</div>
		
		<!-- 底部导航 // -->
		<footer class="foot_nav">
			<div class="foot_nav_copyright">©2013 搜狐焦点</div>
			<div class="foot_nav_box2">
				<span class="wap_version" role="wap_version_menu">手机版</span>
				<ul class="nav_box_ul1 wap_version_menu_ul1">
					<c:if test="${mobile=='false'}">
							<li><a href="#" role="wap_version_text" url="" data-nr="PAD">Pad版</a></li>
					</c:if>
					<li><a href="#" role="wap_version_text" url="" data-nr="PHONE" class="current">手机版</a></li>
					<li><a href="#" role="wap_version_text" url="" data-nr="PC">电脑版</a></li>
				</ul>
			</div>
		</footer>
		<!-- // 底部导航 -->
		
		<div class="back2top" data-title="返回顶部"></div>
		
	</div>
	<%@ include file="/views/pv.jsp"%>
</body>
</html>