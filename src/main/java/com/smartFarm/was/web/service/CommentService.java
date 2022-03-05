package com.smartFarm.was.web.service;

import com.smartFarm.was.domain.request.comment.AddCommentForm;
import org.springframework.stereotype.Service;


public interface CommentService {

    void addComment(AddCommentForm addCommentDto, long boardId, long memberId);
}
