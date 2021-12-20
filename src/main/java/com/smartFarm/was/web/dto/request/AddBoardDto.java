package com.smartFarm.was.web.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter @Setter
public class AddBoardDto {

    private String boardTitle;
    private String boardContent;
    private String boardSecret;
    private Long categoryId;
}
