package com.smartFarm.was.domain.request.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class UpdateCommentForm {

    private final long boardId;
    private final String commentContent;
    private final String commentStatus;
}
