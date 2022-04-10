package com.smartFarm.was.web.controller;

import com.smartFarm.was.domain.request.board.AddBoardForm;
import com.smartFarm.was.domain.response.board.BoardResponse;
import com.smartFarm.was.web.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;


@Slf4j
@SpringBootTest
class BoardControllerTest {

    @Autowired
    BoardService boardService;

    @Test
    void noticeBoards() throws SQLException {
        List<BoardResponse> noticeBoards = boardService.getNoticeBoards();
        log.info("boards = {}", noticeBoards.toString());
    }

    @Test
    void faqBoards() throws SQLException {
        List<BoardResponse> faqBoards = boardService.getFAQBoards();
        log.info("boards = {}", faqBoards.toString());
    }

    @Test
    void addBoard() throws SQLException {
        long memberId = 34l;
//        AddBoardForm addBoardForm = AddBoardForm.builder()
//                .boardTitle("hello3")
//                .boardContent("안녕3")
//                .boardStatus("public")
//                .categoryId(2L)
//                .build();
//        boardService.addBoard(addBoardForm);
    }

    @Test
    void getDetail() throws NotFoundException {
//        BoardService.DetailResult boardDetail = boardService.findBoardDetail(28l, 34l);
//        log.info("boardDetail={}", boardDetail.toString());
    }
}