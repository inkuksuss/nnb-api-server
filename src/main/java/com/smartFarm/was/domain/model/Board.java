package com.smartFarm.was.domain.model;

import com.smartFarm.was.domain.dto.request.AddBoardForm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
public class Board {

    private Long boardId;
    private Long memberId;
    private Long categoryId;
    private Integer boardView;
    private String boardTitle;
    private String boardContent;
    private String boardSecret;
    private String boardStatus;
    private Timestamp boardCreated;
    private Timestamp boardLastUpdated;

    public static Board ofAddBoardFormAndMemberId(AddBoardForm addBoardForm, Long memberId) {
        Board board = new Board();
        board.memberId = memberId;
        board.categoryId = addBoardForm.getCategoryId();
        board.boardTitle = addBoardForm.getBoardTitle();
        board.boardContent = addBoardForm.getBoardContent();
        board.boardSecret = addBoardForm.getBoardSecret();
        board.boardStatus = "a";
        board.boardView = 0;
        board.boardCreated = new Timestamp(System.currentTimeMillis());
        return board;
    }

    private Board() {
    }
}
