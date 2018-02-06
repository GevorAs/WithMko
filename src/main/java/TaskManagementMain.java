//import taskMAnagementWithDB.manager.CommentManager;
//import taskMAnagementWithDB.manager.ProjectManager;
//import taskMAnagementWithDB.manager.TaskManager;
//import taskMAnagementWithDB.manager.UserManager;
//import taskMAnagementWithDB.model.Comment;
//import taskMAnagementWithDB.model.Project;
//import taskMAnagementWithDB.model.Task;
//import taskMAnagementWithDB.model.User;
//import taskMAnagementWithDB.util.Commands;
//import taskMAnagementWithDB.util.CreateDBTables;
//
//import java.sql.SQLException;
//import java.util.Random;
//import java.util.Scanner;
//
//public class TaskManagementMain implements Commands {
//
//    private static final Scanner SCANNER = new Scanner(System.in);
//    private static long currentUserid = 0;
//    private static Random randompassword = new Random();
//    private static final TaskManager TASK_MANAGER = new TaskManager();
//    private static final UserManager USER_MANAGER = new UserManager();
//    private static final ProjectManager PROJECT_MANAGER = new ProjectManager();
//    private static final CommentManager COMMENT_MANAGER = new CommentManager();
//
//
//    public static void main(String[] args) throws SQLException {
//        CreateDBTables.getInstance();
//
//        boolean start = true;
//        while (start) {
//
//            printLoginCommand();
//            int command;
//            try {
//                command = Integer.parseInt(SCANNER.nextLine());
//            } catch (NumberFormatException e) {
//                command = -1;
//            }
//            switch (command) {
//                case EXIT:
//                    start = false;
//                    break;
//                case LOGIN:
//                    login();
//                    break;
//                case -1:
//                    System.out.println("input a number");
//                    break;
//                default:
//                    System.out.println("input valid command");
//            }
//        }
//
//    }
//
//    private static void login() throws SQLException {
//        System.out.println("input username,password");
//        String userLogin = SCANNER.nextLine();
//        String[] userLoginAndPassword = userLogin.split(",");
//        if (!USER_MANAGER.userIsExistByEmail(userLoginAndPassword[0])) {
//            System.out.println("not found any user");
//            login();
//        } else {
//            if (USER_MANAGER.userIsExistByPassword(userLoginAndPassword[1])) {
//                if (USER_MANAGER.getUserByEmail(userLoginAndPassword[0]).getType().equals("manager")) {
//                    currentUserid = USER_MANAGER.getUserByEmail(userLoginAndPassword[0]).getId();
//                    System.out.println("welcome " + USER_MANAGER.getUserById(currentUserid).getName()
//                            + " " + USER_MANAGER.getUserById(currentUserid).getSurname());
//                    loginAdmin();
//                } else {
//                    currentUserid = USER_MANAGER.getUserByEmail(userLoginAndPassword[0]).getId();
//                    System.out.println("welcome " + USER_MANAGER.getUserById(currentUserid).getName() + " "
//                            + USER_MANAGER.getUserById(currentUserid).getSurname());
//                    loginUser();
//                }
//            } else {
//                System.out.println("invalid data");
//                login();
//            }
//        }
//    }
//
//    private static void loginAdmin() throws SQLException {
//        boolean isRun = true;
//        while (isRun) {
//            printAdminCommand();
//            int command;
//            try {
//                command = Integer.parseInt(SCANNER.nextLine());
//            } catch (NumberFormatException e) {
//                command = -1;
//            }
//            switch (command) {
//                case -1:
//                    System.out.println("input a number");
//                    break;
//                case LOGOUT:
//                    isRun = false;
//                    currentUserid = 0;
//                    break;
//                case ADD_PROJECT:
//                    addProject();
//                    break;
//                case ADD_USER:
//                    addUser();
//                    break;
//                case ADD_TASK:
//                    addTask();
//                    break;
//                case PRINT_ALL_TASKS:
//                    TASK_MANAGER.printAllTasks();
//                    break;
//                case PRINT_ALL_USERS:
//                    USER_MANAGER.print();
//                    break;
//                case PRINT_TASKS_BY_STATUS:
//                    printTasksByStatus();
//                    break;
//
//                case PRINT_PROJECTS:
//                    PROJECT_MANAGER.print();
//                    break;
//                case ADD_COMMENT_BY_TASK_NAME:
//                    addComentByAdmin();
//                    break;
//
//                default:
//                    System.out.println("input valid command");
//            }
//
//        }
//
//    }
//
//    private static void addProject() {
//        System.out.println("input project name,price,start date,end date");
//        System.out.println("Date format is:2000/12/30");
//        try {
//            String prStr = SCANNER.nextLine();
//            String[] projectDate = prStr.split(",");
//            Double price = Double.parseDouble(projectDate[1]);
//            Project project1 = new Project(projectDate[0], price,
//                    projectDate[2], projectDate[3]);
//            PROJECT_MANAGER.add(project1);
//            System.out.println("new projec is added");
//        } catch (Exception e) {
//            System.out.println("input valid data");
//        }
//
//    }
//
//    private static void addUser() {
//        try {
//            System.out.println("please input 'name','surname','email'");
//            String userString = SCANNER.nextLine();
//            String[] userData = userString.split(",");
//            int passKey = (randompassword.nextInt(9000) + 1000);
//            String password = userData[0] + String.valueOf(passKey);
//            User user = new User(userData[0], userData[1], userData[2], password, "user");
//            USER_MANAGER.addUser(user);
//            System.out.println("user is added: password: " + password);
//
//        } catch (Exception e) {
//            System.out.println("try again");
//            addUser();
//        }
//    }
//
//    private static void addTask() throws SQLException {
//        PROJECT_MANAGER.print();
//        if (!PROJECT_MANAGER.isEmpty()) {
//            System.out.println("first create a project");
//            addProject();
//        } else {
//            System.out.println("input the project name");
//            String prName = SCANNER.nextLine();
//            System.out.println("please input 'title','description',estimate',");
//            String taskString = SCANNER.nextLine();
//            String[] taskData = taskString.split(",");
//            try {
//                Long est = Long.parseLong(taskData[2]);
//                USER_MANAGER.print();
//                System.out.println();
//                System.out.println("please input user email");
//                String userEmail = SCANNER.nextLine();
//                User user = USER_MANAGER.getUserByEmail(userEmail);
//                if (USER_MANAGER.userIsExistByEmail(userEmail)) {
//                    Task task = new Task(taskData[0], taskData[1], est,
//                            user.getId(), "new", PROJECT_MANAGER.getProjectByName(prName).getId());
//                    TASK_MANAGER.addTask(task);
//                    System.out.println("new task is added");
//                    System.out.println(task);
//                }
//            } catch (Exception e) {
//                System.out.println("input a valid data" + e);
//                addTask();
//            }
//        }
//    }
//
//    private static void printTasksByStatus() throws SQLException {
//
//
//        System.out.println("please input a status: new,inprogress,finished");
//        String taskStatus = SCANNER.nextLine();
//        TASK_MANAGER.printTasksByStatus(taskStatus);
//
//    }
//
//    private static void addComentByAdmin() throws SQLException {
//        if (TASK_MANAGER.taskIsExists()) {
//            TASK_MANAGER.printAllTasks();
//            System.out.println("input task name for add a coment");
//            String taskNameStr = SCANNER.nextLine();
//            Task task = TASK_MANAGER.getTaskByTaskName(taskNameStr);
//            System.out.println("input a comment");
//            String commentStr = SCANNER.nextLine();
//            Comment comment = new Comment(commentStr, currentUserid, task.getId());
//            COMMENT_MANAGER.add(comment);
//            COMMENT_MANAGER.printCommentsByTask(task);
//        } else System.out.println("no any task");
//
//    }
//
//    private static void loginUser() throws SQLException {
//        boolean isRun = true;
//        while (isRun) {
//            printUserCommand();
//            int command;
//            try {
//                command = Integer.parseInt(SCANNER.nextLine());
//            } catch (NumberFormatException e) {
//                command = -1;
//            }
//            switch (command) {
//                case -1:
//                    System.out.println("input a number");
//                    break;
//                case LOGOUT:
//                    currentUserid = 0;
//                    isRun = false;
//                    break;
//                case PRINT_MY_NEW_TASKS:
//                    TASK_MANAGER.printNewTasksByUserEmail(currentUserid);
//                    break;
//                case PRINT_MY_CURRENT_TASKS:
//                    TASK_MANAGER.printCurrentTasksByUserEmail(currentUserid);
//                    break;
//                case PRINT_MY_FINISHED_TASKS:
//                    TASK_MANAGER.PrintFinishedTasksByUserEmail(currentUserid);
//
//                    break;
//                case CHANGE_MY_TASK_STATUS_BY_NAME:
//                    changeMyTaskStatusByName();
//                    break;
//                case CHANGE_MY_TASK_ESTIMATIONS_BY_NAME:
//                    changeMyTaskEstimationByName();
//                    break;
//                case ADD_COMMENT_BY_TASKS_NAME:
//                    addComentByUser();
//                    break;
//                default:
//                    System.out.println("input valid command");
//            }
//        }
//    }
//
//    private static void changeMyTaskStatusByName() throws SQLException {
//        TASK_MANAGER.printAllTasks();
//        System.out.println("input task name for change status");
//        String taskName = SCANNER.nextLine();
//        Task task = TASK_MANAGER.getTaskByNameAndUserEmail(taskName, currentUserid);
//        System.out.println("input new status: inprogress,finished");
//        String taskStatus = SCANNER.nextLine();
//
//        switch (taskStatus.toLowerCase()) {
//            case "inprogress":
//                if (task.getStatus().equalsIgnoreCase("new")) {
//                    System.out.println("old status: " + task.getStatus() + "\nnew status: "
//                            + taskStatus);
//                    task.setStatus("inprogress");
//                    TASK_MANAGER.updateTask(task);
//                } else {
//                    if (task.getStatus().equalsIgnoreCase("inprogress")) {
//                        System.out.println("old status: " + task.getStatus() + "\nnew status: "
//                                + taskStatus);
//                        task.setStatus("inprogress");
//                        TASK_MANAGER.updateTask(task);
//                    } else {
//                        System.out.println("the task is finished and can't change status");
//                    }
//                }
//                break;
//            case "finished":
//                System.out.println("old status: " + task.getStatus() + "\nnew status: "
//                        + taskStatus);
//                task.setStatus("finished");
//                TASK_MANAGER.updateTask(task);
//                break;
//            default:
//                System.out.println("input valid status");
//
//        }
//    }
//
//    private static void changeMyTaskEstimationByName() throws SQLException {
//        TASK_MANAGER.printAllTasks();
//        System.out.println("input task name for change estimation");
//        String taskName = SCANNER.nextLine();
//        System.out.println("input new estimation");
//        try {
//            String taskData = SCANNER.nextLine();
//            Long est = Long.parseLong(taskData);
//            Task task = TASK_MANAGER.getTaskByNameAndUserEmail(taskName, currentUserid);
//            task.setEstimate(est);
//            TASK_MANAGER.updateTask(task);
//            System.out.println("your task new estimation is: " + est);
//        } catch (Exception e) {
//            System.out.println("input a number");
//            changeMyTaskEstimationByName();
//        }
//
//    }
//
//    private static void addComentByUser() throws SQLException {
//        TASK_MANAGER.printTasksByUserEmail(currentUserid);
//        System.out.println("input task name for add a coment");
//        String taskNameStr = SCANNER.nextLine();
//        Task task = TASK_MANAGER.getTaskByNameAndUserEmail(taskNameStr, currentUserid);
//        System.out.println("input a comment");
//        String commentStr = SCANNER.nextLine();
//        Comment comment = new Comment(commentStr, task.getId(), currentUserid);
//        COMMENT_MANAGER.add(comment);
//
//        COMMENT_MANAGER.printCommentsByTask(task);
//
//    }
//
//    private static void printAdminCommand() {
//        System.out.println("input \'" + LOGOUT + "\' for logout");
//        System.out.println("input \'" + ADD_PROJECT + "\' for add project");
//        System.out.println("input \'" + ADD_USER + "\' for add user");
//        System.out.println("input \'" + ADD_TASK + "\' for add task");
//        System.out.println("input \'" + PRINT_ALL_TASKS + "\' for print all tasks");
//        System.out.println("input \'" + PRINT_ALL_USERS + "\' for print all users");
//        System.out.println("input \'" + PRINT_TASKS_BY_STATUS + "\' for print tasks by status");
//        System.out.println("input \'" + PRINT_PROJECTS + "\' for print projects");
//        System.out.println("input \'" + ADD_COMMENT_BY_TASK_NAME + "\' for add comment");
//    }
//
//    private static void printLoginCommand() {
//        System.out.println("input " + "\'" + EXIT + "\'" + " for close project");
//        System.out.println("input " + "\'" + LOGIN + "\'" + " for login");
//    }
//
//    private static void printUserCommand() {
//        System.out.println("input " + LOGOUT + " for logout");
//        System.out.println("input " + PRINT_MY_NEW_TASKS + " for print new tasks");
//        System.out.println("input " + PRINT_MY_CURRENT_TASKS + " for print current tasks");
//        System.out.println("input " + PRINT_MY_FINISHED_TASKS + " for print finished tasks");
//        System.out.println("input " + CHANGE_MY_TASK_STATUS_BY_NAME + " for change task status");
//        System.out.println("input " + CHANGE_MY_TASK_ESTIMATIONS_BY_NAME + " for change task estimation");
//        System.out.println("input " + ADD_COMMENT_BY_TASKS_NAME + " for add coment");
//    }
//}
