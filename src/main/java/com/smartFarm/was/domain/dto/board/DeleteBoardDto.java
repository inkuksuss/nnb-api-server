package com.smartFarm.was.domain.dto.board;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteBoardDto {

    private final long boardId;
    private final long memberId;
    private final String boardStatus;
}
