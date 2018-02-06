<%@ page import="java.util.List" %>
<%@ page import="model.Task" %>
<%@ page import="model.Comment" %>
<%@ page import="manager.UserManager" %>
<%@ page import="manager.ProjectManager" %><%--
  Created by IntelliJ IDEA.
  User: Arianna
  Date: 25.01.2018
  Time: 2:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserPage</title>
</head>
<body>
<%List<Task> tasksByStatus = (List) request.getAttribute("tasksByStatus");%>
<%List<Task> tasksByUserId = (List) request.getAttribute("tasksByUserId");%>
<%List<Comment> commentsByTAskId = (List) request.getAttribute("commentsByTaskId");%>
<% UserManager userManager = new UserManager();%>
<% ProjectManager projectManager = new ProjectManager();%>
<div>
    USER
    <% if (request.getAttribute("errMessage") != null) {%>
    <div style="border: 2px solid red">
        <span style="color: red"> <%=request.getAttribute("errMessage")%>  </span>
    </div>
    <%}%>
    <a href="/logoutServlet">Logout</a>

</div>
<div style="width: 100%">
    <div style="width: 30%;outline: 1px solid red; margin-bottom: 25px">
        <form action="/userPageServlet" method="post">
            SELECT TASK STATUS <select name="taskStatus">
            <option value="NEW">New</option>
            <option value="INPROGRESS">in progress</option>
            <option value="FINISHED">Finished</option>
        </select>
            <input type="submit" value="select">
        </form>
    </div>
    <div >
        <div style="outline: 3px solid greenyellow">

            <table border="1px" style="width: 100%">
                <tr>
                    <th>id</th>
                    <th>title</th>
                    <th>description</th>
                    <th>estimate</th>
                    <th>assign to</th>
                    <th>create date</th>
                    <th>status</th>
                    <th>project id</th>
                    <th>Select new Status:</th>
                    <th>Input new estimation</th>
                    <th>Input comment</th>
                </tr>
                <%
                    if (tasksByStatus != null) {
                        for (Task taskBystatus : tasksByStatus) {
                %>
                <tr>
                    <td><%=taskBystatus.getId()%>
                    </td>
                    <td><%=taskBystatus.getTitle()%>
                    </td>
                    <td><%=taskBystatus.getDesc()%>
                    </td>
                    <td><%=taskBystatus.getEstimate()%>
                    </td>
                    <td><%=userManager.getUserById(taskBystatus.getAssignTo()).getEmail()%>
                    </td>
                    <td><%=taskBystatus.getCreateDate()%>
                    </td>
                    <td><%=taskBystatus.getStatus()%>
                    </td>
                    <td><%=projectManager.getProjectById(taskBystatus.getProjectId()).getName()%>
                    </td>


                    <td>
                        <% if (taskBystatus.getStatus().equalsIgnoreCase("NEW")) {%>
                        <form action="/changeStatusServlet" method="post">
                            <input type="hidden" name="taskTitle" value="<%=taskBystatus.getTitle()%>">
                            <select name="newStatus">
                                <option value="INPROGRESS">in progress</option>
                                <option value="FINISHED">Finished</option>
                            </select>
                            <input type="submit" value="select">
                        </form>
                        <%}%>
                        <%if (taskBystatus.getStatus().equalsIgnoreCase("INPROGRESS")) {%>
                        <form action="/changeStatusServlet" method="post">
                            <input type="hidden" name="taskTitle" value="<%=taskBystatus.getTitle()%>">
                            <select name="newStatus">
                                <option value="FINISHED">Finished</option>
                            </select>
                            <input type="submit" value="select">
                        </form>


                        <%}%>
                    </td>
                    <td>
                        <form action="/changeEstimationServlet" method="post">
                            <input type="hidden" name="taskTitle" value="<%=taskBystatus.getTitle()%>">
                            <input type="number" value="1" name="newEstimation">
                            <input type="submit" value="select">
                        </form>
                    </td>
                    <td>
                        <form action="/addCommentByUser" method="post">
                            <input type="hidden" name="taskTitle" value="<%=taskBystatus.getTitle()%>">
                            <textarea name="text"></textarea>
                            <input type="submit" value="select">
                        </form>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </table>

        </div>
    </div>
</div>
<div>
    <div><h3>Select the task for see comments</h3>
        <%if (tasksByUserId!=null) {%>
        <form  action="/getCommentsByTaskIdServlet" method="post">
            <select name="getCommentsBytaskId">
                <%for (Task task : tasksByUserId) {%>
                        <option value="<%=task.getId()%>"><%=task.getTitle()%>&nbsp;</option>
                    <%}%>
            </select>
            <input type="submit" value="select">
        </form >
        <%}%>
    </div>
    <div>
        <%if (commentsByTAskId!=null) {%>
      <table border="1px">
          <tr>
              <th>Commented user email</th>
              <th>Text</th>
              <th>Comment date</th>
          </tr>
          <%
              for (Comment comment : commentsByTAskId) {%>
                <tr>
                    <td ><%=userManager.getUserById(comment.getCommentedUserId()).getEmail()%></td>
                    <td height="150px"><%=comment.getText()%></td>
                    <td ><%=comment.getCommentedDate()%></td>
                </tr>
              <%}%>
      </table>
        <%}%>
    </div>
</div>
</body>
</html>
