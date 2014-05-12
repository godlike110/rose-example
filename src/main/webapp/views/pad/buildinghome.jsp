<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
		<meta content="telephone=no" name="format-detection" />
		<title>${info.projName }-${_city.cityName }${info.projName }相关信息-搜狐焦点</title>
		<%@ include file="/views/icon.jsp" %>
		<link rel="stylesheet" href="http://192.168.242.44/sceapp/focus_static/wap/common/css/css_mobile_common_v1.1.css" />
		<link rel="stylesheet" href="http://192.168.242.44/sceapp/focus_static/wap/pad/css/index.css" />
		<script src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/zepto-1.1.3.min.js"></script>
        <script>
			var city = ${cityStr};
			var groupId = ${groupId}; 
			var phone400 = ${phone400};
		</script>
	</head>
	
	<body>
		<section class="container">
			<header class="clearfix">
										<c:if test="${returnFlag=='0' }">
		<a href="javascript:history.back();" class="back fl" data-title="返回上一级">返回</a>
		</c:if>
		<c:if test="${returnFlag=='1' }">
			<a href="${ctx}/${_city.cityPinyinAbbr}/" class="back fl" data-title="返回上一级">首页</a>
		</c:if>
				
				<h1>楼盘详情</h1>
				<a href="${ctx}/${_city.cityPinyinAbbr}/" class="main fr">首页</a>
			</header>
			
			<section class="content">
				<aside class="scroll_mod" id="left_side" style="position:absolute;left:0;top:0">
					<div class="scroller">
						<article>
							<div class="title clearfix">
								<h2>${info.projName}</h2>
							</div>
							
							<c:if test="${'' != buildingpiccount}">
							<div class="album" role="touch_bg" >
								<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${info.groupId}/tupian/-1/"><img src="${info.logUrl}" alt="${info.projName}"></a>
								${buildingpiccount }
							</div>
							</c:if>

							<c:if test="${'' == buildingpiccount}">
							<div class="album">
								<a href="javascript:;" class="none" title="搜狐焦点房产"></a>
							</div>
							</c:if>
							
							<p>${info.priceForShow}<c:choose>
											   <c:when test="${info.saleStatus == 0}">
													  <span class="status sale">在售</span>
											   </c:when>
											   <c:when test="${info.saleStatus == 1}">
													  <span class="status wait">待售</span>
											   </c:when>
											   <c:when test="${info.saleStatus == 2}">
													  <span class="status remain">尾盘</span>
											   </c:when>
											   <c:otherwise>
													  <span class="status over">售罄</span>
											   </c:otherwise>
									</c:choose></p>
							<p class="clearfix">
							<c:if test="${not empty activeInfo}">
								<span class="discount">${activeInfo}</span>
								<!--a href="javascript:;" class="coupon fr">获取优惠</a-->
								</c:if>
							</p>
							<form class="sign_up_form">
								<div class="form_item clearfix">
									<label for="username" class="lbl">姓名</label>
									<input type="text" id="username" class="txt" />
									<div class="tips success"></div>
									<div class="info">
										<span class="default_info">姓名最多8个汉字或16个英文字母</span>
										<span class="error_info">请输入姓名，最多8个汉字或16个英文字母</span>
									</div>
								</div>
								<div class="form_item clearfix">
									<label for="phone" class="lbl">手机号</label>
									<input type="tel" id="phone" class="txt" maxlength="11" />
									<div class="tips error"></div>
									<div class="info">
										<span class="default_info">请输入11位手机号</span>
										<span class="error_info">请输入正确的手机号</span>
									</div>
								</div>
								<div class="form_item">
									<label class="check_item"><input type="checkbox" />订阅本楼盘的抓房团、折扣及动态信息</label>
								</div>
								<div class="form_item">
									<input type="button" value="报名" class="btn">
								</div>
							</form>
							<div class="line"></div>
							<c:if test="${not empty reason}">
							<p class="recommend"><strong>【主编推荐】</strong>${reason}</p>
							
							<div class="line"></div>
							</c:if>
						</article>

						<article class="loupan_info">
							<h2 class="border_wrap">楼盘信息</h2>
							<dl>
								<dt>楼盘位置：</dt><dd><c:choose><c:when test="${empty fn:trim(info.projAddress)}">暂无</c:when>
						<c:otherwise>${info.projAddress}</c:otherwise></c:choose></dd>
							</dl>
							<dl>
								<dt>所属片区：</dt><dd><c:choose><c:when test="${empty fn:trim(info.areaDetail)}">暂无</c:when>
						<c:otherwise>${info.areaDetail}</c:otherwise></c:choose></dd>
							</dl>
							<dl>
								<dt>开盘时间：</dt><dd><c:choose><c:when test="${empty fn:trim(info. saleDateDetail)}">暂无</c:when>
						<c:otherwise>${info. saleDateDetail }</c:otherwise></c:choose></dd>
							</dl>
							<dl>
								<dt>入住时间：</dt><dd><c:choose><c:when test="${empty fn:trim(info. moveInDateDetail)}">暂无</c:when>
						<c:otherwise>${info. moveInDateDetail }</c:otherwise></c:choose></dd>
							</dl>
							<dl>
								<dt>楼盘类型：</dt><dd><c:choose><c:when test="${empty fn:trim(info. projTypeDetail)}">暂无</c:when>
						<c:otherwise>${info. projTypeDetail }</c:otherwise></c:choose></dd>
							</dl>
							<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${info.groupId}/xiangxi/" role="touch_bg" class="more">查看更多详细信息<b></b></a>
						</article>

						${layouts }


						<article class="location">
							<h2 class="border_wrap">位置及周边</h2>
							<p>${info.projAddress}</p>
							<c:if test="${empty ditu }">
							<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${info.groupId}/ditu/" class="lm">
								<img style="-webkit-user-select: none" src="http://restapi.amap.com/gss/simple?sid=3027&amp;width=470&amp;height=240&amp;ia=1&amp;content=MAP&maplevel=6&amp;encode=UTF-8&amp;xys=${info.longitude},${info.latitude}&amp;key=9e4b883b2a6d8482638c56b6f60078b7&iconheight=32&iconwidth=30&icons=http://webapi.amap.com/images/marker_sprite.png">
							</a>
							</c:if>
							<c:if test="${not empty ditu }">
							<a href="javascript:;" class="lm">
								<img src="http://192.168.242.44/sceapp/focus_static/wap/pad/img/map_default.jpg" alt="${info.projName}地图" width="470">
							</a>
							</c:if>
						</article>
					</div>
				</aside>

				<section class="scroll_mod wrapper bg_all" id="right_side">
					<div class="scroller">
					
					    ${questions}
                        ${hotnews}



                        ${arroundbuilding}
					</div>
				</section>
			</section>

			<section class="crumb">
				<div class="inner">
					<a href="${ctx}/${_city.cityPinyinAbbr}/">新房</a>&gt;<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/"> ${_city.cityName }楼盘</a>&gt;<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${info.groupId}/"> ${buildName}</a>
				</div>
			</section>
			
            <c:if test="${info.phone400!=0 }">
			<footer>
				<a href="tel:4008882200,${info.phone400 }" class="free_advice"><b></b>免费咨询 400-888-2200 转 ${info.phone400 }</a>
			</footer>
			</c:if>
						 <c:if test="${info.phone400==0 }">
			<footer class="position_fixed">
				<a href="tel:4006782020" class="free_advice"><b></b>免费咨询 400-678-2020</a>
			</footer>
			</c:if>
			<section class="popup_layer" id="popup-1">
                <div class="mask"></div>
                <div class="popup">
                    <div class="inner">
                        <h4 class="rright">报名成功！</h4>
                        <p>楼盘的销售人员会和您联系，请保持电话畅通。</p>
                        <p class="booknews_item">当楼盘有新动态时会第一时间短信通知您。</p>
                    </div>
                </div>
            </section>
            <section class="popup_layer" id="popup-2">
                <div class="mask"></div>
                <div class="popup">
                    <div class="inner">
                        <h4 class="rright">报名成功！</h4>
                        <p>您已报名过本楼盘的团购。</p>
                    </div>
                </div>
            </section>
		</section>
		<!-- js -->
		<script src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/iscroll_v4.2.5.js"></script>
		<script src="http://192.168.242.44/sceapp/focus_static/wap/pad/snippets/sohu.focus.mobile.common.gh_1.0.js"></script>
		<script src="http://192.168.242.44/sceapp/focus_static/wap/pad/snippets/sohu.focus.mobile.detail.gh_1.0.js"></script>
		<script src="http://192.168.242.44/sceapp/focus_static/wap/pad/snippets/ipad.ad.js"></script>
		<%@ include file="/views/pv.jsp"%>
	</body>
</html>