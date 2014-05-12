<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
		<meta content="telephone=no" name="format-detection" />
		<title>【${_city.cityName }${group_name }楼盘】-搜狐焦点${_city.cityName }房产</title>
		<meta name="Keywords" content="${group_name }楼盘">
		<meta name="Description" content="搜狐焦点${_city.cityName }房产网为广大网友提供了最新的${group_name }楼盘信息，最准确的${group_name }房价情况和最及时的${group_name }新房资讯等，是买房、购房首选网站。 ">
		<%@ include file="/views/icon.jsp" %>
		<link rel="stylesheet" href="http://192.168.242.44/sceapp/focus_static/wap/common/css/css_mobile_common_v1.1.css" />
		<link rel="stylesheet" href="http://192.168.242.44/sceapp/focus_static/wap/pad/css/index.css" />
		<script src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/zepto-1.1.3.min.js"></script>
		<script>
			var city = ${cityStr};
		</script>
	</head>
	
	<body class="bg_all">
		<section class="container">
				<header class="clearfix">
		
					 <a href="${ctx }/${_city.cityPinyinAbbr }/" class="back fl" >首页</a>
	
					<h1>搜索</h1>
					<div class="menu menu_right fr" id="menu">
						<a href="javascript:;" class="choose">选择</a>
						<div class="menu_list">
							<a href="${ctx }/${_city.cityPinyinAbbr }/loupan/" role="touch_bg" class="current">楼盘</a>
							<c:if test="${totalDaogou > 0 }">
							<a href="${ctx}/${_city.cityPinyinAbbr}/daogou/" role="touch_bg">导购</a>
							</c:if>
							<a href="${ctx }/${_city.cityPinyinAbbr }/xinwen/" role="touch_bg">新闻</a>
							<a href="${ctx }/${_city.cityPinyinAbbr }/baodian/" role="touch_bg" >宝典</a>
						</div>
					</div>
				</header>
				
				<section class="content">
					<article class="article_search article_search_no">
						<div class="search_wrap">
							<form name="searchForm" method="post" action="${ctx }/${_city.cityPinyinAbbr }/search/list/" class="search_form clearfix">
								<div class="form_item">
									<input type="text" name="group_name" placeholder="${group_name }" autocomplete="off" value="${group_name }" />
								</div>
								<input type="button" value="搜索" />
								<a href="javascript:;" class="empty">清空</a>
							</form>
							<div class="suggest">
								<div class="suggest_item suggest_tip">
									<span>历史记录</span><a class="sempty fr" href="javascript:;">清空</a>
								</div>
							</div>
						</div>
						<div class="noresult">
							<div class="inner">
								<div>没有找到符合<h1 class="search_text">${group_name }</h1>的楼盘，请修改条件重新搜索。</div>
							</div>
						</div>
					</article>
				</section>
				
				<footer class="position_fixed">
					<span class="copyright">&copy;2013搜狐焦点</span>
					<div class="switch_version">
						<a href="javascript:;" class="version">Pad版</a>
						<div class="version_list">
							<a href="" role="touch_bg" class="search_phone">手机版</a>
							<a href="" role="touch_bg" class="search_ipad current">Pad版</a>
							<a href="" role="touch_bg" class="search_pc">电脑版</a>
						</div>
					</div>
				</footer>
		</section>
		
		<!-- js -->
		<script src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/iscroll_v4.2.5.js"></script>
		<script src="http://192.168.242.44/sceapp/focus_static/wap/pad/snippets/sohu.focus.mobile.common.gh_1.0.js"></script>
		<script src="http://192.168.242.44/sceapp/focus_static/wap/pad/snippets/sohu.focus.mobile.search.gh_1.0.js"></script>
		<%@ include file="/views/pv.jsp"%>
	</body>
</html>