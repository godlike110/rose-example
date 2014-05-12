<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>	
<c:if test="${not empty newHouse }">			
					<!-- 最新开盘 // -->

	<ul class="featured_property_box_list">
				<c:forEach var="proj" items="${newHouse}">
		<li><a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${proj.groupId}/k_____ZXKP_/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/loupan/${proj.groupId}/k_____ZXKP_/" class="clearfix">
				<div class="fl its_img">
					<img alt="${proj.projName }"
						src="${proj.url}" width="120" height="90" alt="${proj.projName}" />
				</div>
				<div class="its_content">
					<p class="it_name">${proj.projName}</p>
					<p class="it_address">${proj.projAddress}</p>
					<p class="it_price">
						${proj.avgPriceShow}
					</p>
					<p class="it_zhekou">
						<em class="time1"></em>${proj.saleDateDetail}
					</p>
				</div>
		</a></li>
	     </c:forEach>
						</ul>
<c:if test="${nums>=3 }">
						<div class="look_more"><a href="${ctx}/${_city.cityPinyinAbbr}/loupan/k_____ZXKP_/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/loupan/k_____ZXKP_/">查看更多最新开盘<em class="look_more_icon"></em></a></div>

											</c:if>
					 <!-- // 最新开盘 -->
											</c:if>
