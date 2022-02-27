package com.smartFarm.was.domain.dto.response.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString
@RequiredArgsConstructor
public class BoardDetailDto {

    private long boardView;
    private long memberId;
    private String memberName;
    private String categoryValue;
    private String boardTitle;
    private String boardContent;
    private String boardStatus;
    private Timestamp boardCreated;
    private Timestamp boardLastUpdated;
}
