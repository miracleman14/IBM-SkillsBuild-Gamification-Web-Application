<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome to IBM Skills Build</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/quiz/quiz.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;1,100;1,200;1,300;1,400;1,500;1,600;1,700&display=swap"
          rel="stylesheet">
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="activePage" value="quiz"/>
</jsp:include>
<div class="container">
    <div class="title">
        <h1>Quiz</h1>
    </div>
    <div class="container m_t_2">
        <div class="quiz">
            <div class="p_1">
                <progress max="8" value="0"></progress>
            </div>
            <div class="progressData"><span class="current">0</span> / <span class="limit">0</span>
            </div>
            <div class="question card">
                <p class="questionText"></p>
                <div class="options"></div> <!-- END .options -->
            </div> <!-- END .question -->
        </div> <!-- END .quiz -->
    </div> <!-- END .container -->
</div>
<script src="${pageContext.request.contextPath}/js/quiz/quiz.js"></script>
<script src="${pageContext.request.contextPath}/js/scrollbar.js"></script>
</body>
</html>