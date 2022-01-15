package com.smartFarm.was.web.service;


import com.smartFarm.was.domain.model.Board;
import com.smartFarm.was.web.repository.BoardRepository;
import com.smartFarm.was.domain.dto.request.AddBoardForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void addBoard(AddBoardForm addBoardDto, Long memberId) {

        Timestamp now = new Timestamp(System.currentTimeMillis());
        Board board = new Board(addBoardDto, memberId, now);
        boardRepository.add(board);
    }


}
