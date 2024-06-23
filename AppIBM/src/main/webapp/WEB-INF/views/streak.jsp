<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Streak</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/streaks/streaks.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200">

</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="activePage" value="streak"/>
</jsp:include>
<div class="streak-message">
    <h2>Log in again tomorrow to extend your streak!</h2>
</div>


<div class="wrapper">
    <header>
        <p class="user-streak">${userStreak.streakCount} day streak</p>
    </header>
    <div class="calendar">
        <ul class="weeks">
            <li>Sun</li>
            <%-- Weekday abbreviations --%>
            <li>Mon</li>
            <li>Tue</li>
            <li>Wed</li>
            <li>Thu</li>
            <li>Fri</li>
            <li>Sat</li>
        </ul>
        <ul class="days"></ul>
        <%-- Container for calendar days --%>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/streak/streak.js"></script>

</body>
</html>
