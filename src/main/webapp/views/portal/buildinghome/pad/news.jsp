<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>								
						<article>
							<h2 class="border_wrap">动态</h2>
							<c:if test="${empty  recentNews}">
							<p class="empty">暂无动态</p>
							</c:if>
							<ul class="article_list article_line dynamic_mod">
							<c:forEach var="news" items="${recentNews}">
								<li role="touch_bg">
									<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${info.groupId}/dongtai/${news.infoid}/">${news.infoname}</a>
								</li>
								</c:forEach>
							</ul>
							<c:if test="${qcount>=3}">
							<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${info.groupId}/dongtai/" role="touch_bg" class="more">查看更多动态<b></b></a>
							</c:if>
						</article>