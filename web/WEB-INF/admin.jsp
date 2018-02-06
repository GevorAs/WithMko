<%@ page import="java.util.List" %>
<%@ page import="model.Task" %>
<%@ page import="model.Project" %>
<%@ page import="model.User" %>
<%@ page import="manager.UserManager" %>
<%@ page import="manager.ProjectManager" %><%--
  Created by IntelliJ IDEA.
  User: Karen-nout-W
  Date: 1/23/2018
  Time: 8:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdminPage</title>
    <style>
        @import url(https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300|Oxygen);

        /* Page layout */

        body {
            background-color: #fff;
            margin: 0;
            padding: 0;
            font-family: 'Oxygen', sans-serif;
            color: #444;
            font-size: 15px;
            line-height: 18px;
            font-weight: 300;
        }

        header {
            margin: 0;
            background: #000;
            height: 65px;
            padding: 1px;
        }

        article {
            margin: 40px 30px;
        }

        h1 {
            font-family: 'Open Sans Condensed', sans-serif;
            letter-spacing: 1px;
            font-size: 2.3em;
            line-height: 44px;
            text-transform: uppercase;
            color: #fff;
            font-weight: 900;
            margin: 0;
            padding: 10px 0 0 30px;
            letter-spacing: 2px;
        }

        h2 {
            margin: 20px 0 10px 0;
            font-weight: 900;
        }

        p {
            margin: 20px 0 5px 0;
        }

        /* Table Layout */

        table.vitamins {
            margin: 20px 0 0 0;
            border-collapse: collapse;
            border-spacing: 0;
            background: #212121;
            color: #fff;
        }

        table.vitamins th, table.vitamins td {
            text-align: center;
        }

        table.vitamins thead {
            line-height: 12px;
            background: #2e63e7;
            text-transform: uppercase;
        }

        table.vitamins thead th {
            color: #fff;
            padding: 10px;
            letter-spacing: 1px;
            vertical-align: bottom;
        }

        table.vitamins thead th:nth-child(1) {
            width: 20%;
            text-align: left;
            padding-left: 20px;
        }

        table.vitamins thead th:nth-child(2) {
            width: 30%;
        }

        table.vitamins thead th:nth-child(3) {
            width: 35%;
        }

        table.vitamins thead th:nth-child(4) {
            width: 15%;
        }

        table.vitamins tbody {
            font-size: 1em;
            line-height: 15px;
        }

        table.vitamins tbody tr {
            border-top: 2px solid rgba(109, 176, 231, 0.8);
            transition: background 0.6s, color 0.6s;
        }

        table.vitamins tbody tr:nth-child(even) {
            background: rgba(255, 255, 255, 0.2);
        }

        table.vitamins tbody tr:hover {
            color: #000;
            background: rgba(255, 255, 255, 0.7);
            font-weight: 900;
        }

        table.vitamins tbody td {
            padding: 12px;
        }

        table.vitamins tbody tr:hover td:first-child {
            background: rgba(0, 0, 0, 0);
        }

        table.vitamins tbody td:first-child {
            text-align: left;
            padding-left: 20px;
            font-weight: 700;
            background: rgba(109, 176, 231, 0.35);
            transition: backgrounf 0.6s;
        }

        table.vitamins tfoot {
            font-size: 0.8em;
        }

        table.vitamins tfoot tr {
            border-top: 2px solid #2e63e7;
        }

        table.vitamins tfoot td {
            color: rgba(255, 255, 215, 0.6);
            text-align: left;
            line-height: 15px;
            padding: 15px 20px;
        }

        /* Mobile Layout */

        @media screen and (max-width: 400px) {
            h1 {
                font-size: 34px;
                line-height: 36px;
                padding-left: 15px;
            }

            article {
                margin: 10px 15px;
            }

            table.vitamins {
                font-size: 0.8em;
            }
        }
    </style>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        h1 {
            font-size: 2em;
            font-family: "Core Sans N W01 35 Light";
            font-weight: normal;
            margin: .67em 0;
            display: block;
        }

        #registered {
            margin-top: 50px;
        }

        #registered img {
            margin-bottom: 0px;
            width: 100px;
            height: 100px;
        }

        #registered span {
            clear: both;
            display: block;
        }

        img {
            margin-bottom: 20px;
        }

        .avatar {
            margin: 10px 0 20px 0;
        }

        .module {
            position: relative;
            top: 10%;
            height: 65%;
            width: 450px;
            margin-left: auto;
            margin-right: auto;
        }

        .user {
            color: #66d8fc;
            font-weight: bold;
        }

        .userlist {
            float: left;
            padding: 30px;
        }

        .userlist span {
            color: #0590fc;
        }

        .welcome {
            position: relative;
            top: 30%;
            height: 65%;
            width: 900px;
            margin-left: auto;
            margin-right: auto;
            margin-top: 50px;
        }

        ::-moz-selection {
            background: #19547c;
        }

        ::selection {
            background: #19547c;
        }

        input::-moz-selection {
            background: #037db6;
        }

        input::selection {
            background: #037db6;
        }

        body {
            color: #fff;
            background-color: #f0f0f0;
            font-family: helvetica;
            background: url('http://clevertechie.com/img/bnet-bg.jpg') #0f2439 no-repeat center top;
        }

        .body-content {
            position: relative;
            top: 20px;
            height: 700px;
            width: 800px;
            margin-left: auto;
            margin-right: auto;
            background: transparent;
        }

        select,
        textarea,
        input[type="text"],
        input[type="password"],
        input[type="email"] {
            height: 30px;
            width: 100%;;
            display: inline-block;
            vertical-align: middle;
            height: 34px;
            padding: 0 10px;
            margin-top: 3px;
            margin-bottom: 10px;
            font-size: 15px;
            line-height: 20px;
            border: 1px solid rgba(255, 255, 255, 0.3);
            background-color: rgba(0, 0, 0, 0.5);
            color: rgba(255, 255, 255, 0.7);
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            border-radius: 2px;
        }

        select,
        textarea,
        input[type="text"],
        input[type="password"],
        input[type="email"] {
            -webkit-appearance: none;
            -moz-appearance: none;
            -ms-appearance: none;
            appearance: none;
            -webkit-transition: background-position 0.2s, background-color 0.2s, border-color 0.2s, box-shadow 0.2s;
            transition: background-position 0.2s, background-color 0.2s, border-color 0.2s, box-shadow 0.2s;
        }

        select:hover,
        textarea:hover,
        input[type="text"]:hover,
        input[type="password"]:hover,
        input[type="email"]:hover {
            border-color: rgba(255, 255, 255, 0.5);
            background-color: rgba(0, 0, 0, 0.5);
            color: rgba(255, 255, 255, 0.7);
        }

        select:focus,
        textarea:focus,
        input[type="text"]:focus,
        input[type="password"]:focus,
        input[type="email"]:focus {
            border: 2px solid;
            border-color: #1e5f99;
            background-color: rgba(0, 0, 0, 0.5);
            color: #ffffff;
        }

        .btn {
            text-overflow: ellipsis;
            white-space: nowrap;
            overflow: hidden;
            margin: 3px 0;
            padding: 6px 20px;
            font-size: 15px;
            line-height: 20px;
            height: 34px;
            background-color: rgba(0, 0, 0, 0.15);
            color: #00aeff;
            border: 1px solid rgba(255, 255, 255, 0.15);
            box-shadow: 0 0 rgba(0, 0, 0, 0);
            border-radius: 2px;
            -webkit-transition: background-color 0.2s, box-shadow 0.2s, background-color 0.2s, border-color 0.2s, color 0.2s;
            transition: background-color 0.2s, box-shadow 0.2s, background-color 0.2s, border-color 0.2s, color 0.2s;
        }

        .btn.active,
        .btn:active {
            padding: 7px 19px 5px 21px;
        }

        .btn.disabled:active,
        .btn[disabled]:active,
        .btn.disabled.active,
        .btn[disabled].active {
            padding: 6px 20px !important;
        }

        .btn:hover,
        .btn:focus {
            background-color: rgba(0, 0, 0, 0.25);
            color: #ffffff;
            border-color: rgba(255, 255, 255, 0.3);
            box-shadow: 0 0 rgba(0, 0, 0, 0);
        }

        .btn:active,
        .btn.active {
            background-color: rgba(0, 0, 0, 0.15);
            color: rgba(255, 255, 255, 0.8);
            border-color: rgba(255, 255, 255, 0.07);
            box-shadow: inset 1.5px 1.5px 3px rgba(0, 0, 0, 0.5);
        }

        .btn-primary {
            background-color: #098cc8;
            color: #ffffff;
            border: 1px solid transparent;
            box-shadow: 0 0 rgba(0, 0, 0, 0);
            border-radius: 2px;
            -webkit-transition: background-color 0.2s, box-shadow 0.2s, background-color 0.2s, border-color 0.2s, color 0.2s;
            transition: background-color 0.2s, box-shadow 0.2s, background-color 0.2s, border-color 0.2s, color 0.2s;
            background-image: -webkit-linear-gradient(top, #0f9ada, #0076ad);
            background-image: linear-gradient(to bottom, #0f9ada, #0076ad);
            border: 0;
            box-shadow: 0 1px 1px rgba(0, 0, 0, 0.3), 0 0 0 1px rgba(255, 255, 255, 0.15) inset;
        }

        .btn-primary:hover,
        .btn-primary:focus {
            background-color: #21b0f1;
            color: #ffffff;
            border-color: transparent;
            box-shadow: 0 0 rgba(0, 0, 0, 0);
        }

        .btn-primary:active,
        .btn-primary.active {
            background-color: #006899;
            color: rgba(255, 255, 255, 0.7);
            border-color: transparent;
            box-shadow: inset 1.5px 1.5px 3px rgba(0, 0, 0, 0.5);
        }

        .btn-primary:hover,
        .btn-primary:focus {
            background-image: -webkit-linear-gradient(top, #37c0ff, #0097dd);
            background-image: linear-gradient(to bottom, #37c0ff, #0097dd);
        }

        .btn-primary:active,
        .btn-primary.active {
            background-image: -webkit-linear-gradient(top, #006ea1, #00608d);
            background-image: linear-gradient(to bottom, #006ea1, #00608d);
            box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.6) inset, 0 0 0 1px rgba(255, 255, 255, 0.07) inset;
        }

        .btn-block {
            display: block;
            width: 100%;
            padding-left: 0;
            padding-right: 0;
        }

        .alert {
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            padding: 4px 20px 4px 20px;
            font-size: 13px;
            line-height: 20px;
            margin-bottom: 20px;
            text-shadow: none;
            position: relative;
            background-color: #272e3b;
            color: rgba(255, 255, 255, 0.7);
            border: 1px solid #000;
            box-shadow: 0 0 0 1px #363d49 inset, 0 5px 10px rgba(0, 0, 0, 0.75);
        }

        .alert-error {
            color: #f00;
            background-color: #360e10;
            box-shadow: 0 0 0 1px #551e21 inset, 0 5px 10px rgba(0, 0, 0, 0.75);
        }

        .alert:empty {
            display: none;
        }

        .alert-success {
            color: #21ec0c;
            background-color: #15360e;
            box-shadow: 0 0 0 1px #2a551e inset, 0 5px 10px rgba(0, 0, 0, 0.75);
        }</style>
    <style>
        body {
            padding: 50px;
        }

        .animate {
            transition: all 0.1s;
            -webkit-transition: all 0.1s;
        }

        .action-button {
            position: relative;
            padding: 10px 40px;
            margin: 0px 10px 10px 0px;
            float: left;
            border-radius: 10px;
            font-family: 'Pacifico', cursive;
            font-size: 25px;
            color: #FFF;
            text-decoration: none;
        }

        .blue {
            background-color: #3498DB;
            border-bottom: 5px solid #2980B9;
            text-shadow: 0px -2px #2980B9;
        }

        .red {
            background-color: #E74C3C;
            border-bottom: 5px solid #BD3E31;
            text-shadow: 0px -2px #BD3E31;
        }

        .green {
            background-color: #82BF56;
            border-bottom: 5px solid #669644;
            text-shadow: 0px -2px #669644;
        }

        .yellow {
            background-color: #F2CF66;
            border-bottom: 5px solid #D1B358;
            text-shadow: 0px -2px #D1B358;
        }

        .action-button:active {
            transform: translate(0px, 5px);
            -webkit-transform: translate(0px, 5px);
            border-bottom: 1px solid;
        }
    </style>
</head>
<body>
<%List<Task> tasks = (List) request.getAttribute("tasks"); %>
<%List<Project> projects = (List) request.getAttribute("projects"); %>
<%List<User> users = (List) request.getAttribute("users"); %>
<%List<Task> tasksByStatus = (List) request.getAttribute("tasksByStatus"); %>
<% UserManager userManager = new UserManager();%>
<% ProjectManager projectManager = new ProjectManager();%>

<%--dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd--%>
<div>

    <% if (request.getAttribute("errMessage") != null) {%>
    <div style="border: 2px solid red">
        <span style="color: red"> <%=request.getAttribute("errMessage")%>  </span>
    </div>
    <%}%>


</div>
<a href="/logoutServlet" class="action-button shadow animate blue">Logout</a>


<div style="text-align: center" ;>


    <header>
        <h1>Users</h1>
    </header>
    <article>


        <table class="vitamins">
            <thead>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>surname</th>
                <th>email</th>
                <th>password</th>
                <th>type</th>
                <th>-</th>
            </tr>
            </thead>

            <tbody>

            <% for (User user : users) {%>
            <tr>
                <td><%=user.getId()%>
                </td>
                <td><%=user.getName()%>
                </td>
                <td><%=user.getSurname()%>
                </td>
                <td><%=user.getEmail()%>
                </td>
                <td><%=user.getPassword()%>
                </td>
                <td><%=user.getType()%>
                </td>
                <td>
                    <form action="/deleteUserServlet" method="post">

                        <input type="hidden" name="userId" value="<%=user.getId()%>">

                        <input type="submit" value="DELETE" class="action-button shadow animate blue">
                    </form>
                </td>
            </tr>
            <%}%>

            </tbody>

        </table>

    </article>


    <header>
        <h1>Projects</h1>
    </header>
    <article>


        <table class="vitamins">
            <thead>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>price</th>
                <th>start date</th>
                <th>end date</th>
                <th>-</th>
            </tr>
            </thead>

            <tbody>
            <% for (Project project : projects) {%>
            <tr>
                <td><%=project.getId()%>
                </td>
                <td><%=project.getName()%>
                </td>
                <td><%=project.getPrice()%>
                </td>
                <td><%=project.getStartDate()%>
                </td>
                <td><%=project.getEndDate()%>
                </td>
                <td>
                    <form action="/deleteProjectServlet" method="post">

                        <input type="hidden" name="projectId" value="<%=project.getId()%>">

                        <input type="submit" value="DELETE" class="action-button shadow animate blue">
                    </form>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </article>


    <header>
        <h1>Tasks</h1>
    </header>
    <article>


        <table class="vitamins">
            <thead>
            <tr>
                <th>id</th>
                <th>title</th>
                <th>description</th>
                <th>estimate</th>
                <th>assign to</th>
                <th>create date</th>
                <th>status</th>
                <th>project id</th>
                <th>change task</th>
                <th>-</th>
            </tr>
            </thead>

            <tbody>
            <% for (Task task : tasks) {%>
            <tr>
                <td><%=task.getId()%>
                </td>
                <td><%=task.getTitle()%>
                </td>
                <td><%=task.getDesc()%>
                </td>
                <td><%=task.getEstimate()%>
                </td>
                <td><%=userManager.getUserById(task.getAssignTo()).getEmail()%>
                </td>
                <td><%=task.getCreateDate()%>
                </td>
                <td><%=task.getStatus()%>
                </td>
                <td><%=projectManager.getProjectById(task.getProjectId()).getName()%>
                </td>
                <td>
                    <form action="/changeTaskAssignToServlet" method="post">

                        <input type="hidden" name="taskTitle" value="<%=task.getTitle()%>">
                        <select name="userId">
                            <% for (User user : users) {%>
                            <option value="<%=user.getId()%>"><%=user.getName()%>&nbsp;<%=user.getSurname()%>
                            </option>
                            <% }%>
                        </select>

                        <input type="submit" value="Change" class="action-button shadow animate blue">
                    </form>
                </td>
                <td>
                    <form action="/deleteTaskServlet" method="post">

                        <input type="hidden" name="taskId" value="<%=task.getId()%>">

                        <input type="submit" value="DELETE" class="action-button shadow animate blue">
                    </form>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </article>


</div>
<%--33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333--%>
<br>

<div style=" vertical-align: super">
    <div style="width: 24%; display: inline-block">
        <h1>Add new user</h1>
        <form class="form" action="/addUserServlet" method="post">
            <div class="alert alert-error"></div>
            <input type="text" placeholder="Name" name="name" required/>
            <input type="text" placeholder="Surname" name="surname" required/>
            <input type="email" placeholder="Email" name="email" required/>
            <input type="password" placeholder="Password" name="password" autocomplete="new-password" required/>
            <input type="password" placeholder="Confirm Password" name="repassword" autocomplete="new-password"
                   required/>
            <input type="submit" value="Add" name="register" class="btn btn-block btn-primary"/>
        </form>
    </div>
    <%--33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333--%>


    <div style="width: 24%; display: inline-block">
        <h1>Add new task</h1>
        <form class="form" action="/addTaskServlet" method="post">
            <div class="alert alert-error"></div>
            <input type="text" placeholder="Title" name="name" required/>
            <textarea placeholder="Description" name="desc"></textarea>
            <input type="text" placeholder="Estimation time" name="estimate"/><br>

            User: <select name="userId">
            <% for (User user : users) {%>
            <option value="<%=user.getId()%>"><%=user.getName()%>&nbsp;<%=user.getSurname()%>
            </option>
            <% }%>
        </select>
            </select><br>

            Project: <select name="projectId">
            <% for (Project project : projects) {%>
            <option value="<%=project.getId()%>"><%=project.getName()%>&nbsp;</option>
            <% }%>
        </select>

            <input type="submit" value="Add" name="" class="btn btn-block btn-primary"/>
        </form>
    </div>

    <%--33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333--%>

    <div style="width: 24%; display: inline-block">
        <h1>Add new Project</h1>
        <form class="form" action="/addProjectServlet" method="post">
            <div class="alert alert-error"></div>
            <input type="text" placeholder="Name" name="name" required/>
            <input type="text" placeholder="Price" name="price" required/>
            Start date : <input type="date" placeholder="2000/12/31" name="startDate" required/><br>
            End date : <input type="date" placeholder="2000/12/31" name="endDate" required/>
            <input type="submit" value="Add" name="" class="btn btn-block btn-primary"/>
        </form>
    </div>

    <%--33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333--%>
    <div style="width: 24%; display: inline-block">
        <h1>Add new Comment</h1>
        <form class="form" action="/addCommentServlet" method="post">
            <div class="alert alert-error"></div>
            Text : <textarea name="text"></textarea><br>
            Task: <select name="taskId">
            <% for (Task task : tasks) {%>
            <option value="<%=task.getId()%>"><%=task.getTitle()%>&nbsp;</option>
            <% }%>
        </select>
            <input type="submit" value="Add" name="" class="btn btn-block btn-primary"/>
        </form>
    </div>
</div>
<br>
<%--33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333--%>


<%--33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333--%>


<div style="text-align: center;">

    <header>
        <h1>Show tasks by status</h1>


    </header>
    <article>


        <table class="vitamins">
            <thead>
            <tr>
                <th>id</th>
                <th>title</th>
                <th>description</th>
                <th>estimate</th>
                <th>assign to</th>
                <th>create date</th>
                <th>status</th>
                <th>project id</th>
            </tr>
            </thead>

            <tbody>
            <%
                if (tasksByStatus != null) {
                    for (Task taskByStatus : tasksByStatus) {
            %>
            <tr>

                <td><%=taskByStatus.getId()%>
                </td>
                <td><%=taskByStatus.getTitle()%>
                </td>
                <td><%=taskByStatus.getDesc()%>
                </td>
                <td><%=taskByStatus.getEstimate()%>
                </td>
                <td><%=userManager.getUserById(taskByStatus.getAssignTo()).getEmail()%>
                </td>
                <td><%=taskByStatus.getCreateDate()%>
                </td>
                <td style="font-size: 20px"><%=taskByStatus.getStatus()%>
                </td>
                <td><%=projectManager.getProjectById(taskByStatus.getProjectId()).getName()%>
                </td>
            </tr>
            <%
                    }
                }
            %>

            </tbody>



        </table>

        <div style="text-align: center">
            <form action="/adminPageServlet" method="post">
                <div>
                    <select name="taskStatus">
                        <option value="NEW">New</option>
                        <option value="INPROGRESS">in progress</option>
                        <option value="FINISHED">Finished</option>
                    </select>
                </div>


                    <input type="submit" value="select" class="action-button shadow animate blue">

            </form>
        </div>
    </article>


</div>


</body>
</html>
