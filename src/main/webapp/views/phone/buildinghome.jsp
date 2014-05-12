<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<meta content="telephone=no" name="format-detection" />
<title>${info.projName }-${_city.cityName }${info.projName }相关信息-搜狐焦点</title>
<meta name="Keywords" content="${_city.cityName }楼盘, ${_city.cityName }房价, ${_city.cityName }新房">
<meta name="Description" content="搜狐焦点${_city.cityName }房产网为广大网友提供了最新的${_city.cityName }楼盘信息，最准确的${_city.cityName }房价情况和最及时的${_city.cityName }新房资讯等，是买房、购房首选网站。 ">
<%@ include file="/views/icon.jsp" %>
<link rel="stylesheet" type="text/css" href="http://192.168.242.44/sceapp/focus_static/wap/common/css/css_mobile_common_v1.1.css">
<link rel="stylesheet" type="text/css" href="http://192.168.242.44/sceapp/focus_static/wap/phone/css/wap_public_v1.0.css">
<link rel="stylesheet" type="text/css" href="http://192.168.242.44/sceapp/focus_static/wap/phone/css/wap_housedetail_v1.0.css">
<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/zepto-1.1.3.min.js"></script>
<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/phone/snippets/wap_common_v1.0.js"></script>
<script>
var city = ${cityStr};
var groupId = ${groupId};
var phone400 = ${phone400};
</script>
</head>
<body class="wap_housedetail_all">
	<div class="wap_container">
		<!-- banner -->
		<div id="ad">
			<div class="advertisement_box">
				<a href="" id="adImg1"></a>
				<span class="closeAd"></span>
				<span class="advertisement_box_fade"></span>
			</div>
		</div>
		
		<!-- 楼盘详情页—顶部导航 -->
		<header class="top_nav">
		<c:if test="${returnFlag=='0' }">
		<a href="javascript:history.back();" class="go_back" data-title="返回上一级">返回</a>
		</c:if>
		<c:if test="${returnFlag=='1' }">
			<a href="${ctx}/${_city.cityPinyinAbbr}/" class="go_back" data-title="返回上一级">首页</a>
		</c:if>	
			<span class="search_title">楼盘详情</span>			
			<a class="go_home" href="${ctx}/${_city.cityPinyinAbbr}/"></a>
		</header>
		
		<!-- 楼盘详情页主要内容板块 // -->
		<section class="detail_index_main">
			<div class="detail_index_container">
				<h1>${info.projName}</h1>
				<div class="detail_index_lp_imgbox border_e9e9e9">
					<div class="its_img_box">
					<c:if test="${''!= buildingpiccount}">
						<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${info.groupId}/tupian/-1/">
						<span>
							<img src="${info.logUrl}" alt="${info.projName}" />
							${buildingpiccount }
						</span>
						</a>
					</c:if>
					<c:if test="${''==buildingpiccount}">
						<a href="#">
						<span>
							<img src="#" alt="${info.projName}" />
						</span>
						</a>
					</c:if>
					</div>
					<div class="its_cankao_price">${info.priceForShow}<c:choose>
											   <c:when test="${info.saleStatus == 0}">
													  <span class="in_sale" data-title="在售"></span>
											   </c:when>
											   <c:when test="${info.saleStatus == 1}">
													  <span class="wait_sale" data-title="待售"></span>
											   </c:when>
											   <c:when test="${info.saleStatus == 2}">
													  <span class="last_sale" data-title="尾盘"></span>
											   </c:when>
											   <c:otherwise>
													   <span class="out_sale" data-title="售罄"></span>
											   </c:otherwise>
									</c:choose>
					</div>
					<c:if test="${not empty activeInfo}">
					
					<div class="it_zhekou">							
								<!--span class="fr get_goodprice">获取优惠<em class="its_arrow1"></em></span--><em class="zhekou1"></em>${activeInfo}</div>
					</c:if>
					
					<!-- 电话报名 // -->
					<div class="favorable_signup" style="display:;">
						<ul class="favorable_ul1">
							<li class="clearfix">
								<div class="ots_name1 fl tr textNowrap">姓名</div>
								<div class="ots_inp1" add-class="ots_inp1_focused">
									<input class="ots_inp1_input ots_inp1_name" maxlength="12" type="text" placeholder="请输入姓名" />
									<span class="its_yes hide"></span>
									<span class="its_no hide"></span>
								</div>
								<div class="ots_tip1">
									<p class="it_ok">姓名最多6个汉字或12个英文字母</p>
									<p class="it_no hide"><em class="it_no_icon"></em>请输入姓名，最多6个汉字或12个英文字母</p>
								</div>
							</li>
							<li class="clearfix">
								<div class="ots_name1 fl tr textNowrap">手机号</div>
								<div class="ots_inp1">
									<input class="ots_inp1_input ots_inp1_telephone" maxlength="11" type="text" placeholder="请输入手机号" />
									<span class="its_yes hide"></span>
									<span class="its_no hide"></span>
								</div>
								<div class="ots_tip1">
									<p class="it_ok">请输入11位手机号</p>
									<p class="it_no hide"><em class="it_no_icon"></em>请输入正确的手机号</p>
								</div>
							</li>
						</ul>
						<div class="check_it"><i class="check_it_icon" add-class="input_checked"></i><input class="hide" type="checkbox" />订阅本楼盘的抓房团、折扣及动态信息</div>
						<div class="signup_btn1"><a href="#" class="ots_btn" add-class="ots_btn_use">报名</a></div>
					</div>
					<!-- // 电话报名 -->
					
				</div>
				<c:if test="${not empty reason}">
				<div class="zhubian_tj border_e9e9e9"><span class="strong1">【主编推荐】</span>${reason}</div>
				</c:if>
				
				<section data-title="楼盘信息">
					<header class="public_title1" data-title="楼盘信息-标题">
						<h2 class="its_tit1">楼盘信息<em class="its_icon1"></em></h2>
					</header>
					<div class="detail_info_container">
						<ul class="detail_info_ul">
							<li class="clearfix">
								<div class="detail_info_li_tit">楼盘位置：</div>
								<div class="detail_info_li_nr"><c:choose><c:when test="${empty fn:trim(info.projAddress)}">暂无</c:when>
						<c:otherwise>${info.projAddress}</c:otherwise></c:choose></div>
							</li>
							<li class="clearfix">
								<div class="detail_info_li_tit">所属片区：</div>
								<div class="detail_info_li_nr"><c:choose><c:when test="${empty fn:trim(info.areaDetail)}">暂无</c:when>
						<c:otherwise>${info.areaDetail}</c:otherwise></c:choose></div>
							</li>
							<li class="clearfix">
								<div class="detail_info_li_tit">开盘时间：</div>
								<div class="detail_info_li_nr"><c:choose><c:when test="${empty fn:trim(info. saleDateDetail)}">暂无</c:when>
						<c:otherwise>${info. saleDateDetail }</c:otherwise></c:choose></div>
							</li>
							<li class="clearfix">
								<div class="detail_info_li_tit">入住时间：</div>
								<div class="detail_info_li_nr"><c:choose><c:when test="${empty fn:trim(info. moveInDateDetail)}">暂无</c:when>
						<c:otherwise>${info. moveInDateDetail }</c:otherwise></c:choose></div>
							</li>
							<li class="clearfix">
								<div class="detail_info_li_tit">楼盘类型：</div>
								<div class="detail_info_li_nr"><c:choose><c:when test="${empty fn:trim(info. projTypeDetail)}">暂无</c:when>
						<c:otherwise>${info. projTypeDetail }</c:otherwise></c:choose></div>
							</li>
						</ul>
						<div class="look_more"><a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${info.groupId}/xiangxi/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/loupan/${info.groupId}/xiangxi/" class="">查看更多详细信息<em class="look_more_icon"></em></a></div>
					</div>
				</section>
				
				${layouts }
				
				<section class="detail_index_map_container" data-title="位置及周边">
					<header class="public_title1" data-title="位置及周边-标题">
						<h2 class="its_tit1">地图及周边<em class="its_icon1"></em></h2>
					</header>
					<div class="detail_index_map_nr">
					<c:if test="${ empty ditu }">
					    <a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${info.groupId}/ditu/">
						<div class="its_address">${info.projAddress}</div>
						
						<div id="map_box" class="its_map_block">
						<img style="-webkit-user-select: none" src="http://restapi.amap.com/gss/simple?sid=3027&maplevel=6&amp;width=300&amp;height=150&amp;ia=1&amp;content=MAP&amp;encode=UTF-8&amp;xys=${info.longitude},${info.latitude}&amp;key=9e4b883b2a6d8482638c56b6f60078b7&iconheight=32&iconwidth=30&icons=http://webapi.amap.com/images/marker_sprite.png">
						</div>
						</a>
					</c:if>
					<c:if test="${ not empty ditu }">
					    <a href="javascript:;">
						<div class="its_address">${info.projAddress}</div>
						
						<div id="map_box" class="its_map_block">
						</div>
						</a>
					</c:if>
					</div>
				</section>
				
				        ${questions}
                        ${hotnews}


				
