package com.smartFarm.was.web.service;

import com.smartFarm.was.domain.dto.comment.AddCommentDto;
import com.smartFarm.was.domain.dto.comment.CommentDto;
import com.smartFarm.was.domain.dto.comment.GetCommentDto;

import java.sql.SQLException;

public interface CommentService {

    AddCommentDto addComment(AddCommentDto addCommentDto) throws SQLException;

    GetCommentDto getComment(CommentDto commentDto) throws SQLException;

//    updateComment(UpdateCommentDto updateCommentDto) throws SQLException;
//
//    deleteComment(long commentId) throws SQLException;
}
