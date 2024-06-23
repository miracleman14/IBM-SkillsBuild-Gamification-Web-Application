<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile Page</title>
    <!-- Include CSS files -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/profile/profile.css">
    <!-- Include Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;1,100;1,200;1,300;1,400;1,500;1,600;1,700&display=swap"
          rel="stylesheet">
</head>
<body>

<!-- Include navigation -->
<jsp:include page="nav.jsp">
    <jsp:param name="activePage" value="profile"/>
</jsp:include>

<div class="container">
    <!-- Display profile information -->
    <div class="profile-info">
        <h2>Welcome to Your Profile, ${username}</h2> <!-- Display username here -->
        <!-- Add more profile information here -->
    </div>

    <!-- Display the avatar at the top middle of the page -->
    <div class="avatar">
        <img src="${avatarDataURL}" alt="Avatar" width="200" height="200">
    </div>

    <!-- Add an Edit Avatar button/link with parameters -->
    <div class="edit-avatar">
        <a href="/avatar?avatarDataURL=${avatarDataURL}&skinColor=${currentUserAvatarURL}&eyeColor=${currentUser.getAvatar().getEyeColor()}&hairType=${currentUser.getAvatar().getHairType()}&hairColor=${currentUser.getAvatar().getHairColor()}&noseSize=${currentUser.getAvatar().getNoseSize()}&mouthSize=${currentUser.getAvatar().getMouthSize()}&glasses=${currentUser.getAvatar().isGlasses()}">Edit
            Avatar</a>
    </div>


    <!-- Display streak message and streak count -->
    <div class="streak-message">
        <h2>Log in again tomorrow to extend your streak!</h2>
    </div>

    <div class="wrapper">
        <header>
            <p class="user-streak">${userStreak.streakCount} day streak</p>
        </header>
        <!-- Display the calendar -->
        <div class="calendar">
            <ul class="weeks">
                <li>Sun</li>
                <li>Mon</li>
                <li>Tue</li>
                <li>Wed</li>
                <li>Thu</li>
                <li>Fri</li>
                <li>Sat</li>
            </ul>
            <ul class="days"></ul> <!-- Container for calendar days -->
        </div>
    </div>
    <!-- Include JavaScript file -->
    <script src="${pageContext.request.contextPath}/js/streak/streak.js"></script>
</div>
</body>
</html>
