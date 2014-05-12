<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>	
				<section class="hotquestion_container" data-title="动态">
					<header class="public_title1" data-title="动态-标题">
						<h2 class="its_tit1">最新动态<em class="its_icon1"></em></h2>
					</header>
					<c:if test="${empty  recentNews}">
					<div class="nothave_info">暂无动态</div>
					</c:if>
					<ul class="hotquestion_list_ul">
					<c:forEach var="news" items="${recentNews}">
						<li>
							<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${info.groupId}/dongtai/${news.infoid}/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/loupan/${info.groupId}/dongtai/${news.infoid}/" class="clearfix">
								<div class="fl its_question_icon"><em class="dynamic_icon"></em></div>
								<div class="its_content">
									<p class="ask_paragram">${news.infoname}</p>
								</div>
							</a>
						</li>
						</c:forEach>
					</ul>
					<c:if test="${qcount>=3}">
					<div class="look_more"><a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${info.groupId}/dongtai/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/loupan/${info.groupId}/dongtai/" class="">查看更多动态<em class="look_more_icon"></em></a></div>
					</c:if>
				</section>