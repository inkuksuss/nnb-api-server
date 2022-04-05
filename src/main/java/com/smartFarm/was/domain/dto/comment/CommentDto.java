package com.smartFarm.was.domain.dto.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Getter @Setter
@RequiredArgsConstructor
public class CommentDto {

    private long commentId;
    private long memberId;
    private String memberName;
    private String commentContent;
    private String commentStatus;
    private Timestamp commentCreated;
    private Timestamp commentLastUpdated;
}
