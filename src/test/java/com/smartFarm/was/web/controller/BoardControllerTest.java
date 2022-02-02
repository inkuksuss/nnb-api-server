package com.smartFarm.was.web.controller;

import com.smartFarm.was.domain.dto.request.AddBoardForm;
import com.smartFarm.was.domain.dto.response.boardsDto;
import com.smartFarm.was.web.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
class BoardControllerTest {

    @Autowired
    BoardService boardService;

    @Test
    void noticeBoards() {
        List<boardsDto> noticeBoards = boardService.getNoticeBoards();
        log.info("boards = {}", noticeBoards.toString());
    }

    @Test
    void faqBoards() {
        List<boardsDto> faqBoards = boardService.getFAQBoards();
        log.info("boards = {}", faqBoards.toString());
    }

    @Test
    void addBoard() {
        Long memberId = 19L;
        AddBoardForm addBoardForm = AddBoardForm.builder()
                .boardTitle("hello3")
                .boardContent("안녕3")
                .boardSecret("p")
                .categoryId(2L)
                .build();
        boardService.addBoard(addBoardForm, memberId);
    }
}