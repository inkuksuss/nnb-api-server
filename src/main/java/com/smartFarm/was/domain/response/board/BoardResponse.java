package com.smartFarm.was.domain.response.board;

import lombok.Getter;

import java.sql.Timestamp;


@Getter
public class BoardResponse {

    private long boardId;
    private long boardView;
    private String memberName;
    private String boardTitle;
    private Timestamp boardCreated;
}
