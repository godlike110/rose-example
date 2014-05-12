<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>		
			<!-- 购房宝典-模块 // -->
			<section class="wap_module property_news buyhouse_baodian1" data-title="购房宝典-模块">
				<header class="public_title1" data-title="购房宝典-模块-标题">
					<h2 class="its_tit1">购房宝典<em class="its_icon1"></em></h2>
				</header>
				<section class="property_news_list">
					<ul class="news_list_ul">
                       <c:forEach var="proj" items="${guideList}">
						<li>
							<a href="${ctx}/${_city.cityPinyinAbbr}/baodian/${proj.id}/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/baodian/${proj.id}/" class="clearfix">
								<p class="its_date_p"><fmt:formatDate value="${proj.createTime }" pattern="M月d日"/></p>
								<p class="its_nr_p">${proj.title}</p>
							</a>
						</li>
						</c:forEach>
					</ul>
					<div class="look_more"><a href="${ctx}/${_city.cityPinyinAbbr}/baodian/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/baodian/">查看更多购房宝典<em class="look_more_icon"></em></a></div>
				</section>
			</section>
			<!-- // 购房宝典-模块 -->