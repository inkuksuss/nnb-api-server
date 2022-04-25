package com.smartFarm.was.domain.dto.comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class GetCommentDto {

    private long memberId;
    private long boardId;
    private long commentId;
    private boolean isOwnerFlag;
    private List<CommentDto> commentDtoList;

    public GetCommentDto() {}

    public static GetCommentDto forOwner(long boardId) {
        GetCommentDto getCommentDto = new GetCommentDto();
        getCommentDto.boardId =  boardId;
        getCommentDto.isOwnerFlag = true;
        return getCommentDto;
    }

    public static GetCommentDto forGuest(long boardId) {
        GetCommentDto getCommentDto = new GetCommentDto();
        getCommentDto.boardId =  boardId;
        getCommentDto.isOwnerFlag = false;
        return getCommentDto;
    }
}
