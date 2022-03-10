package com.smartFarm.was.web.service;

import com.smartFarm.was.domain.dto.comment.AddCommentDto;
import com.smartFarm.was.domain.request.comment.AddCommentForm;
import com.smartFarm.was.web.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public AddCommentDto addComment(AddCommentForm addCommentDto, long boardId, long memberId) throws SQLException {
        AddCommentDto commentDto = AddCommentDto.of(addCommentDto, boardId, memberId);
        commentRepository.add(commentDto);
        return commentDto;
    }
}
