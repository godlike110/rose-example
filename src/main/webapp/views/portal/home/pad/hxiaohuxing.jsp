<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
<c:if test="${not empty littleHouse}">
<article>
	<h2 class="border_wrap">小户型</h2>
	<ul class="article_list article_img">
		<c:forEach var="proj" items="${littleHouse}">
			<li role="touch_bg"><a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${proj.groupId}/k_____XHX_/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/loupan/${proj.groupId}/k_____XHX_/"
				class="clearfix"> <img src="${proj.url}" alt="${proj.projName }" class="img fl" />
					<dl>
						<dt>${proj.projName }</dt>
						<dd class="clearfix">
							${proj.projAddress}<span class="price fr">${proj.avgPriceShow}</span>
						</dd>

						
							<dd>
	
								<span class="mark house_type">
														<c:if test="${proj.roomOne != '' }">一居室${proj.roomOne}	</c:if>
														<c:if test="${proj.roomTwo != '' }">二居室${proj.roomTwo}	</c:if>
														<c:if test="${proj.roomTwo == '' && proj.roomOne == ''  }">暂无数据</c:if>
														</span>

							</dd>


					</dl>
			</a></li>
		</c:forEach>
	</ul>
	<c:if test="${nums>=3 }">
	<a role="touch_bg" href="${ctx}/${_city.cityPinyinAbbr}/loupan/k_____XHX_/" url="${ctx}/${_city.cityPinyinAbbr}/loupan/k_____XHX_/" class="more">查看更多小户型<b></b></a>
	</c:if>
</article>
</c:if>
<!-- // 小户型楼盘 -->