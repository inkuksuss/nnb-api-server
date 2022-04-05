package com.smartFarm.was.domain.request.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteCommentForm {

    private final long boardId;
}
