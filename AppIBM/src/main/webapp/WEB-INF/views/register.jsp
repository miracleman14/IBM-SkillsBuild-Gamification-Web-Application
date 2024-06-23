<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome to IBM Skill Build</title>
    <style>
      /* General styles */
      body {
        font-family: 'Arial', sans-serif;
        background-size: cover;
        background-position: center;
        margin: 0;
        padding: 0;
        color: #fff; /* Text color */
        background-color: rgb(3, 13, 63);
      }

      .container {
        width: 430px;
        margin: 100px auto;
        padding: 40px 20px; /* Increased padding */
        border-radius: 10px;
        text-align: center;
      }

      .logo {
        position: absolute;
        height: auto;
        width: 300px;
        top: 50px;
        left: 50px;
      }

      h2 {
        font-size: 32px; /* Increase font size for emphasis */
        color: #ffffff; /* Neon green color */
        margin-bottom: 20px; /* Add margin bottom */
        animation: typing 5s steps(40, end); /* Typing animation */
        overflow: hidden; /* Hide overflow */
        white-space: nowrap; /* Keep text on one line */
        border-right: 2px solid; /* Create typing cursor effect */
      }

      /* Welcome message */
      .welcome-message {
        font-size: 18px;
        color: #fff;
        margin-bottom: 20px;
      }

      /* Form styles */
      form {
        margin-top: 20px;
      }

      input[type="text"],
      input[type="password"] {
        width: calc(100% - 40px);
        padding: 12px; /* Increased padding */
        margin-bottom: 20px; /* Increase margin bottom */
        border: none; /* Remove border */
        border-radius: 20px; /* Round the corners */
        background-color: rgba(0, 63, 166, 0.65);
        color: rgb(255, 255, 255); /* Text color */
        font-size: 16px; /* Font size */
      }

      input[type="submit"] {
        width: 100%;
        padding: 12px; /* Increase padding */
        background-color: rgba(0, 63, 166, 0.65); /* Red color */
        color: #fff; /* Text color */
        border: 2px solid rgba(0, 63, 166, 0.65);
        border-radius: 10px;
        cursor: pointer;
        font-size: 18px; /* Font size */
        text-transform: uppercase; /* Convert text to uppercase */
        transition: background-color 0.3s ease; /* Add transition effect */
      }

      input[type="submit"]:hover {
        background-color: rgba(0, 97, 166, 0.73);
      }

      /* Add typing animation */
      @keyframes typing {
        from {
          width: 0
        }
        to {
          width: 103%
        }
      }

      /* Style the register button */
      .login-button {
        display: inline-block;
        padding: 10px 20px;
        background-color: rgba(0, 63, 166, 0.65);
        color: #fff;
        text-decoration: none;
        border-radius: 10px;
        margin-top: 20px;
        transition: background-color 0.3s ease;
      }

      .login-button:hover {
        background-color: rgba(0, 97, 166, 0.73);
      }

    </style>
</head>
<body>
<div class="container">
    <img src="https://www.ibm.com/brand/experience-guides/developer/8f4e3cc2b5d52354a6d43c8edba1e3c9/02_8-bar-reverse.svg"
         class="logo">

    <h2 class="gamification-element">Welcome to IBM Skills Build</h2>
    <p class="welcome-message">Register</p>
    <form action="/register" method="POST" modelAttribute="user">
        User Name: <input type="text" name="userName"/> <br/>
        Password: <input type="password" name="password"/> <br/>
        <span style="color: #ffffff;">${error.user}</span>
        <input type="submit" value="Sign Up" class="gamification-element"/> <br/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <a href="/login-form" class="login-button">Already Have An Account?</a>

    </form>
</div>
</body>
</html>

