package com.smartFarm.was.domain.request.board;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
public class AddBoardForm {

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String boardTitle;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String boardContent;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String boardStatus;

    @NotNull(message = "존재하지 않는 정보입니다.")
    private Long categoryId;

    @NotNull(message = "존재하지 않는 정보입니다.")
    private Long memberId;
}
