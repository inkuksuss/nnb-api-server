package com.smartFarm.was.domain.dto.comment;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class GetCommentDto {

    private long memberId;
    private long boardId;
    private long commentId;
    private Boolean isOwner;
    private List<CommentDto> commentDtoList;
}
