package com.smartFarm.was.web.service.impl;


import com.smartFarm.was.domain.dto.board.AddBoardDto;
import com.smartFarm.was.domain.dto.comment.CommentDto;
import com.smartFarm.was.domain.dto.mapping.BoardMemberMappingDto;
import com.smartFarm.was.domain.dto.board.DeleteBoardDto;
import com.smartFarm.was.domain.dto.board.UpdateBoardDto;
import com.smartFarm.was.domain.dto.board.BoardDto;
import com.smartFarm.was.domain.response.ResultCode;
import com.smartFarm.was.domain.response.ResultResponse;
import com.smartFarm.was.domain.response.board.BoardResponse;
import com.smartFarm.was.domain.entity.Board;
import com.smartFarm.was.web.repository.BoardRepository;
import com.smartFarm.was.web.service.BoardService;
import com.smartFarm.was.web.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

import static com.smartFarm.was.web.utils.MemberAuthenticationUtils.*;
import static com.smartFarm.was.web.utils.StatusCheckUtils.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private static final String BOARD_TYPE = "게시물";

    private final BoardRepository boardRepository;
    private final CommentService commentService;
    private final MessageSource messageSource;

    @Transactional
    public ResultResponse<Long> addBoard(AddBoardDto addBoardDto) throws SQLException {

        int result = boardRepository.addBoard(addBoardDto);

        if (querySuccess(result)) {
            return new ResultResponse<>(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), addBoardDto.getBoardId());
        } else {
            return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public ResultResponse<List<BoardResponse>> getNoticeBoards() throws SQLException {

        List<BoardResponse> noticeBoardList = boardRepository.getNoticeBoards();

        return new ResultResponse<>(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), noticeBoardList);
    }

    @Transactional(readOnly = true)
    public ResultResponse<List<BoardResponse>> getFAQBoards() throws SQLException {

        List<BoardResponse> faqBoardList = boardRepository.getFAQBoards();

        return new ResultResponse<>(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), faqBoardList);
    }

    @Transactional(readOnly = true)
    public ResultResponse<BoardResponse> getBoard(long boardId) throws Exception {

        BoardDto boardDto = boardRepository.getBoard(boardId)
                .orElseThrow(() -> new NotFoundException(messageSource.getMessage("fail.find", new Object[]{BOARD_TYPE}, null)));

        ResultResponse<BoardResponse> response = new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage());

        if (isDeleted(boardDto.getStateDel())) {

            response.setResultCode(ResultCode.DELETED_CONTENTS.getCode());
            response.setResultMessage(ResultCode.DELETED_CONTENTS.getMessage());

        } else {

            if (isPublic(boardDto.getBoardStatus())) {
                if (isMember()) {

                    BoardMemberMappingDto boardMemberMappingDto = BoardMemberMappingDto.of(boardId, getMemberIdByMemberAuthentication());

                    if (checkOwnerById(boardMemberMappingDto)) {

                        response.setHttpStatus(HttpStatus.OK);
                        response.setResultMessage(ResultCode.SUCCESS.getMessage());
                        response.setResultCode(ResultCode.SUCCESS.getCode());
                        response.setData(BoardResponse.caseOwner(boardDto));

                    } else {

                        BoardResponse boardResponse = filteredResponse(boardDto);
                        response.setHttpStatus(HttpStatus.OK);
                        response.setResultMessage(ResultCode.SUCCESS.getMessage());
                        response.setResultCode(ResultCode.SUCCESS.getCode());
                        response.setData(boardResponse);

                    }

                } else {

                    BoardResponse boardResponse = filteredResponse(boardDto);
                    response.setHttpStatus(HttpStatus.OK);
                    response.setResultMessage(ResultCode.SUCCESS.getMessage());
                    response.setResultCode(ResultCode.SUCCESS.getCode());
                    response.setData(boardResponse);

                }

            } else {

                if (isMember()) {

                    BoardMemberMappingDto boardMemberMappingDto = BoardMemberMappingDto.of(boardId, getMemberIdByMemberAuthentication());

                    if (checkOwnerById(boardMemberMappingDto)) {

                        response.setHttpStatus(HttpStatus.OK);
                        response.setResultMessage(ResultCode.SUCCESS.getMessage());
                        response.setResultCode(ResultCode.SUCCESS.getCode());
                        response.setData(BoardResponse.caseOwner(boardDto));

                    } else {

                        response.setHttpStatus(HttpStatus.BAD_REQUEST);
                        response.setResultMessage(ResultCode.PRIVATE_CONTENTS.getMessage());
                        response.setResultCode(ResultCode.PRIVATE_CONTENTS.getCode());

                    }
                } else {

                    response.setHttpStatus(HttpStatus.BAD_REQUEST);
                    response.setResultMessage(ResultCode.PRIVATE_CONTENTS.getMessage());
                    response.setResultCode(ResultCode.PRIVATE_CONTENTS.getCode());

                }
            }
        }
        return response;
    }

    private BoardResponse filteredResponse(BoardDto boardDto) {
        List<CommentDto> commentDtoList = boardDto.getCommentDtoList();

        List<CommentDto> filteredCommentDtoList = commentService.filteredCommentList(commentDtoList);
        boardDto.setCommentDtoList(filteredCommentDtoList);

        return BoardResponse.casePublic(boardDto);
    }

    @Transactional
    public ResultResponse<Void> deleteBoard(long boardId) throws SQLException {

        DeleteBoardDto deleteBoardDto = new DeleteBoardDto(boardId, getMemberIdByMemberAuthentication());

        int result = boardRepository.deleteBoard(deleteBoardDto);

        if (querySuccess(result)) {
            return new ResultResponse<>(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
        } else {
            return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage());
        }
    }

    @Transactional
    public ResultResponse<Long> updateBoard(UpdateBoardDto updateBoardDto) throws SQLException{

        int result = boardRepository.updateBoard(updateBoardDto);

        if (querySuccess(result)) {
            return new ResultResponse<>(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), updateBoardDto.getBoardId());
        } else {
            return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage());
        }
    }

    @Transactional
    public boolean checkOwnerById(BoardMemberMappingDto boardMemberMapperDto) throws Exception {
        Board board = boardRepository.getBoardById(boardMemberMapperDto.getBoardId())
                .orElseThrow(() -> new NotFoundException(messageSource.getMessage("fail.find", new Object[]{BOARD_TYPE}, null)));

        return (board.getMemberId() == boardMemberMapperDto.getMemberId());
    }
}

