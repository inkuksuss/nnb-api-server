package com.smartFarm.was.web.service;

import com.smartFarm.was.domain.request.board.AddBoardForm;
import com.smartFarm.was.domain.response.board.BoardDetailVO;
import com.smartFarm.was.web.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @Test
    void addNoticeBoard() {
        //given
        long memberId = 36l;
        String boardTitle = "테스트 비공개.";
        String boardContent = "공지 게시판3.";
        String boardSecret = "owner";
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
        String boardTitle = "테스트2 비공개";
        String boardContent = "faq2입니다.";
        String boardSecret = "delete";
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
    void getDetails() throws NotFoundException {
        //given
        BoardDetailVO boardDetail = boardService.findBoardDetail(33l, 35l);
        log.info("test = {}", boardDetail.toString());
    }

    @Test
    void deleteBoards() {
        int i = boardRepository.deleteByIds(33l, 35l);
        System.out.println(i);
    }

    @Test
    void getFAQBoards() {
    }
}