<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
			<!-- 房产新闻-模块 // -->
			<section class="wap_module property_news" data-title="房产新闻-模块">
				<header class="public_title1" data-title="房产新闻-模块-标题">
					<h2 class="its_tit1">房产新闻<em class="its_icon1"></em></h2>
				</header>
				<section class="property_news_list">
					<ul class="news_list_ul">
					 <c:forEach var="news" items="${newsList}">
						<li>
							<a href="${ctx}/${_city.cityPinyinAbbr}/xinwen/${news.newsId}/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/xinwen/${news.newsId}/" class="clearfix">
								<p class="its_date_p">${news.time }</p>
								<p class="its_nr_p">${news.title}</p>
							</a>
						</li>
						 </c:forEach>
					</ul>
					<div class="look_more"><a href="${ctx}/${_city.cityPinyinAbbr}/xinwen/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/xinwen/">查看更多房产新闻<em class="look_more_icon"></em></a></div>
				</section>
			</section>
			<!-- // 房产新闻-模块 -->