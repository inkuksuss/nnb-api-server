package com.smartFarm.was.web.service;

import com.smartFarm.was.domain.dto.board.AddBoardDto;
import com.smartFarm.was.domain.dto.board.BoardDto;
import com.smartFarm.was.domain.dto.board.UpdateBoardDto;
import com.smartFarm.was.domain.dto.comment.CommentDto;
import com.smartFarm.was.domain.dto.mapping.BoardMemberMappingDto;
import com.smartFarm.was.domain.response.ResultResponse;
import com.smartFarm.was.domain.response.board.BoardResponse;

import java.sql.SQLException;
import java.util.List;

public interface BoardService {

    ResultResponse<Long> addBoard(AddBoardDto addBoardDto) throws SQLException;

    ResultResponse<List<BoardResponse>> getNoticeBoards() throws SQLException;

    ResultResponse<List<BoardResponse>> getFAQBoards() throws SQLException;

    ResultResponse<BoardResponse> getBoard(long boardId) throws Exception;

    ResultResponse<Void> deleteBoard(long boardId) throws SQLException;

    ResultResponse<Long> updateBoard(UpdateBoardDto updateBoardDto) throws SQLException;

    boolean checkOwnerById(BoardMemberMappingDto boardMemberMapperDto) throws Exception;

}