${arroundbuilding}				
			</div>
		</section>
		
		<!-- 面包屑 -->
		<div class="link_boxs"><a href="${ctx}/${_city.cityPinyinAbbr}/">新房</a><span class="h_space1">&gt;</span><a href="${ctx}/${_city.cityPinyinAbbr}/loupan/"> ${_city.cityName }楼盘</a><span class="h_space1">&gt;</span><a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${info.groupId}/"> ${buildName}</a></div>
		
		 <c:if test="${info.phone400!=0 }">
		<!-- 400电话 -->
		<div class="hotline_phone">
			<a href="tel:4008882200,${info.phone400 }" class="hotline_phone_btn" phone="${info.phone400}"><span class="pnone_icon"></span>免费咨询 400-888-2200 转 ${info.phone400 }</a>
		</div>
		</c:if>
		
		<c:if test="${info.phone400==0 }">
				<!-- 400电话 -->
		<div class="hotline_phone">
			<a href="tel:4006782020" class="hotline_phone_btn" phone="${info.phone400}"><span class="pnone_icon"></span>免费咨询 400-678-2020</a>
		</div>
		</c:if>
		<div class="back2top" data-title="返回顶部"></div>
		
	</div>
	
	<div class="over"></div>
	<!-- 电话弹层 // -->
	
	<div class="alert_box" id="check_net">
     	<p class="p1">请在接通后拨分机号</p>
     	<p class="p1">${info.phone400 }</p>
     	<div class="itos_btn1" id="check_net_confirm"><a href="javascript:;">好的</a></div>
		<div class="its_close_btn"></div>
    </div>
	<!-- // 电话弹层 -->
	
	<!-- 报名成功弹层 // -->
	<div class="alert_box signup_success_box" id="signup_success1">
     	<h3 class="signup_success_tit"><em class="signup_success_icon"></em>报名成功！</h3>		
     	<p class="p2">楼盘的销售人员会和您联系，请保持电话畅通。</p>
		<p class="p2 dingyue_success1" style="display:none;">当楼盘有新动态时会第一时间短信通知您。</p><!-- 订阅后，显示这里 -->
    </div>
	<!-- // 报名成功弹层 -->
	
	<!-- 报名成功弹层 // -->
	<div class="alert_box signup_success_box" id="signup_have_success1">
     	<h3 class="signup_success_tit"><em class="signup_success_icon"></em>报名成功！</h3>		
     	<p class="p2">您已报名过本楼盘的团购。</p>
    </div>
	<!-- // 报名成功弹层 -->

	<!-- 报名失败弹层 // -->
	<div class="alert_box signup_fail_box" id="signup_fail1">
     	<h3 class="signup_fail_tit red1"><em class="signup_fail_icon"></em>报名失败！</h3>
     	<p class="p2">请检查网络后重试</p>
    </div>
	<!-- // 报名失败弹层 -->
	<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/phone/snippets/wap_detail_index_v1.0.js"></script>
	<%@ include file="/views/pv.jsp"%>
</body>
</html>
