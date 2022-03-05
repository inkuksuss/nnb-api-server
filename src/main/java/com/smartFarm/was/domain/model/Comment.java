package com.smartFarm.was.domain.model;

import com.smartFarm.was.domain.request.comment.AddCommentForm;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString
public class Comment {

    private long commentId;
    private long boardId;
    private long memberId;
    private String commentContent;
    private String commentStatus;
    private Timestamp commentCreated;
    private Timestamp commentLastUpdated;

    private Comment() {}

    public static Comment of(AddCommentForm addCommentForm, long boardId, long memberId) {
        Comment comment = new Comment();
        comment.boardId = boardId;
        comment.memberId = memberId;
        comment.commentContent = addCommentForm.getCommentContent();
        comment.commentStatus = addCommentForm.getCommentStatus();
        comment.commentCreated = new Timestamp(System.currentTimeMillis());
        comment.commentLastUpdated = null;
        return comment;
    }
}
