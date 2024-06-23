<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Analytics Dashboard</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.2/dist/chart.umd.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/analytics/analytics.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;1,100;1,200;1,300;1,400;1,500;1,600;1,700&display=swap"
          rel="stylesheet">
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="activePage" value="analytics"/>
</jsp:include>
<div class="container-fluid">
    <div class="form-group">
        <label for="search">Search:</label>
        <input type="text" class="form-control" id="search" list="searchDropdown">
        <datalist id="searchDropdown"></datalist>
        <button class="btn btn-primary" id="searchButton">Search</button>
        <button class="btn btn-secondary" id="clearButton">Clear</button>
    </div>
    <div class="form-group">
        <label for="filter">Filter by:</label>
        <select class="form-control" id="filter">
            <option value="all">All</option>
            <option value="enrollment">Enrollment</option>
            <option value="trending">Trending Courses</option>
            <option value="averageRating">Average Rating</option>
            <option value="highestCompletionRate">Highest Completion Rate</option>
            <option value="lowestCompletionRate">Lowest Completion Rate</option>
        </select>
    </div>
    <div class="container" id="chartContainer"></div>
    <div id="infoContainer"></div>
</div>
<script type="module" src="${pageContext.request.contextPath}/js/analytics/main.js"></script>
<script type="module" src="${pageContext.request.contextPath}/js/analytics/eventListeners.js">
</script>
<script src="${pageContext.request.contextPath}/js/scrollbar.js"></script>
</body>
</html>
