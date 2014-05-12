<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ include file="/common/taglibs.jsp"%>
    	    						<!-- 楼盘导购 // -->
			<c:if test="${not empty list}">
			<section class="wap_module editor_recommend" data-title="楼盘导购-模块">				
				<header class="public_title1">
					<h2 class="its_tit1">楼盘导购<em class="its_icon1"></em></h2>
					<a href="${ctx}/${_city.cityPinyinAbbr}/daogou/" class="public_title_more1" data-title="查看更多"></a>
				</header>			
				<section class="editor_recommend_loupan" id="wrapper" data-title="楼盘导购-模块-内容">
					<div class="loupan_img_container" id="scroller">
						<ul class="loupan_img_ul">
						<c:forEach var="dg" items="${list}">
							<a href="${ctx}/${_city.cityPinyinAbbr}/daogou/${dg.id}/" role="text" url="${ctx}/${_city.cityPinyinAbbr}/daogou/${dg.id}/">
							<li class="its_img_box">
								<img class="its_img1" src="${dg.pic}" width="100%" alt="搜狐焦点图片" />
								<div class="its_nameprice1">
									${dg.title}
								</div>
							</li>
							</a>
							</c:forEach>
						</ul>
					</div>
					<div class="loupan_point_boxs">
					<c:forEach var="x" begin="1" end="${count }" step="1">
						<c:if test="${x==1}">
						<i class="current"></i>
						</c:if>
						<c:if test="${x>1 }">
						<i></i>
						</c:if>
					</c:forEach>
					</div>
				</section>
			</section>
			</c:if>
			<!-- // 主编推荐-模块 -->