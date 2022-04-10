package com.smartFarm.was.domain.response.board;

import com.smartFarm.was.domain.dto.board.BoardDto;
import com.smartFarm.was.domain.dto.comment.CommentDto;
import com.smartFarm.was.domain.entity.sub.Status;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@Getter
public class BoardResponse {
    private String result;
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

    private BoardResponse() {}

    public static BoardResponse casePublic(BoardDto boardDto) {

        BoardResponse boardResponse = new BoardResponse();
        boardResponse.result = Status.PUBLIC.name();
        boardResponse.boardId = boardDto.getBoardId();
        boardResponse.boardView = boardDto.getBoardView();
        boardResponse.memberId = boardDto.getMemberId();
        boardResponse.memberName = boardDto.getMemberName();
        boardResponse.boardTitle = boardDto.getBoardTitle();
        boardResponse.boardContent = boardDto.getBoardContent();
        boardResponse.categoryValue = boardDto.getCategoryValue();
        boardResponse.boardStatus = boardDto.getBoardStatus();
        boardResponse.boardCreated = boardDto.getBoardCreated();
        boardResponse.boardLastUpdated = boardDto.getBoardLastUpdated();
        boardResponse.commentDtoList = boardDto.getCommentDtoList();

        return boardResponse;
    }

    public static BoardResponse caseOwner(BoardDto boardDto) {

        BoardResponse boardResponse = new BoardResponse();
        boardResponse.result = Status.OWNER.name();
        boardResponse.boardId = boardDto.getBoardId();
        boardResponse.boardView = boardDto.getBoardView();
        boardResponse.memberId = boardDto.getMemberId();
        boardResponse.memberName = boardDto.getMemberName();
        boardResponse.boardTitle = boardDto.getBoardTitle();
        boardResponse.boardContent = boardDto.getBoardContent();
        boardResponse.categoryValue = boardDto.getCategoryValue();
        boardResponse.boardStatus = boardDto.getBoardStatus();
        boardResponse.boardCreated = boardDto.getBoardCreated();
        boardResponse.boardLastUpdated = boardDto.getBoardLastUpdated();
        boardResponse.commentDtoList = boardDto.getCommentDtoList();

        return boardResponse;
    }

    public static BoardResponse caseDelete() {

        BoardResponse boardResponse = new BoardResponse();
        boardResponse.result = Status.DELETE.name();

        return boardResponse;
    }

    public static BoardResponse casePrivate() {

        BoardResponse boardResponse = new BoardResponse();
        boardResponse.result = Status.PRIVATE.name();

        return boardResponse;
    }
}
