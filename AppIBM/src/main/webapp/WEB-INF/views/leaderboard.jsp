<%@ page import="org.example.ibmskillsbuildapp.model.Avatar" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String currentUserAvatarURL = (String) request.getAttribute("currentUserAvatarURL");
    currentUserAvatarURL = (currentUserAvatarURL == null || currentUserAvatarURL.isEmpty()
            || currentUserAvatarURL.equals("avatar")) ? request.getContextPath()
            + "/img/Null_Profile_Image.png" : currentUserAvatarURL;
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Leaderboard</title>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/leaderboard/leaderboard.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;1,100;1,200;1,300;1,400;1,500;1,600;1,700&display=swap"
          rel="stylesheet">
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="activePage" value="leaderboard"/>
</jsp:include>
<div class="container">
    <table id="global" class="leaderBoardHead">
        <tr>
            <th colspan="2" class="head">Leaderboard (Global)</th>
        </tr>
        <tr>
            <th>Player</th>
            <th>Score</th>
        </tr>
        <!-- Display other players in the global leaderboard -->
        <c:forEach items="${allPlayers}" var="player">
            <tr>
                <td>
                    <div class="friend">
                        <img src="${player.avatar.avatarDataURL}" alt="Avatar" width="50"
                             height="50">
                            ${player.userName}
                    </div>
                </td>
                <td>${player.score}</td>
            </tr>
        </c:forEach>
        <!-- Display the current user in the global leaderboard -->
        <c:if test="${empty allPlayers}">
            <tr>
                <td>
                    <div class="friend">
                        <img src="${currentUserAvatarURL}" alt="Avatar" width="50" height="50">
                            ${currentUser.userName}
                    </div>
                </td>
                <td>${currentUser.score}</td>
            </tr>
        </c:if>
    </table>
    <table id="friend" class="leaderBoardHead">
        <tr>
            <th colspan="2" class="head">Leaderboard (Friends)</th>
        </tr>
        <tr>
            <th>Player</th>
            <th>Score</th>
        </tr>
        <!-- Display other friends in the friends leaderboard -->
        <c:forEach items="${friends}" var="friend">
            <tr>
                <td>
                    <div class="friend">
                        <img src="${friend.avatar.avatarDataURL}" alt="Avatar" width="50"
                             height="50">
                            ${friend.userName}
                    </div>
                </td>
                <td>${friend.score}</td>
            </tr>
        </c:forEach>
        <!-- Display the current user in the friends leaderboard -->
        <c:if test="${empty friends}">
            <tr>
                <td>
                    <div class="friend">
                        <img src="${currentUserAvatarURL}" alt="Avatar" width="50" height="50">
                            ${currentUser.userName}
                    </div>
                </td>
                <td>${currentUser.score}</td>
            </tr>
        </c:if>
    </table>
</div>
</body>
</html>
