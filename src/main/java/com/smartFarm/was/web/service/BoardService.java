package com.smartFarm.was.web.service;


import com.smartFarm.was.domain.dto.board.AddBoardDto;
import com.smartFarm.was.domain.dto.comment.CommentDto;
import com.smartFarm.was.domain.dto.mapping.BoardMemberMappingDto;
import com.smartFarm.was.domain.dto.board.DeleteBoardDto;
import com.smartFarm.was.domain.entity.Member;
import com.smartFarm.was.domain.dto.board.UpdateBoardDto;
import com.smartFarm.was.domain.dto.board.BoardDto;
import com.smartFarm.was.domain.response.board.BoardResponse;
import com.smartFarm.was.domain.entity.Board;
import com.smartFarm.was.web.repository.BoardRepository;
import com.smartFarm.was.web.utils.MemberAuthenticationUtils;
import com.smartFarm.was.web.utils.StatusCheckUtils;
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
    private final CommentService commentService;
    private final MessageSource messageSource;

    @Transactional
    public long addBoard(AddBoardDto addBoardDto) throws SQLException {

        boardRepository.addBoard(addBoardDto);

        return addBoardDto.getBoardId();
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> getNoticeBoards() throws SQLException {

        return boardRepository.getNoticeBoards();
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> getFAQBoards() throws SQLException {

        return boardRepository.getFAQBoards();
    }

    @Transactional(readOnly = true)
    public BoardResponse getBoard(long boardId) throws Exception {

        BoardDto boardDto = boardRepository.getBoard(boardId)
                .orElseThrow(() -> new NotFoundException(messageSource.getMessage("fail.find", new Object[]{BOARD_TYPE}, null)));


        if (StatusCheckUtils.isDeleted(boardDto.getStateDel())) {

            return BoardResponse.caseDelete();

        } else {

            if (StatusCheckUtils.isPublic(boardDto.getBoardStatus())) {

                if (MemberAuthenticationUtils.isMember()) {
                    Member member = MemberAuthenticationUtils.getMemberAuthentication();
                    BoardMemberMappingDto boardMemberMappingDto = BoardMemberMappingDto.of(boardId, member.getMemberId());

                    if (checkOwnerById(boardMemberMappingDto)) {

                        return BoardResponse.caseOwner(boardDto);

                    } else {

                        return filteredResponse(boardDto);

                    }

                } else {

                    return filteredResponse(boardDto);

                }

            } else {

                if (MemberAuthenticationUtils.isMember()) {
                    Member member = MemberAuthenticationUtils.getMemberAuthentication();
                    BoardMemberMappingDto boardMemberMappingDto = BoardMemberMappingDto.of(boardId, member.getMemberId());

                    if (checkOwnerById(boardMemberMappingDto)) {

                        return BoardResponse.caseOwner(boardDto);

                    } else {

                        return BoardResponse.casePrivate();
                    }
                } else {

                    return BoardResponse.casePrivate();

                }
            }
        }
    }

    private BoardResponse filteredResponse(BoardDto boardDto) {
        List<CommentDto> commentDtoList = boardDto.getCommentDtoList();

        List<CommentDto> filteredCommentDtoList = commentService.filteredCommentList(commentDtoList);
        boardDto.setCommentDtoList(filteredCommentDtoList);

        return BoardResponse.casePublic(boardDto);
    }

    @Transactional
    public void deleteBoard(long boardId) {

        Member member = MemberAuthenticationUtils.getMemberAuthentication();

        DeleteBoardDto deleteBoardDto = new DeleteBoardDto(boardId, member.getMemberId());

        try {
            boardRepository.deleteBoard(deleteBoardDto);
        } catch (SQLException e) {
            throw new RuntimeException(messageSource.getMessage("fail.delete", new Object[]{BOARD_TYPE}, null));
        }
    }

    @Transactional
    public void updateBoard(UpdateBoardDto updateBoardDto) {

        try {
            boardRepository.updateBoard(updateBoardDto);
        } catch (SQLException e) {
            throw new RuntimeException(messageSource.getMessage("fail.update", new Object[]{BOARD_TYPE}, null));
        }
    }

    @Transactional
    public boolean checkOwnerById(BoardMemberMappingDto boardMemberMapperDto) throws Exception {
        Board board = boardRepository.getBoardById(boardMemberMapperDto.getBoardId())
                .orElseThrow(() -> new NotFoundException(messageSource.getMessage("fail.find", new Object[]{BOARD_TYPE}, null)));

        if (board.getMemberId() == boardMemberMapperDto.getMemberId()) {
            return true;
        } else {
            return false;
        }
    }
}

