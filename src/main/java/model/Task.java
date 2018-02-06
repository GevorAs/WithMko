package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private long id;
    private String title;
    private String desc;
    private long estimate;
    private long assignTo;
    private String createDate;
    private String status;
    private long projectId;




    public Task(String title, String desc, long estimate, long assignTo, String status, long projectId) {
        this.title = title;
        this.desc = desc;
        this.estimate = estimate;
        this.assignTo = assignTo;
        this.status = status;
        this.projectId = projectId;
    }

    public Task(String title, String desc, long estimate, long assignTo, String status) {
        this.title = title;
        this.desc = desc;
        this.estimate = estimate;
        this.assignTo = assignTo;
        this.status = status;
    }

}
