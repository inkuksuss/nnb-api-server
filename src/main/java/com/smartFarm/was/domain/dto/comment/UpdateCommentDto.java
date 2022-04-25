package com.smartFarm.was.domain.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Getter @Setter
public class UpdateCommentDto {

    private long commentId;
    private long boardId;
    private long memberId;
    private String commentContent;
    private String commentStatus;
    private Timestamp commentCreated;
    private Timestamp commentLastUpdated;

    private boolean isOwnerFlag;
}
