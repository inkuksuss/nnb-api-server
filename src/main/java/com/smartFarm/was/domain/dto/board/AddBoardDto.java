package com.smartFarm.was.domain.dto.board;

import com.smartFarm.was.domain.request.board.AddBoardForm;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class AddBoardDto {

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

    private AddBoardDto() {}

    public static AddBoardDto of(AddBoardForm addBoardForm, long memberId) {

        AddBoardDto addBoardDto = new AddBoardDto();

        addBoardDto.memberId = memberId;
        addBoardDto.categoryId = addBoardForm.getCategoryId();
        addBoardDto.boardTitle = addBoardForm.getBoardTitle();
        addBoardDto.boardContent = addBoardForm.getBoardContent();
        addBoardDto.boardStatus = addBoardForm.getBoardStatus();
        addBoardDto.boardView = 0;
        addBoardDto.boardCreated = new Timestamp(System.currentTimeMillis());
        addBoardDto.boardLastUpdated = new Timestamp(System.currentTimeMillis());
        addBoardDto.stateDel = 'N';

        return addBoardDto;
    }
}
