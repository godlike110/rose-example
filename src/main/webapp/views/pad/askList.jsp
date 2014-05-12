<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
		<meta content="telephone=no" name="format-detection" />
		<title>购房问答-${_city.cityName }新房_买房注意事项-搜狐焦点</title>
		<meta name="keywords" content="购房问答、${_city.cityName }新房_买房注意事项" />
		<meta name="description" content="购房问答，搜狐焦点${_city.cityName }买房问答频道为您提供最全面的购房相关信息与咨询，包括楼盘相关信息、楼盘相关新闻、买房注意事项，购房更多信息尽在搜狐焦点网。" />
		<%@ include file="/views/icon.jsp" %>
		<link rel="stylesheet" href="http://192.168.242.44/sceapp/focus_static/wap/common/css/css_mobile_common_v1.1.css" />
		<link rel="stylesheet" href="http://192.168.242.44/sceapp/focus_static/wap/pad/css/index.css" />
		<script src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/zepto-1.1.3.min.js"></script>
		<script>
			var city = ${city};
			var groupId = ${bl.groupId}; 
			var phone400 = ${bl.phone400};
		</script>
		
	</head>
	
	<body class="refer_list_body layout_fixed">
		<section class="container" id="asklist_wrapper">
			<div class="scroller">
				<header class="clearfix">
					<c:choose>
						<c:when test="${returnFlag == 1}">
								<a href="${ctx }/${_city.cityPinyinAbbr }/" class="back fl" >首页</a>
						</c:when>
						<c:when test="${returnFlag == 0}">
								<a href="javascript:history.back();" class="back fl" >返回</a>
						</c:when>
					</c:choose>	
					<h1>楼盘咨询</h1>
				</header>
				
				<section class="content clearfix">
					<article>
						<ul class="article_list article_line qr_mod">				 
					 		<c:forEach var="item" items="${questionList}" varStatus="status">
							<li role="touch_bg" href="${ctx }/${_city.cityPinyinAbbr }/loupan/${bl.groupId}/zixun/${item.id}/">
								<a href="javascript:;">
									<dl>
										<dt>问：${item.question}</dt>
										<dd class="clearfix">
											<span>${item.editorDesc }&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${item.updateTime }" pattern="M月d日"/></span>
											<span class="count fr">有用<em>${item.usefulCount }</em></span>
										</dd>
										<dd>答：${fn:substring(item.answer, 0, 75)}</dd>
									</dl>
								</a>
							</li>
							</c:forEach>
						</ul>
						<c:if test="${hasNext}">
						<div id="has_more">
							<div class="line line_nop"></div>
							<a href="javascript:;" class="load_more">滑动加载更多</a>
							<a href="javascript:;" class="loading"><b class="rotating"></b>加载中...</a>
						</div>
						</c:if>
					</article>
				</section>

				<section class="crumb">
					<div class="inner">
						<a href="/${_city.cityPinyinAbbr }/">新房</a>&gt;
						<a href="${ctx }/${_city.cityPinyinAbbr }/loupan/">${_city.cityName }楼盘</a>&gt;
						<a href="${ctx }/${_city.cityPinyinAbbr }/loupan/${bl.groupId}/">${bl.projName }</a>&gt;
						<span>楼盘咨询</span>
					</div>
					
				</section>
			</div>
		</section>
		
		<c:if test="${bl.phone400 != 0 }">
		<footer class="position_fixed">
					<div><a href="tel:4008882200,${bl.phone400}" class="footer_ban_tel"><b></b>免费咨询 </a></div>
					<div><a href="http://m.focus.cn/static/appwap.html#weixin.qq.com" class="footer_ban_download"><b></b>下载客户端提问</a></div>
		</footer>
		</c:if>
		<c:if test="${bl.phone400==0 }">
			<footer class="position_fixed">
				<div><a href="tel:4006782020" class="free_advice"><b></b>免费咨询 </a></div>
				<div><a href="http://m.focus.cn/static/appwap.html#weixin.qq.com" class="footer_ban_download"><b></b>下载客户端提问</a></div>
			</footer>
		</c:if>
		<a href="javascript:;" id="to_top">返回顶部</a>
		
		<!-- js -->
		<script src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/iscroll_v4.2.5.js"></script>
		<script src="http://192.168.242.44/sceapp/focus_static/wap/pad/snippets/sohu.focus.mobile.common.gh_1.0.js"></script>
		<script src="http://192.168.242.44/sceapp/focus_static/wap/pad/snippets/sohu.focus.mobile.question.gh_1.0.js"></script>
		<%@ include file="/views/pv.jsp"%>
	</body>
</html>