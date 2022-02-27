package com.smartFarm.was.web.service;


import com.smartFarm.was.domain.dto.request.board.UpdateBoardForm;
import com.smartFarm.was.domain.dto.response.board.BoardDetailDto;
import com.smartFarm.was.domain.dto.response.board.BoardsDto;
import com.smartFarm.was.domain.model.Board;
import com.smartFarm.was.domain.model.sub.BoardStatus;
import com.smartFarm.was.web.repository.BoardRepository;
import com.smartFarm.was.domain.dto.request.board.AddBoardForm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public void addBoard(AddBoardForm addBoardForm, Long memberId) {
        Board board = Board.of(addBoardForm, memberId);
        boardRepository.add(board);
    }

    @Transactional(readOnly = true)
    public List<BoardsDto> getNoticeBoards() {
        return boardRepository.findAllNotice();
    }

    @Transactional(readOnly = true)
    public List<BoardsDto> getFAQBoards() {
        return boardRepository.findAllFAQ();
    }

    @Transactional
    public DetailResult findBoardDetail(long boardId, long memberId) throws NotFoundException {
        BoardDetailDto boardDetailDto = boardRepository.findByIdDetail(boardId).orElseThrow(() -> new NotFoundException("게시물을 찾을 수 없습니다."));

        if (boardDetailDto.getBoardStatus().equals(BoardStatus.DELETE.getStatusValue())) return new DetailResult(BoardStatus.DELETE, Optional.empty());
        else if (boardDetailDto.getMemberId() == memberId) return new DetailResult(BoardStatus.OWNER, Optional.ofNullable(boardDetailDto));
        else if (boardDetailDto.getBoardStatus().equals(BoardStatus.PUBLIC.getStatusValue())) return new DetailResult(BoardStatus.PUBLIC, Optional.ofNullable(boardDetailDto));
        else if (boardDetailDto.getBoardStatus().equals(BoardStatus.PRIVATE.getStatusValue())) return new DetailResult(BoardStatus.PRIVATE, Optional.empty());
        else throw new IllegalArgumentException("잘못된 데이터입니다.");
    }

    @Transactional
    public void delete(long boardId, long memberId) {
        boardRepository.deleteByIds(boardId, memberId);
    }

    @Transactional
    public void update(UpdateBoardForm updateBoardForm) {
        boardRepository.updateByUpdateForm(updateBoardForm);
    }

    @Getter
    @ToString
    @RequiredArgsConstructor
    public static class DetailResult {
        private final BoardStatus boardStatus;
        private final Optional<BoardDetailDto> boardDetailDto;
    }
}

