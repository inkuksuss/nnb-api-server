package com.smartFarm.was.domain.dto.request.board;

import lombok.*;

@AllArgsConstructor
@Getter
@Builder
@ToString
public class AddBoardForm {

    private String boardTitle;
    private String boardContent;
    private String boardStatus;
    private Long categoryId;
}
