<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
				<section data-title="周边楼盘">
					<header class="public_title1" data-title="周边楼盘-标题">
						<h2 class="its_tit1">周边楼盘<em class="its_icon1"></em></h2>
					</header>
					<div class="detail_info_container">
					<c:if test="${not empty  arroundbuilding}">	
						<ul class="news_list_ul">
						<c:forEach var="proj" items="${arroundbuilding}">
							<li>
								<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${proj.groupId}/k______/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/loupan/${proj.groupId}/k______/" class="clearfix">
									<p class="its_date_p">${proj.avgPriceShow}</p>
									<p class="its_nr_p">${proj.projName}
										<c:if test="${not empty fn:trim(proj.districtName) }">
										<span class="its_strict">${proj.districtName}</span>
										</c:if>
										</p>
								</a>
							</li>
							</c:forEach>
						</ul>
					</c:if>
											<c:if test="${empty  arroundbuilding}">	
											<div class="nothave_info">
											暂无周边楼盘
											</div>
											</c:if>
					</div>
				</section>
