package com.smartFarm.was.web.service;

import com.smartFarm.was.domain.dto.comment.*;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {

    List<CommentDto> addComment(AddCommentDto addCommentDto) throws SQLException;

    List<CommentDto> getCommentList(GetCommentDto getCommentDto) throws SQLException;

    List<CommentDto> updateComment(UpdateCommentDto updateCommentDto) throws SQLException;

    List<CommentDto> deleteComment(DeleteCommentDto deleteCommentDto) throws SQLException;

    List<CommentDto> filteredCommentList(List<CommentDto> commentDtoList);
}
