<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
	<c:if test="${not empty littleHouse }">

	<!-- 小户型楼盘 // -->

	<ul class="featured_property_box_list">
		<ul class="featured_property_box_list">
			<c:forEach var="proj" items="${littleHouse}">
				<li><a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${proj.groupId}/k_____XHX_/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/loupan/${proj.groupId}/k_____XHX_/"
					class="clearfix">
						<div class="fl its_img">
							<img alt="${proj.projName }" src="${proj.url}" width="120" height="90"
								alt="${proj.projName}" />
						</div>
						<div class="its_content">
							<p class="it_name">${proj.projName}</p>
							<p class="it_address">${proj.projAddress}</p>
							<p class="it_price">${proj.avgPriceShow}</p>
							<p class="it_zhekou">
								<em class="huxing1"></em>
								<c:if test="${proj.roomOne != '' }">一居室${proj.roomOne}	</c:if>
								<c:if test="${proj.roomTwo != '' }">二居室${proj.roomTwo}	</c:if>
								<c:if test="${proj.roomTwo == '' && proj.roomOne == ''  }">暂无数据</c:if>
							</p>

						</div>
				</a></li>
			</c:forEach>
		</ul>
		<c:if test="${nums>=3 }">
		<div class="look_more">
			<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/k_____XHX_/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/loupan/k_____XHX_/">查看更多小户型楼盘<em
				class="look_more_icon"></em></a>
		</div>
		</c:if>

<!-- // 小户型楼盘 -->
		</c:if>