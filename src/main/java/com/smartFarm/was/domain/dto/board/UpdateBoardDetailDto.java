package com.smartFarm.was.domain.dto.board;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;


@Getter
@Builder
public class UpdateBoardDetailDto {

    private final long boardId;
    private final long memberId;
    private final long categoryId;
    private final String boardTitle;
    private final String boardContent;
    private final String boardStatus;
    private final Timestamp boardLastUpdated;
}
