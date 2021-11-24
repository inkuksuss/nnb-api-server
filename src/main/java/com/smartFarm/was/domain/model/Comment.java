package com.smartFarm.was.domain.model;

import java.sql.Timestamp;

public class Comment {

    private Integer commentId;
    private Integer boardId;
    private Integer memberId;
    private String commentContent;
    private Timestamp commentCreated;
    private Timestamp commentLastUpdated;
    private String status;
}
