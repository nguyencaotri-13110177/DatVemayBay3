<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <header class="trasparent_nav">
        <div class="wrapper">
            <div class="logo">
                <a href="main.ute"><img src="img/logo.png" alt="Fertile"></a>
            </div>

            <nav>

            <ul>
                <c:if test="${session_user==null}">
                    <li><a href=\"register.ute\"><spring:message code="lable.login.SignUp" text="default text"/></a></li>
                    <li><a href=\"login.ute\"><spring:message code="lable.login.LogIn" text="default text"/></a></li>
                </c:if>

                <c:if test="${session_user!=null}">
                    <li><spring:message code="lable.login.Welcome" text="default text"/> <c:out value="${session_user}"/></li>
                    <li><a><spring:message code="lable.login.LogOut" text="default text"/></a></li>
                </c:if>



                
                <li><spring:message code="lable.Language" text="Ngôn ngữ"/>:  <a href="?lang=en">English</a> | <a href="?lang=vi">Việt Nam</a></li>
            </ul>
            </nav>
        </div>
    </header>
</html>
