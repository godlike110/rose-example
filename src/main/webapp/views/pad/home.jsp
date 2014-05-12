<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
		<meta content="telephone=no" name="format-detection" />
		<meta name="baidu-site-verification" content="KdVghMsT2x" />
		<title>【${_city.cityName}楼盘|${_city.cityName}房价|${_city.cityName}新房】-搜狐焦点${_city.cityName}房产</title>
		<%@ include file="/views/icon.jsp" %>
		<link rel="stylesheet" href="http://192.168.242.44/sceapp/focus_static/wap/common/css/css_mobile_common_v1.1.css" />
		<link rel="stylesheet" href="http://192.168.242.44/sceapp/focus_static/wap/pad/css/index.css" />
		<script src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/zepto-1.1.3.min.js"></script>
		<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/js/getlocation.js"></script>
        <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.2&amp;key=9e4b883b2a6d8482638c56b6f60078b7"></script>		
		<script>
			var city = ${cityStr};
		</script>
	</head>

	<body>
	   
	   <c:if test="${zzfstatus=='1'}">
	   	   <a>hello</a>
	   
	   </c:if>
	
		<section class="container">
			<header class="clearfix">
				<div class="menu fl" id="menu">
					<a href="javascript:;" class="choose">选择</a>
					<div class="menu_list">
						<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/" role="touch_bg">楼盘</a>
						<c:if test="${''!=daogou}">
						<a href="${ctx}/${_city.cityPinyinAbbr}/daogou/" role="touch_bg">导购</a>
						</c:if>
						<a href="${ctx}/${_city.cityPinyinAbbr}/xinwen/" role="touch_bg">新闻</a>
						<a href="${ctx}/${_city.cityPinyinAbbr}/baodian/" role="touch_bg">宝典</a>
					</div>
				</div>
				
				<div class="logo clearfix">
					<a href="" class="fl"><img src="http://192.168.242.44/sceapp/focus_static/wap/phone/img/logo.png" alt="搜狐焦点图片" /></a>
					<a href="/city/select" class="city fl">${_city.cityName}</a>
				</div>
				
				<div class="search">
					<a href="javascript:;" class="btn">搜索</a>
					<div class="search_wrap clearfix">
						<div class="select_box fl">
							<span>新房</span>
							<!-- a href="javascript:;" class="selected">选择</a-->
							<!-- div class="options">
								<a role="touch_bg" data-type="1" href="javascript:;" class="checked">新房</a>
								<!-- a role="touch_bg" data-type="2" href="javascript:;">二手房</a-->
							</div-->
						</div>
						<div class="search_form">
							<form action="" method="post" class="clearfix">
								<div class="form_item">
									<input type="text" name="group_name" id="search_text" placeholder="输入楼盘名称/地点" autocomplete="off" />
								</div>
								<input type="button" value="查询" />
								<a href="javascript:;" class="empty">清空</a>
							</form>
							<div class="suggest">
								<div class="suggest_item suggest_tip clearfix">
									<span>历史记录</span><a class="sempty fr" href="javascript:;">清空</a>
								</div>
								<div class="suggest_item suggest_tip_close clearfix">
									<a href="javascript:;" class="close fr">关闭</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</header>
			
			<nav>
				<ul>
					<li class="clearfix">
						<span class="fl">价格找房</span>
						<div class="items">
							<ul class="clearfix">
                            <c:forEach var="priceCond" items="${priceList}">
                                    <li>
								<a role="touch_bg" href="${ctx}/${_city.cityPinyinAbbr}/loupan/k__${priceCond.condValue}____/">
									${priceCond.condName} </a>
									</li>
							</c:forEach>
							</ul>
							<a href="javascript:;" class="more">更多</a>
						</div>
					</li>
					<li class="clearfix">
						<span class="fl">区域找房</span>
						<div class="items">
							<ul class="clearfix">
                            <c:forEach var="priceCond" items="${hotList}">
                                    <li>
								<a role="touch_bg" href="${ctx}/${_city.cityPinyinAbbr}/loupan/k${priceCond.condValue}______/">
									${priceCond.condName} </a>
									</li>
							</c:forEach>
							</ul>
							<a href="javascript:;" class="more">更多</a>
						</div>
					</li>
				</ul>
			</nav>
			
			<section class="content">
				<aside class="scroll_mod" id="left_aside" style="position:absolute;left:0;top:0">
					<div class="scroller">
                      <!--    ${recommend} -->
                       ${daogou}
						${dazheloupan}
						${zuixinkaipan}
						${xiaohuxing}
					</div>
				</aside>

				<section class="scroll_mod wrapper" id="right_aside">
					<div class="scroller">
						${news}
						${baodian}
					</div>
				</section>
			</section>	
			
			<footer class="clearfix">
				<!--ul class="clearfix">
					< li role="touch_bg" data-type="1" class="current"><a href="">新房</a></li>
					<li role="touch_bg" data-type="2"><a href="${city.esfUrl}">二手房</a></li>
				</ul-->
				<div class="switch_version">
					<a href="javascript:;" class="version">Pad版</a>
					<div class="version_list">
						<a role="touch_bg" href="#" class="phone">手机版</a>
						<a role="touch_bg" href="#" class="ipad current">Pad版</a>
						<a role="touch_bg" href="#" class="pc">电脑版</a>
					</div>
				</div>
			</footer>

			<div class="alert_box">
				<p>请输入关键词后再搜索</p>
			</div>
			<section class="popup_layer" id="popup-1">
                <div class="mask"></div>
                <div class="popup">
                    <div class="inner">
                        <h4 class="wrong">定位失败！</h4>
                        <p>请检查网络和GPS设置。</p>
                    </div>
                </div>
			</section>
			<section class="popup_layer" id="popup-2">
                <div class="mask"></div>
                <div class="popup">
                    <div class="inner">
                        <h4 class="wrong">您已禁止浏览器定位！</h4>
                        <p>手动选择城市。</p>
                    </div>
                </div>
			</section>
			<section class="popup_layer" id="popup-3">
                <div class="mask"></div>
                <div class="popup">
                    <div class="inner inner_center">
                        <p>您所在的城市尚未开通移动版！</p>
                        <a href="http://${_city.domainName }" class="btn">进入电脑版</a>
                    </div>
                </div>
			</section>
		</section>
		
		<!-- js -->
		<script src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/iscroll_v4.2.5.js"></script>
		<script src="http://192.168.242.44/sceapp/focus_static/wap/pad/snippets/sohu.focus.mobile.common.gh_1.0.js"></script>
		<script src="http://192.168.242.44/sceapp/focus_static/wap/pad/snippets/sohu.focus.mobile.index.gh_1.0.js"></script>
		<script src="http://192.168.242.44/sceapp/focus_static/wap/pad/snippets/ipad.ad.js"></script>
		<script src="http://192.168.242.44/sceapp/focus_static/wap/pad/snippets/sohu.focus.mobile.select_city.gh_1.0.js"></script>
		<script >
			getlocation("${needLBS}",navigator.cookieEnabled);
		</script>
		<%@ include file="/views/pv.jsp"%>
	</body>
</html>