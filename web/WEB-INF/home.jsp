<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.Task" %>
<%@ page import="model.Project" %><%--
  Created by IntelliJ IDEA.
  User: Karen-nout-W
  Date: 1/23/2018
  Time: 8:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>


<style>
    html, body {
        width: 100%;
        height: 100%;
        margin: 0;
        font-family: Helvetica, Arial, sans-serif;
        overflow: hidden;
    }

    .wrapper {
        background-color: #000;
    }

    .framed {
        position: absolute;
        top: 50%;
        left: 50%;
        width: 15rem;
        margin-left: -7.5rem;

    }

    .logo {
        margin-top: -15em;
        cursor: default;
        position: absolute;
        top: 50%;
        left: 50%;
        margin-left: -2.5rem;
        height: 8rem;

    }

    .form {
        margin-top: -4.5em;
        transition: 1s ease-in-out;
    }

    .input {
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        font-size: 1.125rem;
        line-height: 3rem;
        width: 100%;
        height: 3rem;
        color: #000;
        background-color: rgba(255, 255, 255, 0.8);
        border: 0;
        border-top: 1px solid rgba(255, 255, 255, 0.3);
        padding: 0 1rem;
    }

    .input:focus {
        outline: none;
    }

    .input--top {
        border-radius: 10px 10px 0px 0px;
    }

    .input--submit {
        background-color: rgba(92, 168, 214, 0.9);
        color: #fff;
        font-weight: bold;
        cursor: pointer;
        border-top: 0;
        border-radius: 0 0 0.5rem 0.5rem;
        margin-bottom: 1rem;
    }

    .text {
        color: #EEE;
        text-shadow: 2px 2px 2px rgba(51, 51, 51, 0.8);
        text-decoration: none;
    }

    .text--small {
        opacity: 0.9;
        font-size: 1rem;
        cursor: pointer;
    }

    .text--small:hover {
        opacity: 1;
    }

    .text--omega {
        width: 200%;
        margin: 0 0 1rem -50%;
        font-size: 1.5rem;
        line-height: 1.125;
        font-weight: normal;
    }

    .text--centered {
        display: block;
        text-align: center;
    }

    .text--border-right {
        border-right: 1px solid rgba(255, 255, 255, 0.5);
        margin-right: 0.75rem;
        padding-right: 0.75rem;
    }

    .fullscreen-bg {
        position: fixed;
        z-index: -1;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        background: url(http://i22.photobucket.com/albums/b339/surfdude36/DSC_0084.jpg) center;
        background-size: cover;
    }

    .faded {
        position: fixed;
        z-index: 1;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        background: rgba(0, 0, 0, 0.5);
    }

    .modal {
        position: fixed;
        z-index: 1000;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
    }

    #toggle--login:checked ~ .form--signup {
        left: 200%;
        visibility: hidden;
    }

    #toggle--signup:checked ~ .form--login {
        left: -100%;
        visibility: hidden;
    }

    @media (height: 300px) {
        .legal, .photo-cred {
            display: none
        }
    }


</style>
</head>
<%  if (request.getAttribute("errMessage")!=null){%>
<span style="color: red"> <%=request.getAttribute("errMessage")%>  </span>
<%}%>
<body>
<div class="modal">

    <!--handle validating which form version to show -->

    <img class="logo" src="http://i22.photobucket.com/albums/b339/surfdude36/stormfire_icon_small.png"/>

    <form class="form form--login framed" action="/loginServlet" method="post">
        <input type="text" placeholder="Email" name="email" class='input input--top' required/>
        <input type="password" name="password" placeholder="Password" class='input' required/>
        <input type="submit" value="Log In" class="input input--submit"/>


    </form>




</div>
<div class="faded"></div>
<div class="fullscreen-bg"></div>



</body>
</html>
