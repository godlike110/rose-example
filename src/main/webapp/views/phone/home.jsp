<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<meta content="telephone=no" name="format-detection" />
<meta name="baidu-site-verification" content="KdVghMsT2x" />
<title>【${_city.cityName}楼盘|${_city.cityName}房价|${_city.cityName}新房】-搜狐焦点${_city.cityName}房产</title>
<meta name="Keywords" content="${_city.cityName}楼盘, ${_city.cityName}房价, ${_city.cityName}新房">
<meta name="Description" content="搜狐焦点${_city.cityName}房产网为广大网友提供了最新的${_city.cityName}楼盘信息，最准确的${_city.cityName}房价情况和最及时的${_city.cityName}新房资讯等，是买房、购房首选网站。 ">
<%@ include file="/views/icon.jsp" %>
<link rel="stylesheet" type="text/css" href="http://192.168.242.44/sceapp/focus_static/wap/common/css/css_mobile_common_v1.1.css">
<link rel="stylesheet" type="text/css" href="http://192.168.242.44/sceapp/focus_static/wap/phone/css/wap_public_v1.0.css">
<link rel="stylesheet" type="text/css" href="http://192.168.242.44/sceapp/focus_static/wap/phone/css/wap_home_v1.0.css">
<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/zepto-1.1.3.min.js"></script>
<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/iscroll_v5.1.1.js"></script>
<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/phone/snippets/wap_common_v1.0.js"></script>
<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/js/getlocation.js"></script>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.2&amp;key=9e4b883b2a6d8482638c56b6f60078b7"></script>
<script>
var city = ${cityStr};
</script>
</head>
<body class="wap_home_all">	

	   <c:if test="${zzfstatus=='1'}">
	   
	   <a>hello</a>
	   
	   </c:if>

	<div class="wap_container">
		<!-- banner -->
		<div id="ad">
			<div class="advertisement_box">
				<a href="" id="adImg1"></a>
				<span class="closeAd"></span>
				<span class="advertisement_box_fade"></span>
			</div>
		</div>

		<!-- 首页—顶部导航 -->
		<header class="top_nav">
			<span class="menu_list" role="main_menu" role-addClass="its_open"
				data-title="展开列表"></span>
			<ul class="nav_box_ul1 main_menu_ul1">
				<li><a href="${ctx}/${_city.cityPinyinAbbr}/loupan/" role="text" url="loupan/" role-current="current">楼盘</a></li>
				<c:if test="${''!=daogou}">
				<li><a href="${ctx}/${_city.cityPinyinAbbr}/daogou/" role="text" url="daogou/">导购</a></li>
				</c:if>
				<li><a href="${ctx}/${_city.cityPinyinAbbr}/xinwen/" role="text" url="xinwen/">新闻</a></li>
				<li><a href="${ctx}/${_city.cityPinyinAbbr}/baodian/" role="text" url="baodian/">宝典</a></li>
			</ul>
			<span class="logo_city"><em class="logo"></em><a href="/city/select/"><em
					class="city_select1">${_city.cityName}</em></a></span> <span
				class="search_btn fr" role="search_menu" role-addClass="its_open"
				data-title="搜索"></span>
		</header>

		<!-- 搜索列表-隐藏区 // -->
		<section class="search_module_box search_menu_box1">
			<form action="" method="post" id="form_search_result1">
			<div class="search_bar_father">
				<div class="search_button fr" id="search_button1"></div>	
				<div class="search_sort_select">
					<div class="search_sort_selected"><span  house-type="1">新房</span><em></em></div>
					<ul class="search_sort_list">
						<li class="search_sort_xf" role="text" url="" house-type="1">新房</li>
						<!-- li class="search_sort_esf" role="text" url="" house-type="2">二手房</li-->
					</ul>
				</div>
				<div class="search_bar">
					<input type="text" class="search_result1" autocomplete="off" name="group_name" id="search_result1" placeholder="输入楼盘名称/地点" />
					<span class="clear_record1"></span>
				</div>
			</div>
			</form>
			<div class="search_result_boxs">
				<!-- 搜索结果—包含历史记录 -->
				<div class="clear_history">
					历史记录<span class="clear_record2">清空</span>
				</div>
				<!-- 清空历史记录 -->
				<ul class="search_result_ul1">
					<!--<li role="text" url="">万科集团</li>-->
				</ul>
				<div class="close_searchbox" role="text" url="">关闭</div>
			</div>
		</section>
		<!-- // 搜索列表-隐藏区 -->

		<!-- 首页主要内容板块 // -->
		<section class="wap_main home_main">
			<!-- 楼盘筛选-模块 // -->
			<section class="wap_module" data-title="楼盘筛选-模块">
				<header class="public_title1" data-title="楼盘筛选-模块-标题">
					<h1 class="its_tit1">
						${_city.cityName}楼盘<em class="its_icon1"></em>
					</h1>
					<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/" class="public_title_more1" data-title="查看更多"></a>
				</header>
				<ul class="property_screening" id="property_screening1"
					data-title="楼盘筛选-模块-内容">
					<li
						class="property_screening_box borderf2f2f2 property_screening_price_range its_height2">
						<div class="its_biaoti">
							<div class="pos_bt">
								<em class="inlineBlock">价格</em><br />找房
							</div>
							<span class="its_biaoti_arrow"></span>
						</div>
						<div class="its_content its_price_range clearfix"
							id="its_price_range1">
							<c:forEach var="priceCond" items="${priceList}">
								<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/k__${priceCond.condValue}____/" role="text" url="loupan/filterajax/k__${priceCond.condValue}____/">
									${priceCond.condName} </a>
							</c:forEach>
						</div>
						<div class="its_arrow1"></div>
					</li>
					<li
						class="property_screening_box border_e9e9e9 property_screening_area_range its_height2">
						<div class="its_biaoti">
							<div class="pos_bt">
								<em class="inlineBlock">区域</em><br />找房
							</div>
							<span class="its_biaoti_arrow"></span>
						</div>
						<div class="its_content its_area_range clearfix"
							id="its_area_range1">
							<c:forEach var="hotCond" items="${hotList}" varStatus="status">
								<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/k${hotCond.condValue}______/" role="text" url="loupan/filterajax/k___${hotCond.condValue}___/">
									${hotCond.condName} </a>
							</c:forEach>
						</div>
						<div class="its_arrow1"></div>
					</li>
				</ul>
			</section>
			<!-- // 楼盘筛选-模块 -->

            <!--${recommend}-->
             ${daogou}
            
			<!-- 特色楼盘-模块 // -->
			<section class="wap_module featured_property" data-title="特色楼盘-模块">
				<ul class="featured_property_title1" data-title="特色楼盘-模块-标题">
				<c:if test="${''!=dazheloupan}">
					<li class="current" role="text">打折楼盘<em></em></li>
					</c:if>
					<c:if test="${''!=zuixinkaipan}">
					<li role="text">最新开盘<em></em></li>
					</c:if>
					<c:if test="${''!=xiaohuxing}">
					<li role="text">小户型楼盘<em></em></li>
					</c:if>
				</ul>
				<section class="featured_property_nr" data-title="特色楼盘-模块-内容">
				<c:if test="${''!=dazheloupan}">
				<div class="featured_property_box show">
					${dazheloupan}
				</div>
				</c:if>
				<c:if test="${''!=zuixinkaipan}">
				<div class="featured_property_box">
					${zuixinkaipan}
					</div>
					</c:if>
					<c:if test="${''!=xiaohuxing}">
					<div class="featured_property_box">
					${xiaohuxing}
					</div>
					</c:if>
				</section>
			</section>
			<!-- // 特色楼盘-模块 -->

			${news}
			${baodian}

			<!-- 底部导航 // -->
			<footer class="foot_nav">
				<div class="foot_nav_box1" id="foot_nav_box1">
					<!--a href="#" class="radius_top_left4 radius_bottom_left4 current" role="text" url="">新房</a> 
					<a href="#" class="radius_top_right4 radius_bottom_right4" role="text" url="${city.esfUrl}">二手房</a-->
				</div>
				<div class="foot_nav_box2">
					<span class="wap_version" role="wap_version_menu">手机版</span>
					<ul class="nav_box_ul1 wap_version_menu_ul1">
						<li><a href="#" role="wap_version_text" url="" data-nr="PC">电脑版</a></li>
						<li><a href="#" role="wap_version_text" url="" data-nr="PHONE" class="current">手机版</a></li>
					<c:if test="${mobile=='false'}">
						<li><a href="#" role="wap_version_text" url="" data-nr="PAD">Pad版</a></li>
					</c:if>
					</ul>
				</div>
			</footer>
			<!-- // 底部导航 -->
			
		</section>
		<!-- // 首页主要内容板块 -->
	</div>

	<div class="back2top" data-title="返回顶部"></div>
	
	<div class="keywords_alert" id="keywords_alert1" style="">请输入关键词后再搜索</div>
	
	<!-- 定位弹层 —— 往下 // -->
	<div class="over"></div>
	<!-- 定位城市无wap版弹层 // -->
	<div class="alert_box" id="city_location_no_wap1">
		<p class="p1">您所在的城市尚未开通</p>
		<p class="p1">移动版</p>
		<div class="itos_btn1" id="goto_pc1"><a href="http://${_city.domainName }">进入电脑版</a></div>
		<div class="its_close_btn"></div>
	</div>
	<!-- // 定位城市无wap版弹层 -->
	
	<!-- 定位禁用弹层 // -->
	<div class="alert_box signup_fail_box" id="forbid_city_location1">
		<h3 class="signup_fail_tit red1"><em class="signup_fail_icon"></em>您已禁止浏览器定位！</h3>
		<p class="p2">请手动选择城市</p>
	</div>
	<!-- // 定位禁用弹层 -->
	
	<!-- 定位失败弹层 // -->
	<div class="alert_box signup_fail_box" id="city_location_fail1">
		<h3 class="signup_fail_tit red1"><em class="signup_fail_icon"></em>定位失败！</h3>
		<p class="p2">请检查网络和GPS设置</p>
	</div>
	<!-- // 定位失败弹层 -->

<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/phone/snippets/wap_home_v1.0.js"></script>
<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/phone/snippets/wap_city_select_v1.0.js"></script>
<script type="text/javascript">
getlocation("${needLBS}",navigator.cookieEnabled);
</script>
<%@ include file="/views/pv.jsp"%>
</body>
</html>