package com.smartFarm.was.domain.dto.mapping;


import lombok.Getter;

@Getter
public class BoardMemberMappingDto {

    private long boardId;
    private long memberId;

    private BoardMemberMappingDto() {}

    public static BoardMemberMappingDto of(long boardId, long memberId) {
        BoardMemberMappingDto boardMemberMappingDto = new BoardMemberMappingDto();

        boardMemberMappingDto.boardId = boardId;
        boardMemberMappingDto.memberId = memberId;

        return boardMemberMappingDto;
    }
}
