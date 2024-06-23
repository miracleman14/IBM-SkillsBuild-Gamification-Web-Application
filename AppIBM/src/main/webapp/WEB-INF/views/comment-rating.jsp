<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Comment and Rating</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/comment-rating/Comment-Rating.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;1,100;1,200;1,300;1,400;1,500;1,600;1,700&display=swap"
          rel="stylesheet">
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="activePage" value="comment-rating"/>
</jsp:include>
<%--this is to display the comment and rating on the navigation bar in the webpage--%>
<h2>Comment</h2>
<div class="comments">
    <ul id="commentList">
        <li>I loved studying "Artificial Intelligence - AI Foundations: A Collaboration of ISTE and
            IBM" course!
        </li>
    </ul>
</div>

<h2 class="comment-container">
    <img src="${pageContext.request.contextPath}/img/textbox.png" alt="Textbox Image"
         class="textbox-image">
    <%--putting image inside the comment-contatiner--%>
    <label for="commentArea"></label><textarea id="commentArea" class="comment-area"
                                               placeholder="Add a comment about your completed course here!"></textarea>
</h2>
<br>
<button id="addCommentButton" class="addcomment">Add Comment</button>
<%--this is the button for adding comments--%>

<script>
  function addCommentList() {
    const commentText = document.getElementById('commentArea').value.trim();

    if (commentText !== '') {
      const newComment = document.createElement('li');
      newComment.textContent = commentText;

      const existingComments = document.getElementById('commentList');
      existingComments.appendChild(newComment);
      <%--the user should enter a comment before clicking on the button--%>
    } else {
      alert('Please enter a comment.');
    }
  }

  document.getElementById('addCommentButton').addEventListener('click', addCommentList);
</script>
<br>

<h2 class="rating-star">
    Rating <img src="${pageContext.request.contextPath}/img/star.png" alt="Rating Image"
                class="rating-image">
    <%--Displaying star image next to the title Rating--%>
</h2>

<ul id="ratingList" class="rating">
    <li>AI Fundamentals - 5 star/stars</li>
    <li>Cloud Computing Fundamentals - 3 star/stars</li>
    <li>Enterprise Design Thinking Practitioner - 1 star/stars</li>
</ul>

<form id="ratingForm">
    <label for="rating">Add a rating for completed course:</label>
    <%--5 options between 1 to 5 stars--%>
    <select name="value" id="rating" class="option">
        <optgroup label="A Collaboration of ISTE and IBM">
            <option value="A Collaboration of ISTE and IBM - 1">1 star</option>
            <option value="A Collaboration of ISTE and IBM - 2">2 stars</option>
            <option value="A Collaboration of ISTE and IBM - 3">3 stars</option>
            <option value="A Collaboration of ISTE and IBM - 4">4 stars</option>
            <option value="A Collaboration of ISTE and IBM - 5">5 stars</option>
        </optgroup>

        <optgroup label="AI Fundamentals">
            <option value="AI Fundamentals - 1">1 star</option>
            <option value="AI Fundamentals - 2">2 stars</option>
            <option value="AI Fundamentals - 3">3 stars</option>
            <option value="AI Fundamentals - 4">4 stars</option>
            <option value="AI Fundamentals - 5">5 stars</option>
        </optgroup>

        <optgroup label="Cloud Computing Fundamentals">
            <option value="Cloud Computing Fundamentals - 1">1 star</option>
            <option value="Cloud Computing Fundamentals - 2">2 stars</option>
            <option value="Cloud Computing Fundamentals - 3">3 stars</option>
            <option value="Cloud Computing Fundamentals - 4">4 stars</option>
            <option value="Cloud Computing Fundamentals - 5">5 stars</option>
        </optgroup>

        <optgroup label="Introduction to Cloud">
            <option value="Introduction to Cloud - 1">1 star</option>
            <option value="Introduction to Cloud - 2">2 stars</option>
            <option value="Introduction to Cloud - 3">3 stars</option>
            <option value="Introduction to Cloud - 4">4 stars</option>
            <option value="Introduction to Cloud - 5">5 stars</option>
        </optgroup>

        <optgroup label="Enterprise Design Thinking Practitioner">
            <option value="Enterprise Design Thinking Practitioner - 1">1 star</option>
            <option value="Enterprise Design Thinking Practitioner - 2">2 stars</option>
            <option value="Enterprise Design Thinking Practitioner - 3">3 stars</option>
            <option value="Enterprise Design Thinking Practitioner - 4">4 stars</option>
            <option value="Enterprise Design Thinking Practitioner - 5">5 stars</option>
        </optgroup>
    </select>
    <br>
    <br>
    <button id="addRatingButton" class="addrating">Add Rating</button>
</form>


<script>
  <%--function for list of rating--%>

  function addRatingList(event) {
    event.preventDefault();
    const rating = document.getElementById('rating').value;

    if (rating !== '') {

      const newRating = document.createElement('li');
      newRating.textContent = rating + " star/stars";
      <%--Displaying pre setted existing rating list--%>
      const existingRatings = document.getElementById('ratingList');
      existingRatings.appendChild(newRating);
    }
  }

  document.getElementById('addRatingButton').addEventListener('click', addRatingList);
</script>
</body>
</html>
