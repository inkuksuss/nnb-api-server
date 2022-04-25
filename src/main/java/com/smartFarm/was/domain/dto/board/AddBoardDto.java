package com.smartFarm.was.domain.dto.board;

import com.smartFarm.was.domain.request.board.AddBoardForm;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class AddBoardDto {

    private Long boardId;
    private Long memberId;
    private Long categoryId;
    private int boardView;
    private String boardTitle;
    private String boardContent;
    private String boardStatus;
    private Timestamp boardCreated;
    private Timestamp boardLastUpdated;
    private char stateDel;

    private AddBoardDto() {}

    public static AddBoardDto of(AddBoardForm addBoardForm, Long memberId) {

        AddBoardDto addBoardDto = new AddBoardDto();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        addBoardDto.memberId = memberId;
        addBoardDto.categoryId = addBoardForm.getCategoryId();
        addBoardDto.boardTitle = addBoardForm.getBoardTitle();
        addBoardDto.boardContent = addBoardForm.getBoardContent();
        addBoardDto.boardStatus = addBoardForm.getBoardStatus();
        addBoardDto.boardView = 0;
        addBoardDto.boardCreated = currentTime;
        addBoardDto.boardLastUpdated = currentTime;
        addBoardDto.stateDel = 'N';

        return addBoardDto;
    }
}
