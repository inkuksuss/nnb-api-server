package com.smartFarm.was.web.service;

import com.smartFarm.was.domain.dto.comment.AddCommentDto;
import com.smartFarm.was.domain.dto.comment.CommentDto;
import com.smartFarm.was.domain.dto.comment.GetCommentDto;
import com.smartFarm.was.domain.request.comment.AddCommentForm;
import com.smartFarm.was.web.repository.CommentRepository;
import com.smartFarm.was.web.utils.SqlReturnUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public AddCommentDto addComment(AddCommentDto addCommentDto) throws SQLException {
        int result = commentRepository.addComment(addCommentDto);

        if (SqlReturnUtils.changeSuccess(result)) {
            GetCommentDto getCommentDto = new GetCommentDto();
            commentRepository.getComment()
        } else {

        }
    }

    @Override
    public GetCommentDto getComment(CommentDto commentDto) throws SQLException {

    }
}
