package com.smartFarm.was.web.service;

import com.smartFarm.was.domain.dto.comment.*;
import com.smartFarm.was.web.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<CommentDto> getComment(GetCommentDto getCommentDto) throws SQLException {

        if (getCommentDto.isOwner()) {
            return commentRepository.getComment(getCommentDto);
        } else {
            List<CommentDto> filteredCommentDtoList = commentRepository.getComment(getCommentDto).stream()
                    .map(el -> {
                        if (el.getCommentStatus().equals("private")) el.setCommentContent("");
                        return el;
                    }).collect(Collectors.toList());

            return filteredCommentDtoList;
        }
    }

    @Override
    public List<CommentDto> addComment(AddCommentDto addCommentDto) throws SQLException {

        commentRepository.addComment(addCommentDto);

        GetCommentDto getCommentDto = GetCommentDto.ownerFrom(addCommentDto.getBoardId());

        return commentRepository.getComment(getCommentDto);
    }

    @Override
    public List<CommentDto> updateComment(UpdateCommentDto updateCommentDto) throws SQLException {

        commentRepository.updateComment(updateCommentDto);

        GetCommentDto getCommentDto = GetCommentDto.ownerFrom(updateCommentDto.getBoardId());

        return commentRepository.getComment(getCommentDto);
    }

    @Override
    public List<CommentDto> deleteComment(DeleteCommentDto deleteCommentDto) throws SQLException {

        commentRepository.deleteComment(deleteCommentDto);

        GetCommentDto getCommentDto = GetCommentDto.ownerFrom(deleteCommentDto.getBoardId());

        return commentRepository.getComment(getCommentDto);
    }
}
