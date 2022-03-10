package com.smartFarm.was.web.service;


import com.smartFarm.was.domain.entity.sub.Status;
import com.smartFarm.was.domain.response.board.BoardDetailResponse;
import com.smartFarm.was.domain.dto.board.UpdateBoardDetailDto;
import com.smartFarm.was.domain.dto.board.BoardDetailDto;
import com.smartFarm.was.domain.response.board.BoardResponse;
import com.smartFarm.was.domain.entity.Board;
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
    public void addBoard(AddBoardForm addBoardForm, Long memberId) throws SQLException {
        Board board = Board.of(addBoardForm, memberId);
        boardRepository.add(board);
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> getNoticeBoards() throws SQLException {
        return boardRepository.findAllNotice();
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> getFAQBoards() throws SQLException {
        return boardRepository.findAllFAQ();
    }

    @Transactional
    public BoardDetailResponse findBoardDetail(long boardId, long memberId) throws Exception {
        BoardDetailDto boardDetailVO = boardRepository.findByIdDetail(boardId).orElseThrow(() -> new NotFoundException("게시물을 찾을 수 없습니다."));

        if (boardDetailVO.getBoardStatus().equals(Status.DELETE.getStatusValue())) return new BoardDetailResponse(Status.DELETE, Optional.empty());
        else if (boardDetailVO.getMemberId() == memberId) return new BoardDetailResponse(Status.OWNER, Optional.ofNullable(boardDetailVO));
        else if (boardDetailVO.getBoardStatus().equals(Status.PUBLIC.getStatusValue())) return new BoardDetailResponse(Status.PUBLIC, Optional.ofNullable(boardDetailVO));
        else if (boardDetailVO.getBoardStatus().equals(Status.PRIVATE.getStatusValue())) return new BoardDetailResponse(Status.PRIVATE, Optional.empty());
        else throw new IllegalArgumentException("잘못된 요청입니다.");
    }

    @Transactional
    public void delete(long boardId, long memberId) throws SQLException {
        int result = boardRepository.deleteByIds(boardId, memberId);
        if (result == 0) throw new IllegalArgumentException();
    }

    @Transactional
    public Optional<BoardDetailDto> update(UpdateBoardDetailDto updateBoardDetail) throws Exception {
        long boardId = updateBoardDetail.getBoardId();

        if (boardRepository.updateByUpdateForm(updateBoardDetail) != 1) return Optional.empty();

        return Optional.of(boardRepository.findByIdDetail(boardId).orElseThrow(() -> new NotFoundException("게시물을 찾을 수 없습니다.")));
    }
}

