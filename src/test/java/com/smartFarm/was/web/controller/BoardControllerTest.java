package com.smartFarm.was.web.controller;

import com.smartFarm.was.domain.dto.request.AddBoardForm;
import com.smartFarm.was.web.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BoardControllerTest {

    @Autowired
    BoardService boardService;
    @Test
    void noticeBoards() {
    }

    @Test
    void addBoard() {
        Long memberId = 18L;
        AddBoardForm addBoardForm = AddBoardForm.builder()
                .boardTitle("hello")
                .boardContent("안녕")
                .boardSecret("p")
                .categoryId(1L)
                .build();
        boardService.addBoard(addBoardForm, memberId);
    }
}