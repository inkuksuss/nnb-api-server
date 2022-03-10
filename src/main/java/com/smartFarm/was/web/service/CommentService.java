package com.smartFarm.was.web.service;

import com.smartFarm.was.domain.dto.comment.AddCommentDto;
import com.smartFarm.was.domain.request.comment.AddCommentForm;

import java.sql.SQLException;


public interface CommentService {

    AddCommentDto addComment(AddCommentForm addCommentDto, long boardId, long memberId) throws SQLException;
}
