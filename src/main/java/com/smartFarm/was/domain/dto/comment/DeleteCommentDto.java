package com.smartFarm.was.domain.dto.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
public class DeleteCommentDto {

    private long commentId;
    private long boardId;
    private long memberId;
    private Timestamp commentLastUpdated;
    private boolean isOwnerFlag;
    private char stateDel;

    public DeleteCommentDto() {}
}
