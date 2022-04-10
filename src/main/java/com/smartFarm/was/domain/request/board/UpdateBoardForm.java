package com.smartFarm.was.domain.request.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class UpdateBoardForm {

    private final Long categoryId;
    private final String boardTitle;
    private final String boardContent;
    private final String boardStatus;
}
