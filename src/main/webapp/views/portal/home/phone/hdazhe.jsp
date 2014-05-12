<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
<c:if test="${not empty disCouentHouse}">

	<!-- 打折楼盘 // -->

	<ul class="featured_property_box_list">
				<c:forEach var="proj" items="${disCouentHouse}">
		<li><a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${proj.groupId}/k_____DZLP_/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/loupan/${proj.groupId}/k_____DZLP_/" class="clearfix">
				<div class="fl its_img">
					<img alt="${proj.projNameNoSplit }"
						src="${proj.url}" width="120" height="90" alt="${proj.projName}" />
				</div>
				<div class="its_content">
					<p class="it_name">${proj.projNameNoSplit}</p>
					<p class="it_address">${proj.projAddress}</p>
					<p class="it_price">
						${proj.avgPriceShow}
					</p>
					<c:if test="${not empty proj.discount}">
					<p class="it_zhekou">
						<em class="zhekou1"></em>${proj.discount}
					</p>
					</c:if>			
				</div>
		</a></li>
	     </c:forEach></ul>
	     <c:if test="${nums>=3 }">
	<div class="look_more">
		<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/k_____DZLP_/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/loupan/k_____DZLP_/">查看更多打折楼盘<em class="look_more_icon"></em></a>
		</c:if>
	</div>

<!-- // 打折楼盘 -->
	</c:if>