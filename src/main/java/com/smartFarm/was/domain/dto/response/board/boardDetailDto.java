package com.smartFarm.was.domain.dto.response.board;

import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString
public class boardDetailDto {

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
