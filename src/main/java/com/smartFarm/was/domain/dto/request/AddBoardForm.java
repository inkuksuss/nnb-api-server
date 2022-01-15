package com.smartFarm.was.domain.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter @Setter
public class AddBoardForm {

    private String boardTitle;
    private String boardContent;
    private String boardSecret;
    private Long categoryId;
}
