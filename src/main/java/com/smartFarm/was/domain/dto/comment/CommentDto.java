package com.smartFarm.was.domain.dto.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Getter
@RequiredArgsConstructor
public class CommentDto {

    private Long commentId;
    private Long memberId;
    private String memberName;
    private String commentContent;
    private String commentStatus;
    private Timestamp commentCreated;
    private Timestamp commentLastUpdated;

    public static CommentDto getFiltered(CommentDto comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.commentId = comment.commentId;
        commentDto.commentStatus = comment.commentStatus;
        commentDto.commentCreated = comment.commentCreated;
        commentDto.commentLastUpdated = comment.commentLastUpdated;
        commentDto.memberId = null;
        commentDto.memberName = null;
        commentDto.commentContent = null;
        return commentDto;
    }
}
