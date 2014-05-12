<%@page import="cn.focus.dc.focuswap.service.SearchService.SearchType"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
	<meta content="telephone=no" name="format-detection" />
	<title>【${_city.cityName }${chosenTkd }楼盘】-搜狐焦点${_city.cityName }房产</title>
	<meta name="Keywords" content="${_city.cityName }楼盘,${chosenTkd }+楼盘信息">
	<meta name="Description" content="搜狐焦点${_city.cityName }房产每天都有大量的更新房源，它汇聚了最新、最准确的${chosenTkd }的楼盘信息，是买房、购房的首选网站。 ">
	<%@ include file="/views/icon.jsp" %>
	<link rel="stylesheet" href="http://192.168.242.44/sceapp/focus_static/wap/common/css/css_mobile_common_v1.1.css" />
	<link rel="stylesheet" href="http://192.168.242.44/sceapp/focus_static/wap/pad/css/index.css" />
	<script src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/zepto-1.1.3.min.js"></script>
	<script>
		var city = ${city};
		var hasNext = ${hasNext};
	</script>
</head>
	
<body class="bg_all">
	<section class="container">
		<header class="clearfix">
			<a href="${ctx }/${_city.cityPinyinAbbr }/" class="back fl" >首页</a>
			<h1>楼盘筛选</h1>
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
			
		<section class="content bg_all">
			<aside class="scroll_mod filter_list" id="filter_list" style="position:absolute;left:0;top:0">
				<div class="scroller">
					<ul id="filter_items">
						<c:set var="district" value="<%=SearchType.DISTRICT %>"/>
						<c:if test="${not empty conditions[district] }">
						<li ftype="di" <c:if test="${fold == 'di' }">class="unfold"</c:if>>
							<div class="">
								<div class="dt">区域</div>
								<c:forEach var="co" items="${conditions[district]}" varStatus="status">
									<a href="${co.linkUrl}" class="dd ${co.select }" <c:if test="${fold != 'di' }">style="display:none;"</c:if>>${co.condName}</a>
								</c:forEach>
							</div>
						</li>
						</c:if>
						<c:set var="type" value="<%=SearchType.TYPE %>"/>
						<c:if test="${not empty conditions[type] }">
						<li ftype="ty" <c:if test="${fold == 'ty' }">class="unfold"</c:if>>
							<div class="">
								<div class="dt">类型</div>
								<c:forEach var="co" items="${conditions[type]}" varStatus="status">
									<a href="${co.linkUrl}" class="dd ${co.select }" <c:if test="${fold != 'ty' }">style="display:none;"</c:if>>${co.condName}</a>
								</c:forEach>
							</div>
						</li>
						</c:if>
						<c:set var="price" value="<%=SearchType.PRICE %>"/>
						<c:if test="${not empty conditions[price] }">
						<li ftype="pr " <c:if test="${fold == 'pr' }">class="unfold"</c:if>>
							<div class="">
								<div class="dt">价格</div>
								<c:forEach var="co" items="${conditions[price]}" varStatus="status">
									<a href="${co.linkUrl}" class="dd ${co.select }" <c:if test="${fold != 'pr' }">style="display:none;"</c:if>>${co.condName}</a>
								</c:forEach>									
							</div>
						</li>
						</c:if>
						<c:set var="hot" value="<%=SearchType.HOT %>"/>
						<c:if test="${not empty conditions[hot] }">
						<li ftype="ho" <c:if test="${fold == 'ho' }">class="unfold"</c:if>>
							<div class="">
								<div class="dt">热点板块</div>
								<c:forEach var="co" items="${conditions[hot]}" varStatus="status">
									<a href="${co.linkUrl}" class="dd ${co.select }" <c:if test="${fold != 'ho' }">style="display:none;"</c:if>>${co.condName}</a>
								</c:forEach>
							</div>
						</li>
						</c:if>
						<c:set var="subway" value="<%=SearchType.SUBWAY %>"/>
						<c:if test="${not empty conditions[subway] }">
						<li ftype="su" <c:if test="${fold == 'su' }">class="unfold"</c:if>>
							<div class="">
								<div class="dt">轨道交通</div>
								<c:forEach var="co" items="${conditions[subway]}" varStatus="status">
									<a href="${co.linkUrl}" class="dd ${co.select }" <c:if test="${fold != 'su' }">style="display:none;"</c:if>>${co.condName}</a>
								</c:forEach>	
							</div>
						</li>
						</c:if>
						<c:set var="special" value="<%=SearchType.SPECIAL %>"/>
						<c:if test="${not empty conditions[special] }">
						<li ftype="sp" <c:if test="${fold == 'sp' }">class="unfold"</c:if>>
							<div class="">
								<div class="dt">特色楼盘</div>
								<c:forEach var="co" items="${conditions[special]}" varStatus="status">
									<a href="${co.linkUrl}" class="dd ${co.select }" <c:if test="${fold != 'sp' }">style="display:none;"</c:if>>${co.condName}</a>
								</c:forEach>	
							</div>
						</li>
						</c:if>
					</ul>
				</div>
			</aside>

			<article class="scroll_mod filter_result article_result" id="filter_result">
				<div class="scroller">
					<div class="filter_top">
							<div class="item clearfix">
								<span>筛选条件：</span>
								<c:if test="${empty chosen}">
								<a href="javascript:;" class="all">全部楼盘</a>	
								</c:if>
								<c:if test="${not empty chosen}">	
										<a href="/${_city.cityPinyinAbbr }/loupan/" class="empty">清空</a>
										<c:forEach var="ch" items="${chosen}" varStatus="status">
										<a href="${ch.linkUrl }">${ch.condName}</a>
										</c:forEach>
								</c:if>
							</div>
					</div>
					<article>
						<div class="filter_search_results">在<span class="red">${_city.cityName }</span>共找到${total}个符合条件的楼盘</div>
						<c:if test="${ not empty buildingList}">
							<ul class="article_list article_img article_line article_result">
								<c:forEach var="bl" items="${buildingList}" varStatus="status">
								<li>
								<a href="${bl.buildingUrl }" class="clearfix" role="touch_bg">
								<img alt="${bl.projName }" src="${bl.url }" class="img fl">
								<dl><dt>
								${bl.projName }
								<span class="status sale">
								<c:choose>
										   <c:when test="${bl.saleStatus == 0}">
												  在售
										   </c:when>
										   <c:when test="${bl.saleStatus == 1}">
												  待售
										   </c:when>
										   <c:when test="${bl.saleStatus == 2}">
												  尾盘
										   </c:when>
										   <c:otherwise>
												 售罄
										   </c:otherwise>
								</c:choose>
								</span></dt>
								<dd class="clearfix">${bl.projAddress}
								<span class="price fr">${bl.avgPriceShow}</span></dd>
									
									
								<c:if test="${not empty fn:trim(bl.discount) }">
									<dd><span class="mark discount">${bl.discount}</span></dd>
								</c:if>
								</dl></a>
								</li>
								</c:forEach>
							</ul>
							<div id="more">
								<div class="line line_nop"></div>
								<a href="javascript:;" class="load_more">滑动加载更多</a>
								<a href="javascript:;" class="loading"><b class="rotating"></b>加载中...</a>
							</div>
						</c:if>
						<c:if test="${empty buildingList}">
							<div class="noresult">
								<div class="inner">
									<p>暂无符合条件的楼盘，请修改筛选条件</p>
								</div>
							</div>
						</c:if>
					</article>
				</div>
			</article>
		</section>

		<section class="crumb">
			<div class="inner">
				<a href="/${_city.cityPinyinAbbr }/">新房</a>&gt;
				<c:if test="${not empty districtName}"><span>${districtName }楼盘</span></c:if>
				<c:if test="${empty districtName}"><span>${_city.cityName }楼盘</span></c:if>
			</div>
		</section>
			
		<footer>
			<span class="copyright">&copy;2013搜狐焦点</span>
			<div class="switch_version">
				<a href="javascript:;" class="version">Pad版</a>
				<div class="version_list">
					<a href="" role="touch_bg" class="phone">手机版</a>
					<a href="" role="touch_bg" class="ipad current">Pad版</a>
					<a href="" role="touch_bg" class="pc">电脑版</a>
				</div>
			</div>
			<a href="javascript:;" id="to_top">返回顶部</a>
		</footer>
	</section>
		
	<!-- js -->
	<script src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/iscroll_v4.2.5.js"></script>
	<script src="http://192.168.242.44/sceapp/focus_static/wap/pad/snippets/sohu.focus.mobile.common.gh_1.0.js"></script>
	<script src="http://192.168.242.44/sceapp/focus_static/wap/pad/snippets/sohu.focus.mobile.filter.gh_1.0.js"></script>
	<%@ include file="/views/pv.jsp"%>
</body>
</html>