<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>				
						<c:if test="${not empty newHouse }">
						<article>
							<h2 class="border_wrap">最新开盘</h2>
							<ul class="article_list article_img">
															<c:forEach var="proj" items="${newHouse}">
								<li role="touch_bg">
									<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${proj.groupId}/k_____ZXKP_/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/loupan/${proj.groupId}/k_____ZXKP_/" class="clearfix">
										<img src="${proj.url}" alt="${proj.projName }" class="img fl" />
										<dl>
											<dt>${proj.projName }</dt>
											<dd class="clearfix">${proj.projAddress}<span class="price fr">${proj.avgPriceShow}</span></dd>
											<dd>
												<span class="mark date">${proj.saleDateDetail}</span>
											</dd>
										</dl>
									</a>
								</li>
								</c:forEach>
							</ul>
							<c:if test="${nums>=3 }">
							<a role="touch_bg" href="${ctx}/${_city.cityPinyinAbbr}/loupan/k_____ZXKP_/" url="${ctx}/${_city.cityPinyinAbbr}/loupan/k_____ZXKP_/" class="more">查看更多最新开盘<b></b></a>
							</c:if>
						</article> <!-- // 最新开盘 -->
						</c:if>