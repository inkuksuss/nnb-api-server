package com.smartFarm.was.web.service;


import com.smartFarm.was.domain.dto.response.boardsDto;
import com.smartFarm.was.domain.model.Board;
import com.smartFarm.was.web.repository.BoardRepository;
import com.smartFarm.was.domain.dto.request.AddBoardForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void addBoard(AddBoardForm addBoardForm, Long memberId) {
        Board board = Board.of(addBoardForm, memberId);
        boardRepository.add(board);
    }

    public List<boardsDto> getNoticeBoards() {
        return boardRepository.getAllNotice();
    }
    public List<boardsDto> getFAQBoards() {
        return boardRepository.getAllFAQ();
    }
}

