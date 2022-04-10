package com.smartFarm.was.domain.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
public class DeleteCommentDto {

    private long commentId;
    private long boardId;
    private long memberId;
    private Timestamp commentLastUpdated;
    private char stateDel;

    public DeleteCommentDto(long commentId, long boardId, long memberId) {
        this.commentId = commentId;
        this.boardId = boardId;
        this.memberId = memberId;
        this.commentLastUpdated = new Timestamp(System.currentTimeMillis());
        this.stateDel = 'Y';
    }
}
