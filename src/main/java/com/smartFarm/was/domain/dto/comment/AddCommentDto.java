package com.smartFarm.was.domain.dto.comment;

import com.smartFarm.was.domain.request.comment.AddCommentForm;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString
public class AddCommentDto {

    private long commentId;
    private long boardId;
    private long memberId;
    private String memberName;
    private String commentContent;
    private String commentStatus;
    private Timestamp commentCreated;
    private Timestamp commentLastUpdated;

    private AddCommentDto() {}

    public static AddCommentDto of(AddCommentForm addCommentForm, long boardId, long memberId) {
        AddCommentDto addCommentDto = new AddCommentDto();
        addCommentDto.boardId = boardId;
        addCommentDto.memberId = memberId;
        addCommentDto.commentContent = addCommentForm.getCommentContent();
        addCommentDto.commentStatus = addCommentForm.getCommentStatus();
        addCommentDto.commentCreated = new Timestamp(System.currentTimeMillis());
        addCommentDto.commentLastUpdated = null;
        return addCommentDto;
    }
}
