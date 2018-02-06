package util;

public interface Commands {
    int EXIT=0;
    int LOGIN=1;

    int LOGOUT=0;
    //ADMIN COMMANDS
    int ADD_PROJECT=1;
    int ADD_USER=2;
    int ADD_TASK=3;
    int PRINT_ALL_TASKS=4;
    int PRINT_ALL_USERS=5;
    int PRINT_TASKS_BY_STATUS=6;
    int PRINT_PROJECTS=7;
    int ADD_COMMENT_BY_TASK_NAME=8;
    //USER COMMANDS
    int PRINT_MY_NEW_TASKS=1;
    int PRINT_MY_CURRENT_TASKS=2;
    int PRINT_MY_FINISHED_TASKS=3;
    int CHANGE_MY_TASK_STATUS_BY_NAME=4;
    int CHANGE_MY_TASK_ESTIMATIONS_BY_NAME=5;
    int ADD_COMMENT_BY_TASKS_NAME=6;
    }
