<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>			
						<article class="art_text">

							<h2 class="border_wrap">周边楼盘</h2>
							<ul class="article_list article_line around_mod">
													<c:if test="${not empty  arroundbuilding}">
							<c:forEach var="proj" items="${arroundbuilding}">
								<li role="touch_bg">
									<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${proj.groupId}/k______/">${proj.projName}</a>
									<p class="clearfix">
										<c:if test="${not empty fn:trim(proj.districtName) }">
										<span class="area">${proj.districtName}</span>
										</c:if>
										<span class="price fr">
${proj.avgPriceShow}</span>
									</p>
								</li>
								</c:forEach>
												</c:if>
												<c:if test="${empty  arroundbuilding}">
												<p class="empty">暂无周边楼盘</p>
												</c:if>
							</ul>

						</article>