<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
<c:if test="${not empty disCouentHouse}">
						<article>
							<h2 class="border_wrap">打折楼盘</h2>
							<ul class="article_list article_img">
							<c:forEach var="proj" items="${disCouentHouse}">
								<li role="touch_bg">
									<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${proj.groupId}/k_____DZLP_/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/loupan/${proj.groupId}/k_____DZLP_/"class="clearfix">
										<img src="${proj.url}" alt="${proj.projNameNoSplit }" class="img fl" />
										<dl>
											<dt>${proj.projNameNoSplit }</dt>
											<dd class="clearfix">${proj.projAddress}<span class="price fr">${proj.avgPriceShow}</span></dd>
											<dd>
											<c:if test="${not empty proj.discount}">
												<span class="mark discount">${proj.discount}</span>
											</c:if>
											
											</dd>
										</dl>
									</a>
								</li>
								</c:forEach>
							</ul>
							<c:if test="${nums>=3 }">
							<a role="touch_bg" href="${ctx}/${_city.cityPinyinAbbr}/loupan/k_____DZLP_/" url="${ctx}/${_city.cityPinyinAbbr}/loupan/k_____DZLP_/" class="more">查看更多打折楼盘<b></b></a>
							</c:if>
						</article>
						</c:if>
<!-- // 打折楼盘 -->