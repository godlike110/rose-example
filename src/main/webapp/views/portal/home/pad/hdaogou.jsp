<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
    			
    						<!-- 楼盘导购 // -->
			<c:if test="${not empty list}">
						<article>
							<div class="loupan_daogou_title">
								<a href="${ctx}/${_city.cityPinyinAbbr}/daogou/" class="look_more"></a>
								<h2 class="border_wrap">楼盘导购</h2>
							</div>
							<div class="slide">
								<div class="slide_wrap" id="slide_scroll">
									<div class="slide_list clearfix">
										<c:forEach var="dg" items="${list}">
										<figure>
											<a href="${ctx}/${_city.cityPinyinAbbr}/daogou/${dg.id}/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/daogou/${dg.id}/"><img src="${dg.pic}" alt="搜狐焦点图片"/></a>
											<figcaption class="clearfix">
												<h3>${dg.title}</h3>
											</figcaption>
										</figure>
										</c:forEach>
									</div>
								</div>
								<div class="slide_nav">
									<ul class="clearfix">			
									<c:forEach var="x" begin="1" end="${count }" step="1">
                                         <c:if test="${x==1}">
                                         <li class="current"><a href="javascript:;">1</a></li>
                                         </c:if>
                                         <c:if test="${x>1 }">
                                         <li><a href="javascript:;">${x}</a></li>
                                         </c:if>
                                    </c:forEach>
									</ul>
									
								</div>
							</div>
						</article>
						</c:if>
			<!-- // 主编推荐-模块 -->