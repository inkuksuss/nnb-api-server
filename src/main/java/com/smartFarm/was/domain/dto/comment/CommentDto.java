package com.smartFarm.was.domain.dto.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class CommentDto {

    private final long commentId;
    private final long memberId;
    private final String memberName;
    private final String commentContent;
    private final String commentStatus;
    private final Timestamp commentCreated;
}
