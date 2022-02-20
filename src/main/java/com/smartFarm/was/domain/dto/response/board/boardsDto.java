package com.smartFarm.was.domain.dto.response.board;

import lombok.Getter;

import java.sql.Timestamp;


@Getter
public class boardsDto {

    private long boardView;
    private String memberName;
    private String boardTitle;
    private Timestamp boardCreated;
}
