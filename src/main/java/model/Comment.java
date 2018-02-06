package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private long id;
    private String text;
    private long commentedUserId;
    private long taskId;
    private String commentedDate;


    public Comment(String text, long commentedUserId, long taskId, String commentedDate) {
        this.text = text;
        this.commentedUserId = commentedUserId;
        this.taskId = taskId;
        this.commentedDate = commentedDate;
    }

    public Comment(String text, long commentedUserId, long taskId) {
        this.text = text;
        this.commentedUserId = commentedUserId;
        this.taskId = taskId;
    }


}
