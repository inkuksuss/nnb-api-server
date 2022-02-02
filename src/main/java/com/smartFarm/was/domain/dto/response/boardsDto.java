package com.smartFarm.was.domain.dto.response;

import java.sql.Timestamp;


public class boardsDto {

    private String memberName;
    private Integer boardView;
    private String boardTitle;
    private Timestamp boardCreated;

    public String getMemberName() {
        return memberName;
    }

    public Integer getBoardView() {
        return boardView;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public Timestamp getBoardCreated() {
        return boardCreated;
    }
}
