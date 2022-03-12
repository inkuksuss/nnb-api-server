package com.smartFarm.was.web.service;


import com.smartFarm.was.domain.dto.board.DeleteBoardDto;
import com.smartFarm.was.domain.entity.sub.Status;
import com.smartFarm.was.domain.response.board.BoardDetailResponse;
import com.smartFarm.was.domain.dto.board.UpdateBoardDto;
import com.smartFarm.was.domain.dto.board.BoardDetailDto;
import com.smartFarm.was.domain.response.board.BoardResponse;
import com.smartFarm.was.domain.entity.Board;
import com.smartFarm.was.web.repository.BoardRepository;
import com.smartFarm.was.domain.request.board.AddBoardForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private static final String BOARD_TYPE = "게시물";

    private final BoardRepository boardRepository;
    private final MessageSource messageSource;

    @Transactional
    public long addBoard(AddBoardForm addBoardForm, Long memberId) throws SQLException {
        Board board = Board.of(addBoardForm, memberId);

        boardRepository.add(board);

        return board.getBoardId();
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> getNoticeBoards() throws SQLException {

        return boardRepository.findAllNotice();
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> getFAQBoards() throws SQLException {

        return boardRepository.findAllFAQ();
    }

    @Transactional(readOnly = true)
    public BoardDetailResponse findBoardDetail(long boardId, long memberId) throws Exception {

        BoardDetailDto boardDetailDto = boardRepository.findByIdDetail(boardId)
                .orElseThrow(() -> new NotFoundException(messageSource.getMessage("fail.find", new Object[]{BOARD_TYPE}, null)));

        if (boardDetailDto.getBoardStatus().equals(Status.DELETE.getStatusValue())) return BoardDetailResponse.of(Status.DELETE.isOwner(), boardDetailDto);
        else if (boardDetailDto.getMemberId() == memberId) return BoardDetailResponse.of(Status.OWNER.isOwner(), boardDetailDto);
        else if (boardDetailDto.getBoardStatus().equals(Status.PUBLIC.getStatusValue())) return BoardDetailResponse.of(Status.PUBLIC.isOwner(), boardDetailDto);
        else if (boardDetailDto.getBoardStatus().equals(Status.PRIVATE.getStatusValue())) return BoardDetailResponse.of(Status.PRIVATE.isOwner(), boardDetailDto);
        else throw new IllegalArgumentException(messageSource.getMessage("fail", null, null));
    }

    @Transactional
    public void delete(long boardId, long memberId) throws SQLException {

        DeleteBoardDto deleteBoardDto = new DeleteBoardDto(boardId, memberId, Status.DELETE.getStatusValue());

        int result = boardRepository.deleteByDeleteBoardDto(deleteBoardDto);

        if (result != 1) throw new RuntimeException(messageSource.getMessage("fail.delete", new Object[]{BOARD_TYPE}, null));
    }

    @Transactional
    public Optional<BoardDetailDto> update(UpdateBoardDto updateBoardDto) throws Exception {

        long boardId = updateBoardDto.getBoardId();

        if (boardRepository.updateByUpdateForm(updateBoardDto) != 1) return Optional.empty();

        return Optional.of(boardRepository.findByIdDetail(boardId).orElseThrow(() -> new NotFoundException("게시물을 찾을 수 없습니다.")));
    }
}

