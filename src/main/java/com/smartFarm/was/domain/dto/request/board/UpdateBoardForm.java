package com.smartFarm.was.domain.dto.request.board;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;


@Getter
@Builder
public class UpdateBoardForm {

    private long boardId;
    private long memberId;
    private long categoryId;
    private String boardTitle;
    private String boardContent;
    private String boardStatus;
    private Timestamp boardLastUpdated;
}
