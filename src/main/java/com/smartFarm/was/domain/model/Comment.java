package com.smartFarm.was.domain.model;

import java.sql.Timestamp;

public class Comment {

    private long commentId;
    private long boardId;
    private long memberId;
    private String commentContent;
    private String status;
    private Timestamp commentCreated;
    private Timestamp commentLastUpdated;
}
