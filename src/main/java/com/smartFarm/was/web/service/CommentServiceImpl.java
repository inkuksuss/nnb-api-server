package com.smartFarm.was.web.service;

import com.smartFarm.was.domain.model.Comment;
import com.smartFarm.was.domain.request.comment.AddCommentForm;
import com.smartFarm.was.web.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public void addComment(AddCommentForm addCommentDto, long boardId, long memberId) {
        Comment comment = Comment.of(addCommentDto, boardId, memberId);
        commentRepository.add(comment);
        return;
    }
}
