package com.smartFarm.was.web.service;

import com.smartFarm.was.domain.dto.request.AddBoardForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Test
    void addNoticeBoard() {
        //given
        long memberId = 34l;
        String boardTitle = "안녕";
        String boardContent = "내용입니다.";
        String boardSecret = "public";
        long categoryId = 1l;
        AddBoardForm boardForm = AddBoardForm.builder()
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardStatus(boardSecret)
                .categoryId(categoryId)
                .build();

        // when
        boardService.addBoard(boardForm, memberId);
    }

    @Test
    void addFaqBoard() {
        //given
        long memberId = 34l;
        String boardTitle = "안녕하세요";
        String boardContent = "질문 게시판입니다.";
        String boardSecret = "public";
        long categoryId = 2l;
        AddBoardForm boardForm = AddBoardForm.builder()
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardStatus(boardSecret)
                .categoryId(categoryId)
                .build();

        // when
        boardService.addBoard(boardForm, memberId);
    }

    @Test
    void getNoticeBoards() {
    }

    @Test
    void getFAQBoards() {
    }
}