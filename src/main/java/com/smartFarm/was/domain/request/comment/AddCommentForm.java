package com.smartFarm.was.domain.request.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@RequiredArgsConstructor
public class AddCommentForm {

    @NotEmpty(message = "이메일을 입력해주세요.")
    private final Long boardId;

    @NotEmpty(message = "이메일을 입력해주세요.")
    private final String commentContent;

    @NotEmpty(message = "이메일을 입력해주세요.")
    private final String commentStatus;
}
