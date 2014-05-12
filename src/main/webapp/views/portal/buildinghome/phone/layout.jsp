<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> <%@ include file="/common/taglibs.jsp"%>
<section class="huxingtu_main" data-title="户型图">
	<header class="public_title1" data-title="户型图-标题">
		<h2 class="its_tit1">
			户型图<em class="its_icon1"></em>
		</h2>
	</header>
	<c:choose>
		<c:when test="${layoutCount == 0 }"><div class="nothave_info">暂无户型图</div></c:when>
		<c:otherwise>
			<div class="detail_info_container">
				<ul class="news_list_ul">
				<c:forEach var="l" items="${layouts }" end="4">
					<li>
						<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${groupId}/huxingtu/${l.type}/" role="text" url="/{_city.cityPinyinAbbr}/loupan/${groupId}/huxingtu/${l.type}/" class="clearfix"> 
							<span class="public_title_more1"></span>
							<p class="its_nr_p">${l.minMaxArea }</p>
						</a>
					</li>
				</c:forEach>
				</ul>
				<c:if test="${layoutCount >= 6 }">
				<div class="look_more">
					<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${groupId}/huxingtu/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/loupan/${groupId}/huxingtu/1/"class="">
						查看全部户型图<em class="look_more_icon"></em>
					</a>
				</div>
				</c:if>
			</div>	
		</c:otherwise>
	</c:choose>
</section>