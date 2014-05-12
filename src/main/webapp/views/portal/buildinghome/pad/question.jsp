<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>						
						<article>
							<h2 class="border_wrap">热门咨询</h2>
							<ul class="article_list article_line qr_mod">
							<c:if test="${empty  questions}">
								<p class="empty">暂无热门咨询</p>
								</c:if>
								<c:forEach var="question" items="${questions}">
								<li role="touch_bg">
									<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${info.groupId}/zixun/${question.id}/">
										<dl>
											<dt>问：${question.question}</dt>
											<c:choose>
												<c:when test="${fn:length(question.answer) > 28}">
												<dd>答：<c:out value="${fn:substring(question.answer, 0, 28)}..." /></dd>
												</c:when>
												<c:otherwise>
												<dd>答：${question.answer}</dd>
												</c:otherwise>
											</c:choose>
											<dd class="mt10 clearfix">
												<span class="count">有用<em>${question.usefulCount }</em></span>
												<span class="fr"><fmt:formatDate value="${question.updateTime }" pattern="M月d日"/></span>
											</dd>
										</dl>
									</a>
								</li>
								</c:forEach>
							</ul>
							<c:if test="${qcount==3}">
							<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${info.groupId}/zixun/" role="touch_bg" class="more">查看更多咨询<b></b></a>
							</c:if>
						</article>