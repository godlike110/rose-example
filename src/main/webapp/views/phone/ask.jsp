<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<meta content="telephone=no" name="format-detection" />
<title>${questionInfo.question}-购房咨询-搜狐焦点</title>
<meta name="keywords" content="${questionInfo.question }" />
<meta name="description" content="${summary }"/>
<%@ include file="/views/icon.jsp" %>
<link rel="stylesheet" type="text/css" href="http://192.168.242.44/sceapp/focus_static/wap/common/css/css_mobile_common_v1.1.css">
<link rel="stylesheet" type="text/css" href="http://192.168.242.44/sceapp/focus_static/wap/phone/css/wap_public_v1.0.css">
<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/common/lib/zepto-1.1.3.min.js"></script>
<script type="text/javascript" src="http://192.168.242.44/sceapp/focus_static/wap/phone/snippets/wap_common_v1.0.js"></script>
<script type="text/javascript">
var city = ${city};
var groupId = ${bl.groupId}; 
var phone400 = ${bl.phone400};
</script>
</head>
<body class="wap_hotquestion_all wap_hotquestion_all2 refer_list_body">
	<div class="wap_container">
		<!-- 楼盘咨询正文页—顶部导航 -->
		<header class="top_nav">
			<c:choose>
						<c:when test="${returnFlag == 1}">
								<a href="${ctx }/${_city.cityPinyinAbbr }/" class="go_back" data-title="返回上一级">首页</a>
						</c:when>
						<c:when test="${returnFlag == 0}">
								<a href="javascript:history.back();" class="go_back" data-title="返回上一级">返回</a>
						</c:when>
			</c:choose>		
			<span class="search_title">楼盘咨询正文</span>
			
		</header>
		
		<!-- 楼盘咨询正文页主要内容板块 // -->
		<section class="hotquestion_list_main">
			<div class="hotquestion_list_container">	
				<section class="hotquestion_container" data-title="楼盘咨询正文">
					<div class="hotquestion_info_ask border_e9e9e9">
						<p class="its_writer_time">${questionInfo.userName }<span class="h_space2"><fmt:formatDate value="${questionInfo.createTime }" pattern="yyyy年M月d日"/></span></p>
						<p class="answer_paragram">${questionInfo.question }</p>
					</div>
					<div class="hotquestion_info_answer">
						<p class="its_writer_time">${questionInfo.editorDesc }<span class="h_space2"><fmt:formatDate value="${questionInfo.updateTime }" pattern="yyyy年M月d日"/></span></p>
						<p class="answer_paragram">答：${questionInfo.answer }</p>
						<div class="tr"><span class="have_use">有用<em class="red1">${questionInfo.usefulCount }</em></span></div>
					</div>
				</section>
				
				<section class="hotquestion_container" data-title="相关楼盘">
					<header class="public_title1 public_title1_noborder" data-title="相关楼盘-标题">
						<h2 class="its_tit1">相关楼盘<em class="its_icon1"></em></h2>
					</header>
					<ul class="search_list_container" data-title="搜索结果-列表">
							<li>
							<a href="${ctx }/${_city.cityPinyinAbbr }/loupan/${bl.groupId}/" role="text" url="" class="clearfix">
								<div class="fl its_img"><img alt="${bl.projName }" src="${bl.url }"></div>
								<div class="its_content">
									<p class="it_name">${bl.projName }									
									<c:choose>
										   <c:when test="${bl.saleStatus == 0}">
												  <em class='in_sale' data-title=在售></em>
										   </c:when>
										   <c:when test="${bl.saleStatus == 1}">
												  <em class='wait_sale' data-title=待售></em>
										   </c:when>
										   <c:when test="${bl.saleStatus == 2}">
												  <em class='last_sale' data-title=尾盘></em>
										   </c:when>
										   <c:otherwise>
												  <em class='out_sale' data-title=售罄></em>
										   </c:otherwise>
									</c:choose>
									</p>
									<p class="it_address">${bl.projAddress}</p>

									<p class="it_price">${bl.priceForShow}</p>

									<c:if test="${ not empty fn:trim(bl.discount)}">
									<p class="it_zhekou"><em class="zhekou1"></em>${bl.discount}</p>
									</c:if>
								</div>
							</a>
							</li>
					</ul>
				</section>
				
			</div>
		</section>
		
		<!-- 面包屑 -->
		<div class="link_boxs"><a href="/${_city.cityPinyinAbbr }/">新房</a>
			<span class="h_space1">></span><a href="${ctx }/${_city.cityPinyinAbbr }/loupan/">${_city.cityName }楼盘</a>
			<span class="h_space1">></span><a href="${ctx }/${_city.cityPinyinAbbr }/loupan/${bl.groupId}/">
			<c:choose>
				   <c:when test="${fn:length(bl.projName) > 4}">
						  ${fn:substring(bl.projName, 0, 4)}...
				   </c:when>
				   <c:otherwise>
				   		  ${bl.projName }
				   </c:otherwise>
			</c:choose>
			</a>
			<span class="h_space1">></span>楼盘咨询</div>	
		<!-- 400电话 -->
		<c:if test="${bl.phone400 != 0 }">
		<div class="hotline_phone">
			<div><a href="tel:4008882200,${bl.phone400}" class="footer_ban_tel"><b></b>免费咨询</a></div>
			<div><a href="http://m.focus.cn/static/appwap.html#weixin.qq.com" class="footer_ban_download"><b></b>下载客户端提问</a></div>
		</div>
		</c:if>			
		<c:if test="${bl.phone400==0 }">
		<div class="hotline_phone">
			<div><a href="tel:4006782020" class="footer_ban_tel"><b></b>免费咨询</a></div>
			<div><a href="http://m.focus.cn/static/appwap.html#weixin.qq.com" class="footer_ban_download"><b></b>下载客户端提问</a></div>
		</div>
		</c:if>
				
		<div class="back2top" data-title="返回顶部"></div>
		
	</div>
	
	<!-- 电话弹层 // -->
	<div class="over"></div>
	<div class="alert_box" id="check_net">
     	<p class="p1">请在接通后拨分机号</p>
     	<p class="p1">${bl.phone400 }</p>
     	<div class="itos_btn1" id="check_net_confirm"><a href="javascript:;">好的</a></div>
		<div class="its_close_btn"></div>
    </div>
	<!-- // 电话弹层 -->
	<%@ include file="/views/pv.jsp"%>
</body>
</html>