<%--
To use this file, include it in your JSP file using the following code, replacing "jspName" with
the name of the JSP file that you are including it in:
<jsp:include page="nav.jsp">
    <jsp:param name="activePage" value="jspName"/>
</jsp:include>
--%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.example.ibmskillsbuildapp.model.Avatar" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%
    String avatarDataURL = (String) session.getAttribute("avatarDataURL");
    if (avatarDataURL == null || avatarDataURL.isEmpty()) {
        avatarDataURL =
                session.getServletContext().getContextPath() + "/img/Null_Profile_Image.png";
    }
%>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/nav.css">
<nav>
    <div>
        <a href="${pageContext.request.contextPath}/">
            <div id="logo"><img
                    src="https://b585204.smushcdn.com/585204/wp-content/uploads/2020/09/ibm-logo-2-1-300x131.png?lossy=0&strip=1&webp=0"
                    alt="Home"/></div>
        </a>
        <!-- Account dropdown -->
        <div class="dropdown" id="accountDropdown">
            <a href="${pageContext.request.contextPath}/profile">
                <div class="avatar nav-avatar">
                    <img src="<%= avatarDataURL %>" alt="Avatar" width="50" height="50">
                </div>
            </a>
            <div class="dropdown-content">
                <a href="${pageContext.request.contextPath}/viewFriends">Friends</a>
                <a href="${pageContext.request.contextPath}/viewAccountDetails">Your Details</a>
            </div>
        </div>
        <!-- Admin dropdown -->
        <sec:authorize access="hasRole('ADMIN')">
            <div class="dropdown" id="adminDropdown">
                <a href="javascript:void(0)">Admin</a>
                <div class="dropdown-content">
                    <a href="${pageContext.request.contextPath}/viewAnalytics">Analytics
                        Dashboard</a>
                </div>
            </div>
        </sec:authorize>
    </div>
    <div id="navbar">
        <ul>
            <li><a href="${pageContext.request.contextPath}/viewDashboard"
                   class="${param.activePage == 'dashboard' ? 'active' : ''}">Dashboard</a>
            </li>
            <li><a href="${pageContext.request.contextPath}/viewLeaderboard"
                   class="${param.activePage == 'leaderboard' ? 'active' : ''}">Leaderboard</a>
            </li>

            <li><a href="${pageContext.request.contextPath}/viewProgress"
                   class="${param.activePage == 'ProgressBar' ? 'active' : ''}">Course Progress</a>
            </li>

            <li><a href="${pageContext.request.contextPath}/comment-rating"
                   class="${param.activePage == 'comment-rating' ? 'active' : ''}">Comment and
                Rating</a>
            </li>

            <%-- Currently redundant, but checks if the user is logged in. If they aren't,
            it will display Login and Sign up buttons, otherwise it will display a Logout button.
            If in the future Spring Security is configured to allow navigation to a page with nav
            before login, this will handle that. --%>
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal != null}">
                    <li style="float:right"><a href="${pageContext.request.contextPath}/logout">Logout</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li style="float:right"><a href="${pageContext.request.contextPath}/register">Sign
                        up</a></li>
                    <li style="float:right"><a
                            href="${pageContext.request.contextPath}/login">Login</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
