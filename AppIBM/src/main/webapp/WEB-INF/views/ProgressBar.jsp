<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Progress Bar</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;1,100;1,200;1,300;1,400;1,500;1,600;1,700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/ProgressBar/ProgressBar.css">
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="activePage" value="ProgressBar"/>
</jsp:include>

<h1>Course Progress</h1>

<div class="container">
    <div class="courses">
        <div id="AI" class="status-courses">

            <button class="close-button hidden"><img
                    src="${pageContext.request.contextPath}/img/x_icon.png" alt="Close"></button>
            <h2>Artificial Intelligence Courses</h2>
            <div class="progress-bar">
                <div class="progress" style="width: ${AIPercentage}%;"></div>
            </div>

            <div class="percentage">
                <span>${AIPercentage}%</span>

            </div>
            <c:forEach var="course" items="${AI}">
                <p class="lighthover"><a href="${course.url}"
                                         target="_blank">
                        ${course.courseName}</a></p>

            </c:forEach>
        </div>


        <div id="CC" class="status-courses">
            <button class="close-button hidden"><img
                    src="${pageContext.request.contextPath}/img/x_icon.png" alt="Close"></button>
            <h2>Cloud Computing</h2>
            <div class="progress-bar">
                <div class="progress" style="width: ${CCPercentage}%;"></div>
            </div>

            <div class="percentage">
                <span>${CCPercentage}%</span>
            </div>
            <c:forEach var="course" items="${CC}">
                <p class="lighthover"><a href="${course.url}"
                                         target="_blank">
                        ${course.courseName}</a></p>
            </c:forEach>
        </div>


        <div id="DT" class="status-courses">
            <button class="close-button hidden"><img
                    src="${pageContext.request.contextPath}/img/x_icon.png" alt="Close"></button>
            <h2>Design Thinking</h2>
            <div class="progress-bar">
                <div class="progress" style="width: ${DTPercentage}%;"></div>
            </div>

            <div class="percentage">
                <span>${DTPercentage}%</span>
            </div>
            <c:forEach var="course" items="${DT}">
                <p class="lighthover"><a href="${course.url}"
                                         target="_blank">
                        ${course.courseName}</a></p>
            </c:forEach>
        </div>
    </div>

</div>
<script src="${pageContext.request.contextPath}/js/scrollbar.js"></script>
<script src="${pageContext.request.contextPath}/js/ProgressBar/ProgressBar.js"></script>
</body>
</html>
