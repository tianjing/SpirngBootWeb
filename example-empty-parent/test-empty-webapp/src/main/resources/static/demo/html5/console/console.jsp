<%@ page import="org.springframework.web.socket.WebSocketHandler" %>
<%@ page import="org.springframework.web.socket.WebSocketSession" %>
<%@ page import="org.springframework.web.socket.WebSocketMessage" %>
<%@ page import="org.springframework.web.socket.CloseStatus" %>
<%@ page import="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.lang.reflect.Method" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="org.springframework.beans.factory.support.DefaultListableBeanFactory" %><%--
  Created by IntelliJ IDEA.
  User: tian_
  Date: 2017-10-23
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    tgtools.web.platform.PlatformDispatcherServletFactory.getDefaultDispatcher().removeWebsocket("/mywebsocket");
%>