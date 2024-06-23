<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Avatar Customization</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="/css/avatar/avatar.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;1,100;1,200;1,300;1,400;1,500;1,600;1,700&display=swap"
          rel="stylesheet">
    <!-- Add CSRF token meta tag -->
    <meta name="_csrf" content="${_csrf.token}">
    <meta name="_csrf_header" content="${_csrf.headerName}">
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="activePage" value="avatar"/>
</jsp:include>
<div class="container">
    <canvas id="avatarCanvas" width="200" height="200"></canvas>
    <div class="options">
        <label for="skinColor">Skin Color:</label>
        <select id="skinColor" onchange="changeSkinColor()">
            <option value="#ffddb3" ${skinColor eq '#ffddb3' ? 'selected' : ''}>Light</option>
            <option value="#d2a679" ${skinColor eq '#d2a679' ? 'selected' : ''}>Medium</option>
            <option value="#8b5a2b" ${skinColor eq '#8b5a2b' ? 'selected' : ''}>Dark</option>
        </select>
        <label for="eyeColor">Eye Color:</label>
        <select id="eyeColor" onchange="changeEyeColor()">
            <option value="#4e3c28" ${eyeColor eq '#4e3c28' ? 'selected' : ''}>Brown</option>
            <!-- Darker brown -->
            <option value="#348899" ${eyeColor eq '#348899' ? 'selected' : ''}>Blue</option>
            <option value="#90c3d4" ${eyeColor eq '#90c3d4' ? 'selected' : ''}>Gray</option>
        </select>
        <label for="hairType">Hair Type:</label>
        <select id="hairType" onchange="changeHairType()">
            <option value="curly" ${hairType eq 'curly' ? 'selected' : ''}>Curly Hair</option>
            <option value="short" ${hairType eq 'short' ? 'selected' : ''}>Short Hair</option>
            <option value="long" ${hairType eq 'long' ? 'selected' : ''}>Long Hair</option>
            <option value="bald" ${hairType eq 'bald' ? 'selected' : ''}>Bald</option>
            <!-- Added bald option -->
        </select>
        <label for="hairColor">Hair Color:</label>
        <select id="hairColor" onchange="changeHairColor()">
            <option value="#000000" ${hairColor eq '#000000' ? 'selected' : ''}>Black</option>
            <option value="#D2B48C" ${hairColor eq '#D2B48C' ? 'selected' : ''}>Blonde</option>
            <option value="#8B4513" ${hairColor eq '#8B4513' ? 'selected' : ''}>Brown</option>
            <option value="#3D2B1F" ${hairColor eq '#3D2B1F' ? 'selected' : ''}>Brunette</option>
        </select>
        <label for="noseSize">Nose Size:</label>
        <select id="noseSize" onchange="changeNoseSize()">
            <option value="small" ${noseSize eq 'small' ? 'selected' : ''}>Small Nose</option>
            <option value="medium" ${noseSize eq 'medium' ? 'selected' : ''}>Medium Nose</option>
            <option value="big" ${noseSize eq 'big' ? 'selected' : ''}>Big Nose</option>
        </select>
        <label for="mouthSize">Mouth Size:</label>
        <select id="mouthSize" onchange="changeMouthSize()">
            <option value="small" ${mouthSize eq 'small' ? 'selected' : ''}>Small Mouth</option>
            <option value="medium" ${mouthSize eq 'medium' ? 'selected' : ''}>Medium Mouth</option>
            <option value="big" ${mouthSize eq 'big' ? 'selected' : ''}>Big Mouth</option>
        </select>
        <label for="glassesCheckbox">Wear Glasses:</label>
        <input type="checkbox" id="glassesCheckbox"
               onchange="changeGlasses()" ${glasses ? 'checked' : ''}>
        <button onclick="saveAvatar()">Save Avatar</button>
    </div>
</div>


<script src="/js/avatar/avatar.js"></script>
</body>
</html>
