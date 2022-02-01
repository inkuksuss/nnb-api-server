package com.smartFarm.was.domain.dto.request;

import lombok.*;

@AllArgsConstructor
@Getter
@Builder
public class AddBoardForm {

    private String boardTitle;
    private String boardContent;
    private String boardSecret;
    private Long categoryId;
}
