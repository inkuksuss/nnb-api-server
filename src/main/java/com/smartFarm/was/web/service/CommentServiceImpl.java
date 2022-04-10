package com.smartFarm.was.web.service;

import com.smartFarm.was.domain.dto.comment.*;
import com.smartFarm.was.web.repository.CommentRepository;
import com.smartFarm.was.web.utils.StatusCheckUtils;
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
    public List<CommentDto> getCommentList(GetCommentDto getCommentDto) throws SQLException {

        List<CommentDto> commentDtoList = commentRepository.getCommentList(getCommentDto);

        if (getCommentDto.isOwner()) {
            return commentDtoList;
        } else {
            List<CommentDto> filteredCommentDtoList = filteredCommentList(commentDtoList);

            return filteredCommentDtoList;
        }
    }

    @Override
    public List<CommentDto> addComment(AddCommentDto addCommentDto) throws SQLException {

        commentRepository.addComment(addCommentDto);

        GetCommentDto getCommentDto = GetCommentDto.forOwner(addCommentDto.getBoardId());

        return commentRepository.getCommentList(getCommentDto);
    }

    @Override
    public List<CommentDto> updateComment(UpdateCommentDto updateCommentDto) throws SQLException {

        commentRepository.updateComment(updateCommentDto);

        GetCommentDto getCommentDto = GetCommentDto.forOwner(updateCommentDto.getBoardId());

        return commentRepository.getCommentList(getCommentDto);
    }

    @Override
    public List<CommentDto> deleteComment(DeleteCommentDto deleteCommentDto) throws SQLException {

        commentRepository.deleteComment(deleteCommentDto);

        GetCommentDto getCommentDto = GetCommentDto.forOwner(deleteCommentDto.getBoardId());

        return commentRepository.getCommentList(getCommentDto);
    }

    public List<CommentDto> filteredCommentList(List<CommentDto> commentDtoList) {

        List<CommentDto> filteredCommentDtoList = commentDtoList.stream()
                .map(comment -> doFiltering(comment)).collect(Collectors.toList());

        return filteredCommentDtoList;
    }

    private CommentDto doFiltering(CommentDto commentDto) {
        if (StatusCheckUtils.isPrivate(commentDto.getCommentStatus())) {
            return CommentDto.getFiltered(commentDto);
        } else {
            return commentDto;
        }
    }
}
