<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<meta content="telephone=no" name="format-detection" />
<title>${news.title}_房产资讯-搜狐焦点${_city.cityName }房产</title>
<meta name="Keywords" content="${news.tagsAndKeyword }" />
<meta name="Description" content="${news.newsDesc }" />
<%@ include file="/views/icon.jsp" %>
<link rel="stylesheet" type="text/css" href="http://192.168.242.44/sceapp/focus_static/wap/common/css/css_mobile_common_v1.1.css">
<link rel="stylesheet" type="text/css" href="http://192.168.242.44/sceapp/focus_static/wap/phone/css/wap_public_v1.0.css">
<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/zepto-1.1.3.min.js"></script>
<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/phone/snippets/wap_common_v1.0.js"></script>
<script>
var city = ${city};
</script>
</head>
<body class="wap_hotquestion_all wap_baodian_info_all wap_zixun_all">
	<div class="wap_container">
		<!-- 资讯正文页—顶部导航 -->
		<header class="top_nav">
			<span class="search_title">资讯正文</span>
			<a class="go_home" href="/${_city.cityPinyinAbbr }/"></a>
		</header>
		
		<!-- 资讯正文页主要内容板块 // -->
		<section class="hotquestion_list_main zixunnews_main">
			<div class="hotquestion_list_container">	
				<section class="dynamic_info_container" data-title="资讯正文">
					<div class="dynamic_info_ask">
						<h1 class="its_biaoti">${news.title}</h1>
						<p class="its_writer_time">
						<c:if test="${not empty news.author }"><span class="fr">作者 : ${news.author}</span></c:if>
						<c:if test="${not empty news.sourceName }">消息来源: ${news.sourceName}</c:if>	
						</p>
						<c:if test="${not empty news.time }">
						<p class="its_writer_date">${news.time}</p>
						</c:if>
						<div class="borderTopBottom_e9e9e9_2"></div>
						<div class="dynamic_info_nr">
							<!-- 默认前面展示前三段话，后面产品可能还会动态调整第一次展示的段数，后端直接控制输出就行 -->
							<c:if test="${ not empty news.pageContent.newsSummary }"><p>摘要:${news.pageContent.newsSummary}</p></c:if>				
							${frontContent}
							<!-- 收起隐藏的文章内容 // -->
							<div class="hide_content_box">
								${backContent}
							</div>
							<!-- // 收起隐藏的文章内容 -->
						</div>
					</div>
					<c:if test="${ not empty backContent}">
					<div class="look_more unfold_surplus_content"><a href="javascript:;" role="text" url=""><em class="look_more_icon2"></em>展开剩余文章</a></div>
					</c:if>
				</section>
				<c:if test="${not empty infos}">				
					${infos}
				</c:if>
			</div>
		</section>
		
		<!-- 面包屑 -->
		<div class="link_boxs"><a href="/${_city.cityPinyinAbbr }/">新房</a><span class="h_space1">></span>资讯正文</div>
		
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