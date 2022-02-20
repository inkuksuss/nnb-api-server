package com.smartFarm.was.domain.dto.response;

import java.sql.Timestamp;


public class boardsDto {

    private String memberName;
    private String boardTitle;
    private long boardView;
    private Timestamp boardCreated;

    public String getMemberName() {
        return memberName;
    }

    public long getBoardView() {
        return boardView;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public Timestamp getBoardCreated() {
        return boardCreated;
    }
}
