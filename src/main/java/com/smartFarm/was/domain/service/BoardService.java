package com.smartFarm.was.domain.service;


import com.smartFarm.was.domain.model.Board;
import com.smartFarm.was.domain.repository.BoardRepository;
import com.smartFarm.was.web.dto.request.AddBoardDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@Slf4j
//@MapperScan(basePackages = "com.smartFarm.was.domain.repository")
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void addBoard(AddBoardDto addBoardDto, Long memberId) {

        Timestamp now = new Timestamp(System.currentTimeMillis());
        Board board = new Board(addBoardDto, memberId, now);
        boardRepository.add(board);
    }


}
