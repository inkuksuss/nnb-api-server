package com.smartFarm.was.domain.model;

import com.smartFarm.was.domain.dto.request.AddBoardForm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Getter @Setter
@RequiredArgsConstructor
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

    public Board(AddBoardForm addBoardDto, Long memberId, Timestamp now) {
        this.memberId = memberId;
        this.categoryId = addBoardDto.getCategoryId();
        this.boardTitle = addBoardDto.getBoardTitle();
        this.boardContent = addBoardDto.getBoardContent();
        this.boardSecret = addBoardDto.getBoardSecret();
        this.boardStatus = "a";
        this.boardCreated = now;
        this.boardView = 0;
    }

    public
}
