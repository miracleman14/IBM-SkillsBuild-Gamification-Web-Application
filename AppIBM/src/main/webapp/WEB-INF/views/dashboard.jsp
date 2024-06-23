<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome to IBM Skills Build</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/dashboard/dashboard.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;1,100;1,200;1,300;1,400;1,500;1,600;1,700&display=swap"
          rel="stylesheet">
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="activePage" value="dashboard"/>
</jsp:include>
<div class="container">
    <div class="title">
        <h1>SkillsBuild Dashboard</h1>
    </div>

    <div class="courses">
        <%-- Create the containers for Available, Started, and Completed courses --%>
        <div id="available-courses" class="status-courses">
            <button class="close-button hidden"><img
                    src="${pageContext.request.contextPath}/img/x_icon.png" alt="Close"></button>
            <h3>Available Courses</h3>
            <%-- Iterate over the list of courses and add the available ones --%>
            <c:forEach var="courseView" items="${courseViews}">
                <c:if test="${courseView.status == 'AVAILABLE'}">
                    <p class="lighthover"><a href="${courseView.url}"
                                             target="_blank">${courseView.pathName}
                        - ${courseView.courseName}</a></p>
                    <p class="course-description hidden">${courseView.description}</p>
                    <form action="${pageContext.request.contextPath}/enroll" method="post">
                        <input type="hidden" name="userId" value="${user.id}"/>
                        <input type="hidden" name="courseId" value="${courseView.courseId}"/>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button class="course-button hidden"><img
                                src="${pageContext.request.contextPath}/img/enroll_icon.png"
                                alt="Enroll"></button>
                    </form>
                </c:if>
            </c:forEach>
        </div>
        <div id="started-courses" class="status-courses">
            <button class="close-button hidden"><img
                    src="${pageContext.request.contextPath}/img/x_icon.png" alt="Close"></button>
            <h3>Started Courses</h3>
            <%-- Iterate over the list of courses and add the started ones --%>
            <c:forEach var="courseView" items="${courseViews}">
                <c:if test="${courseView.status == 'STARTED'}">
                    <p class="lighthover"><a href="${courseView.url}"
                                             target="_blank">${courseView.pathName}
                        - ${courseView.courseName}</a></p>
                    <p class="course-description hidden">${courseView.description}</p>
                    <form action="${pageContext.request.contextPath}/complete" method="post">
                        <input type="hidden" name="userId" value="${user.id}"/>
                        <input type="hidden" name="courseId" value="${courseView.courseId}"/>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button class="course-button hidden"><img
                                src="${pageContext.request.contextPath}/img/check_icon.png"
                                alt="Complete Course"></button>
                    </form>
                </c:if>
            </c:forEach>
        </div>
        <div id="completed-courses" class="status-courses">
            <button class="close-button hidden"><img
                    src="${pageContext.request.contextPath}/img/x_icon.png" alt="Close"></button>
            <h3>Completed Courses</h3>
            <%-- Iterate over the list of courses and add the completed ones --%>
            <c:forEach var="courseView" items="${courseViews}">
                <c:if test="${courseView.status == 'COMPLETED'}">
                    <p class="lighthover"><a href="${courseView.url}"
                                             target="_blank">${courseView.pathName}
                        - ${courseView.courseName}</a></p>
                    <p class="course-description hidden">${courseView.description}</p>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/scrollbar.js"></script>
<script src="${pageContext.request.contextPath}/js/dashboard/dashboard.js"></script>
</body>
</html>