package com.smartFarm.was.domain.model;

import java.sql.Timestamp;

public class Board {

    private Integer boardId;
    private Integer memberId;
    private Integer categoryId;
    private String boardTitle;
    private String boardContent;
    private String secret;
    private String status;
    private Timestamp boardCreated;
    private Timestamp boardLastUpdated;
    private Integer boardView;


}
