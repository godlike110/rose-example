<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
		<meta content="telephone=no" name="format-detection" />
		<title>【楼盘导购|选房技巧|楼盘选购】-搜狐焦点${_city.cityName }房产</title>
		<meta name="Keywords" content="楼盘导购,选房技巧，楼盘选购">
		<meta name="Description" content="搜狐焦点${_city.cityName }房产楼盘导购频道为您提供真实、海量的楼盘信息，根据您的需求量身定制楼盘选购文章，告诉您选房技巧，包括如何选好房以及好房子的标准是什么等信息，让您在最短的时间内选出最适合您的房子。 ">
		<%@ include file="/views/icon.jsp" %>
		<link rel="stylesheet" href="http://192.168.242.44/sceapp/focus_static/wap/common/css/css_mobile_common_v1.1.css" />
		<link rel="stylesheet" href="http://192.168.242.44/sceapp/focus_static/wap/pad/css/index.css" />
		<script src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/zepto-1.1.3.min.js"></script>
		<script>
			var city = ${city};
		</script>
	</head>
	
	<body>
		<section class="container" id="baodian_wrapper">
			<div class="scroller">
				<header class="clearfix">
		
						<a href="${ctx }/${_city.cityPinyinAbbr }/" class="back fl" >首页</a>
	
					<h1>楼盘导购</h1>
					<div class="menu menu_right fr" id="menu">
						<a href="javascript:;" class="choose">选择</a>
						<div class="menu_list">
							<a href="${ctx }/${_city.cityPinyinAbbr }/loupan/" role="touch_bg">楼盘</a>
							<a href="${ctx }/${_city.cityPinyinAbbr }/daogou/" role="touch_bg" class="current">导购</a>
							<a href="${ctx }/${_city.cityPinyinAbbr }/xinwen/" role="touch_bg">新闻</a>
							<a href="${ctx }/${_city.cityPinyinAbbr }/baodian/" role="touch_bg">宝典</a>
						</div>
					</div>
				</header>
				
				<section class="content clearfix">
					<article class="no_padd">
						<ul class="article_list article_img article_img2 article_line p20">
							<c:forEach var="bp" items="${bpList}" varStatus="status">
							<li role="touch_bg">
								<a href="${ctx }/${_city.cityPinyinAbbr }/daogou/${bp.id}/" class="clearfix">
									<img src="${bp.pic}" alt="${bp.title}" class="img2 fl" />
									<dl class="magin">
										<dt><h2>${bp.title }</h2></dt>
										<dd>相关楼盘：${bp.buildingNameShow}</dd>
										<dd class="seprate2">编辑：${bp.editorName}<span>${bp.pubDate }</span></dd>
									</dl>
								</a>
							</li>
							</c:forEach>
						</ul>
						<c:if test="${hasNext}">
						<div class="line line_nop"></div>
						<a href="javascript:;" class="load_more">滑动加载更多</a>
						</c:if>
					</article>
				</section>

				<section class="crumb">
				<div class="inner">
					<a href="/${_city.cityPinyinAbbr }/">新房</a>&gt; 楼盘导购
				</div>
				</section>	
				
				<footer class="clearfix">
					<span class="copyright">&copy;2013搜狐焦点</span>
					<div class="switch_version fr">
						<a href="javascript:;" class="version">Pad版</a>
						<div class="version_list">
							<a role="touch_bg" href="" class="phone">手机版</a>
							<a role="touch_bg" href="" class="ipad current">Pad版</a>
							<a role="touch_bg" href="" class="pc">电脑版</a>
						</div>
					</div>
					<a href="javascript:;" id="to_top">返回顶部</a>
				</footer>
			</div>
		</section>
		
		<!-- js -->
		<script src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/iscroll_v4.2.5.js"></script>
		<script src="http://192.168.242.44/sceapp/focus_static/wap/pad/snippets/sohu.focus.mobile.common.gh_1.0.js"></script>
		<script src="http://192.168.242.44/sceapp/focus_static/wap/pad/snippets/sohu.focus.mobile.daogou.gh_1.0.js"></script>
		<%@ include file="/views/pv.jsp"%>
	</body>
</html>