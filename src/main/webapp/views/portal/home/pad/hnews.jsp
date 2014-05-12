<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
						<article class="article_news">
							<h2 class="border_wrap">新闻</h2>
							<ul class="article_list article_line">
							<c:forEach var="news" items="${newsList}">
								<li role="touch_bg">
									<a href="${ctx}/${_city.cityPinyinAbbr}/xinwen/${news.newsId}/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/xinwen/${news.newsId}/">
										<dl>
											<dt class="clearfix">${news.title }<span class="timestamp fr">${news.time }</span></dt>
											<dd>${news.description}</dd>
										</dl>
									</a>
								</li>
								</c:forEach>
							</ul>
							<a role="touch_bg" href="${ctx}/${_city.cityPinyinAbbr}/xinwen/" url="${ctx}/${_city.cityPinyinAbbr}/xinwen/" class="more">查看更多新闻<b></b></a>
						</article>
			<!-- // 房产新闻-模块 -->