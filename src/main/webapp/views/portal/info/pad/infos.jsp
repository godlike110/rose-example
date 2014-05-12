<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>	
<%@ include file="/common/taglibs.jsp"%>	
    		<c:if test="${not empty relate_news}">
    		<h2 class="border_wrap">相关资讯</h2>
			<ul class="article_list article_line">
			<c:forEach var="relateNews" items="${relate_news}" varStatus="status">					
				<li><a href="${ctx}/${cityName }/zixun/${relateNews.relatedId}/"><p>${relateNews.title}</p></a></li>
			</c:forEach>
			</ul>
			</c:if>