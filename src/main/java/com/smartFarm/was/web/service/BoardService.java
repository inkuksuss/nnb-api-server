package com.smartFarm.was.web.service;


import com.smartFarm.was.domain.model.sub.Status;
import com.smartFarm.was.domain.response.board.BoardDetailVO;
import com.smartFarm.was.domain.dto.board.UpdateBoardDetailDto;
import com.smartFarm.was.domain.dto.board.BoardDetailDto;
import com.smartFarm.was.domain.response.board.BoardVO;
import com.smartFarm.was.domain.model.Board;
import com.smartFarm.was.web.repository.BoardRepository;
import com.smartFarm.was.domain.request.board.AddBoardForm;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
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
    public List<BoardVO> getNoticeBoards() {
        return boardRepository.findAllNotice();
    }

    @Transactional(readOnly = true)
    public List<BoardVO> getFAQBoards() {
        return boardRepository.findAllFAQ();
    }

    @Transactional
    public BoardDetailVO findBoardDetail(long boardId, long memberId) throws NotFoundException{
        BoardDetailDto boardDetailVO = boardRepository.findByIdDetail(boardId).orElseThrow(() -> new NotFoundException("게시물을 찾을 수 없습니다."));

        if (boardDetailVO.getBoardStatus().equals(Status.DELETE.getStatusValue())) return new BoardDetailVO(Status.DELETE, Optional.empty());
        else if (boardDetailVO.getMemberId() == memberId) return new BoardDetailVO(Status.OWNER, Optional.ofNullable(boardDetailVO));
        else if (boardDetailVO.getBoardStatus().equals(Status.PUBLIC.getStatusValue())) return new BoardDetailVO(Status.PUBLIC, Optional.ofNullable(boardDetailVO));
        else if (boardDetailVO.getBoardStatus().equals(Status.PRIVATE.getStatusValue())) return new BoardDetailVO(Status.PRIVATE, Optional.empty());
        else throw new IllegalArgumentException("잘못된 데이터입니다.");
    }

    @Transactional
    public void delete(long boardId, long memberId) {
        int result = boardRepository.deleteByIds(boardId, memberId);
        if (result == 0) throw new IllegalArgumentException();
    }

    @Transactional
    public Optional<BoardDetailDto> update(UpdateBoardDetailDto updateBoardDetail) throws NotFoundException {
        long boardId = updateBoardDetail.getBoardId();

        if (boardRepository.updateByUpdateForm(updateBoardDetail) != 1) return Optional.empty();

        return Optional.of(boardRepository.findByIdDetail(boardId).orElseThrow(() -> new NotFoundException("게시물을 찾을 수 없습니다.")));
    }
}

