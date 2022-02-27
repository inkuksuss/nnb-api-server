package com.smartFarm.was.domain.dto.response.board;

import lombok.Getter;

import java.sql.Timestamp;


@Getter
public class BoardsDto {

    private long boardView;
    private String memberName;
    private String boardTitle;
    private Timestamp boardCreated;
}
