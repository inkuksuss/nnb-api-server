package com.smartFarm.was.web.service;

import com.smartFarm.was.domain.dto.comment.*;
import com.smartFarm.was.domain.response.ResultResponse;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {

    ResultResponse<List<CommentDto>> addComment(AddCommentDto addCommentDto) throws SQLException;

    ResultResponse<List<CommentDto>> getCommentList(GetCommentDto getCommentDto) throws SQLException;

    ResultResponse<List<CommentDto>> updateComment(UpdateCommentDto updateCommentDto) throws SQLException;

    ResultResponse<List<CommentDto>> deleteComment(DeleteCommentDto deleteCommentDto) throws SQLException;

    List<CommentDto> filteredCommentList(List<CommentDto> commentDtoList);
}
