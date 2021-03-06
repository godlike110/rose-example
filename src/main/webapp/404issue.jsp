<!DOCTYPE html>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="com.alibaba.fastjson.JSON"%>
<%@page import="cn.focus.dc.focuswap.utils.DeviceUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="cn.focus.dc.focuswap.service.CityService" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="cn.focus.dc.focuswap.model.DictCity" %>
<%@ page import="org.springframework.mobile.device.Device" %>
<%@ page import="org.springframework.mobile.device.DeviceResolver" %>
     
<%
         String domain = "http://house.focus.cn/?cfrom=mobile";
         WebApplicationContext ctx = (WebApplicationContext) application
                 .getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
         CityService cityService = (CityService) ctx.getBean("cityService");
         DictCity city = null;
         if (null != cityService) {
             city = cityService.getCityLocatedInfo((HttpServletRequest) request);

             if (null != city) {
                 domain = "http://" + city.getDomainName();
                 request.setAttribute("_city", city);
             }
         }
         DeviceResolver deviceResolver = (DeviceResolver) ctx.getBean("deviceResolver");
         Device device = null;
         if (null != deviceResolver) {
             device = deviceResolver.resolveDevice(request);
         }

         //refer
         String referUrl = request.getHeader("Referer");
         domain = request.getServerName();
         String backUrl = "";
         if (referUrl != null && referUrl.indexOf(domain) != -1 ) {
             backUrl = referUrl;
             int temFront = referUrl.indexOf(domain+"/");
             String removeFront = referUrl.substring(temFront+(domain+"/").length(),referUrl.length());
             if(removeFront.indexOf("/") != -1){
	             int temBack = removeFront.indexOf("/");
	             String pinyin = removeFront.substring(0,temBack);
	             city = cityService.getCityByNameOrPinYin(pinyin);
             }
         }else{
             if(city == null){
                 backUrl = "/";
             }else{
                 backUrl = "/"+city.getCityPinyinAbbr()+"/";
             }
             
         }
         request.setAttribute("backUrl", backUrl);
         JSONObject jo = (JSONObject)JSON.toJSON(city);
         String d = DeviceUtils.device(request, response, device);
         request.setAttribute("city", jo);
         if ("phone".equals(d)) {
             request.setAttribute("mobile", device.isMobile());
             request.getRequestDispatcher(request.getContextPath() + "/views/phone/404.jsp?").forward(request,
                     response);
         } else {
             request.getRequestDispatcher(request.getContextPath() + "/views/pad/404.jsp")
                     .forward(request, response);
         }
     %>
