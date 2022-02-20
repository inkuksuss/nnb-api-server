package com.smartFarm.was.web.controller;

import com.smartFarm.was.domain.dto.request.AddBoardForm;
import com.smartFarm.was.domain.dto.response.board.boardDetailDto;
import com.smartFarm.was.domain.dto.response.board.boardsDto;
import com.smartFarm.was.web.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


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
        Long memberId = 34l;
        AddBoardForm addBoardForm = AddBoardForm.builder()
                .boardTitle("hello3")
                .boardContent("안녕3")
                .boardStatus("public")
                .categoryId(2L)
                .build();
        boardService.addBoard(addBoardForm, memberId);
    }

    @Test
    void getDetail() {
        Optional<boardDetailDto> boardDetail = boardService.findBoardDetail(28l, 34l);
        log.info("boardDetail={}", boardDetail.toString());
    }
}