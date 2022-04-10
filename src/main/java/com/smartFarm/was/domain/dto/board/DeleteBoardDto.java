package com.smartFarm.was.domain.dto.board;


import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

public class DeleteBoardDto {

    private final Long boardId;
    private final Long memberId;
    private final Timestamp boardLastUpdated;
    private final char stateDel;

    public DeleteBoardDto(Long boardId, Long memberId) {
        this.boardId = boardId;
        this.memberId = memberId;
        this.boardLastUpdated = new Timestamp(System.currentTimeMillis());
        this.stateDel = 'Y';
    }
}
