<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>	
				<section class="hotquestion_container" data-title="热门咨询">
					<header class="public_title1" data-title="热门咨询-标题">
						<h2 class="its_tit1">热门咨询<em class="its_icon1"></em></h2>
					</header>
					<c:if test="${empty  questions}">
					<div class="nothave_info">暂无热门咨询</div>
					</c:if>
					<ul class="hotquestion_list_ul">
					<c:forEach var="question" items="${questions}">
						<li>
							<a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${info.groupId}/zixun/${question.id}/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/loupan/${info.groupId}/zixun/${question.id}/" class="clearfix">
								<div class="fl its_question_icon"><em class="question_icon"></em></div>
								<div class="its_content">
									<p class="ask_paragram">问：${question.question}</p>
									<p class="answer_paragram">
									<c:choose>
										<c:when test="${fn:length(question.answer) > 28}">
										答：<c:out value="${fn:substring(question.answer, 0, 28)}..." />
										</c:when>
										<c:otherwise>
										答：${question.answer}
										</c:otherwise>
									</c:choose>
									</p>
									<div class="about_its"><span class="fl have_use">有用<em class="red1">${question.usefulCount }</em></span><fmt:formatDate value="${question.updateTime }" pattern="M月d日"/></div>
								</div>
							</a>
						</li>
						</c:forEach>						
					</ul>
					<c:if test="${qcount==3}">
					<div class="look_more"><a href="${ctx}/${_city.cityPinyinAbbr}/loupan/${info.groupId}/zixun/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/loupan/${info.groupId}/zixun/" class="">查看更多咨询<em class="look_more_icon"></em></a></div>
					</c:if>
				</section>		