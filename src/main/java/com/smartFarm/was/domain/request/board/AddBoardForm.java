package com.smartFarm.was.domain.request.board;

import lombok.*;

@AllArgsConstructor
@Getter
@ToString
public class AddBoardForm {

    private final String boardTitle;
    private final String boardContent;
    private final String boardStatus;
    private final long categoryId;
    private final long memberId;
}
