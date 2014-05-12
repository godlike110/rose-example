<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>		
			<!-- 购房宝典-模块 // -->
						<article>
							<h2 class="border_wrap">购房宝典</h2>
							<ul class="article_list article_line">
							<c:forEach var="proj" items="${guideList}">
								<li role="touch_bg">
									<a href="${ctx}/${_city.cityPinyinAbbr}/baodian/${proj.id}/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/baodian/${proj.id}/" class="clearfix">
										<dl>
											<dt class="clearfix">${proj.title }<span class="timestamp fr"><fmt:formatDate value="${proj.createTime }" pattern="M月d日"/></span></dt>
											<dd>${proj.summary}</dd>
										</dl>
									</a>
								</li>
								</c:forEach>
							</ul>
							<a role="touch_bg" href="${ctx}/${_city.cityPinyinAbbr}/baodian/" url="${ctx}/${_city.cityPinyinAbbr}/baodian/" class="more">查看更多宝典<b></b></a>
						</article>
			<!-- // 购房宝典-模块 -->