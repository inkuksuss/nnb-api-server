package com.smartFarm.was.web.service;


import com.smartFarm.was.domain.dto.response.board.boardDetailDto;
import com.smartFarm.was.domain.dto.response.board.boardsDto;
import com.smartFarm.was.domain.model.Board;
import com.smartFarm.was.web.repository.BoardRepository;
import com.smartFarm.was.domain.dto.request.AddBoardForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class BoardService {

    private final static String PUBLIC = "public";

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void addBoard(AddBoardForm addBoardForm, Long memberId) {
        Board board = Board.of(addBoardForm, memberId);
        boardRepository.add(board);
    }

    public List<boardsDto> getNoticeBoards() {
        return boardRepository.findAllNotice();
    }

    public List<boardsDto> getFAQBoards() {
        return boardRepository.findAllFAQ();
    }

    public Optional<boardDetailDto> findBoardDetail(long boardId, long memberId) {
        boardDetailDto boardDetailDto = boardRepository.findByIdDetail(boardId).orElseThrow(NoSuchElementException::new);
        if (boardDetailDto.getMemberId() == memberId) return Optional.ofNullable(boardDetailDto);
        else if (boardDetailDto.getBoardStatus().equals(PUBLIC)) return Optional.ofNullable(boardDetailDto);
        else return Optional.empty();
    }
}

