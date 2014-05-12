<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>	
    <%@ include file="../../../../common/taglibs.jsp"%>	
    <c:if test="${not empty relate_news }">	
    		<section class="wap_module relative_news" data-title="相关新闻-模块">
					<header class="public_title1" data-title="相关新闻-模块-标题">
						<h2 class="its_tit1">相关资讯<em class="its_icon1"></em></h2>
					</header>
					
					<section class="property_news_list">
						<ul class="news_list_ul">
							<c:forEach var="relateNews" items="${relate_news}" varStatus="status">
								<li><a href="${ctx}/${cityName }/zixun/${relateNews.relatedId}/"><p>${relateNews.title}</p></a></li>
							</c:forEach>
						</ul>
					</section>
				</section>
			</c:if>