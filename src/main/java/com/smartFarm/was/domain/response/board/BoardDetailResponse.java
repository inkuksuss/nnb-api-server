package com.smartFarm.was.domain.response.board;

import com.smartFarm.was.domain.dto.board.BoardDetailDto;
import com.smartFarm.was.domain.dto.comment.CommentDto;
import com.smartFarm.was.domain.entity.sub.Status;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Getter
public class BoardDetailResponse {
    private boolean isOwner;
    private long boardId;
    private long boardView;
    private long memberId;
    private String memberName;
    private String boardTitle;
    private String boardContent;
    private String categoryValue;
    private String boardStatus;
    private Timestamp boardCreated;
    private Timestamp boardLastUpdated;
    private List<CommentDto> commentDtoList;

    private BoardDetailResponse() {}

    public static BoardDetailResponse of(boolean isOwner, BoardDetailDto boardDetailDto) {

        final String checkAccess = boardDetailDto.getBoardStatus();

        BoardDetailResponse boardDetailResponse = new BoardDetailResponse();
        boardDetailResponse.isOwner = isOwner;

        if (checkAccess.equals(Status.PUBLIC.getStatusValue()) || checkAccess.equals(Status.OWNER.getStatusValue())) {
            boardDetailResponse.boardId = boardDetailDto.getBoardId();
            boardDetailResponse.boardView = boardDetailDto.getBoardView();
            boardDetailResponse.memberId = boardDetailDto.getMemberId();
            boardDetailResponse.memberName = boardDetailDto.getMemberName();
            boardDetailResponse.boardTitle = boardDetailDto.getBoardTitle();
            boardDetailResponse.boardContent = boardDetailDto.getBoardContent();
            boardDetailResponse.categoryValue = boardDetailDto.getCategoryValue();
            boardDetailResponse.boardStatus = checkAccess;
            boardDetailResponse.boardCreated = boardDetailDto.getBoardCreated();
            boardDetailResponse.boardLastUpdated = boardDetailDto.getBoardLastUpdated();
            boardDetailResponse.commentDtoList = boardDetailDto.getCommentDtoList();
        } else {
            boardDetailResponse.boardId = -1l;
            boardDetailResponse.boardView = -1l;
            boardDetailResponse.memberId = -1l;
            boardDetailResponse.memberName = "";
            boardDetailResponse.boardTitle = "";
            boardDetailResponse.boardContent = "";
            boardDetailResponse.categoryValue = "";
            boardDetailResponse.boardStatus = checkAccess;
            boardDetailResponse.boardCreated = null;
            boardDetailResponse.boardLastUpdated = null;
            boardDetailResponse.commentDtoList = Collections.EMPTY_LIST;
        }
        return boardDetailResponse;
    }
}
