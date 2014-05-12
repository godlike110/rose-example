<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
<article class="room_size">
	<h2 class="border_wrap">户型图</h2>
	<c:choose>
		<c:when test="${layoutCount == 0 }" ><p class="empty">暂无户型图</p>	</c:when>
		<c:otherwise>
			<ol>
				<c:forEach var="l" items="${layouts }" varStatus="status" end="4">
					<li role="touch_bg"><a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${groupId}/huxingtu/${l.type}/">${l.minMaxArea}</a></li>
				</c:forEach>
			</ol>
			<c:if test="${layoutCountt >=6 }">
				<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${groupId}/huxingtu/" role="touch_bg" class="more">查看更多户型图<b></b></a>
			</c:if>
		</c:otherwise>
	</c:choose>
</article>