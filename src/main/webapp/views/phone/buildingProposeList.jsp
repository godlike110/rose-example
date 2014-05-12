<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<meta content="telephone=no" name="format-detection" />
<title>【楼盘导购|选房技巧|楼盘选购】-搜狐焦点${_city.cityName }房产</title>
<meta name="Keywords" content="楼盘导购,选房技巧，楼盘选购">
<meta name="Description" content="搜狐焦点${_city.cityName }房产楼盘导购频道为您提供真实、海量的楼盘信息，根据您的需求量身定制楼盘选购文章，告诉您选房技巧，包括如何选好房以及好房子的标准是什么等信息，让您在最短的时间内选出最适合您的房子。 ">
<%@ include file="/views/icon.jsp" %>
<link rel="stylesheet" type="text/css" href="http://192.168.242.44/sceapp/focus_static/wap/common/css/css_mobile_common_v1.1.css">
<link rel="stylesheet" type="text/css" href="http://192.168.242.44/sceapp/focus_static/wap/phone/css/wap_public_v1.0.css">
<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/zepto-1.1.3.min.js"></script>
<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/iscroll_v4.2.5.js"></script>
<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/phone/snippets/wap_common_v1.0.js"></script>
<script>
var city = ${city};
</script>
</head>
<body class="wap_daogou_all wap_baodian_all">
	<div class="wap_container scroller" id="wrapper">
		<div id="scroller">
			<!-- 楼盘导购列表页—顶部导航 -->
			<header class="top_nav">
			
					<a href="${ctx }/${_city.cityPinyinAbbr }/" class="go_back" data-title="返回上一级">首页</a>
		
				<h1 class="search_title">楼盘导购</h1>
				<span class="menu_list" role="main_menu" role-addclass="its_open" data-title="展开列表"></span>
				<ul class="nav_box_ul1 main_menu_ul1">
					<li><a href="${ctx }/${_city.cityPinyinAbbr }/loupan/" url="" role="text">楼盘</a></li>
					<li><a href="${ctx }/${_city.cityPinyinAbbr }/daogou/" url="" role="text" role-current="current" class="current">导购</a></li>
					<li><a href="${ctx }/${_city.cityPinyinAbbr }/xinwen/" url="" role="text">新闻</a></li>
					<li><a href="${ctx }/${_city.cityPinyinAbbr }/baodian/" url="" role="text">宝典</a></li>
				</ul>
			</header>
			
			<!-- 楼盘导购列表页主要内容板块 // -->
			<section class="hotquestion_list_main">
				<div class="hotquestion_list_container">	
					<section class="dynamic_info_container" data-title="楼盘导购列表">
						<div class="dynamic_info_ask">
							<ul class="baodian_ul1">
								<c:forEach var="bp" items="${bpList}" varStatus="status">
								<li class="dynamic_info_nr">
									<a href="${ctx }/${_city.cityPinyinAbbr }/daogou/${bp.id}/" role="text" url="">
										<h2 class="its_biaoti">${bp.title }</h2>
										<p class="its_writer_time"><span class="fr">${bp.pubDate }</span>编辑：${bp.editorName }</p>
										<div class="img_box1"><img src="${bp.pic }" alt="${bp.title}" width="300" height="150" /></div>
										<p class="its_summary">相关楼盘：${bp.buildingNameShow}</p>
									</a>
								</li>
								</c:forEach>
								
							</ul>
						</div>
						<!-- pull up -->
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
			<div class="link_boxs"><a href="/${_city.cityPinyinAbbr }/">新房</a><span class="h_space1">></span>楼盘导购</div>
			
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
		</div>
	</div>
	
	<div class="back2top" data-title="返回顶部"></div>
	
	<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/phone/snippets/wap_daogou_list_v1.0.js"></script>
	<%@ include file="/views/pv.jsp"%>
</body>
</html>