package com.smartFarm.was.domain.entity;

import com.smartFarm.was.domain.request.board.AddBoardForm;
import lombok.Getter;

import java.sql.Timestamp;


@Getter
public class Board {

    private long boardId;
    private long memberId;
    private long categoryId;
    private long boardView;
    private String boardTitle;
    private String boardContent;
    private String boardStatus;
    private Timestamp boardCreated;
    private Timestamp boardLastUpdated;
    private char stateDel;

    public static Board of(AddBoardForm addBoardForm, long memberId) {
        Board board = new Board();
        board.memberId = memberId;
        board.categoryId = addBoardForm.getCategoryId();
        board.boardTitle = addBoardForm.getBoardTitle();
        board.boardContent = addBoardForm.getBoardContent();
        board.boardStatus = addBoardForm.getBoardStatus();
        board.boardView = 0;
        board.boardCreated = new Timestamp(System.currentTimeMillis());
        board.boardLastUpdated = new Timestamp(System.currentTimeMillis());
        board.stateDel = 'N';
        return board;
    }

    private Board() {}
}
